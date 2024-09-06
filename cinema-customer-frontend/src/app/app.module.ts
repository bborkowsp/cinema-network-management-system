import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from "./_shared/shared.module";
import {RepertoryModule} from "./repertory/repertory.module";
import {AppRoutingModule} from "./app-routing.module";
import { provideHttpClient, withInterceptorsFromDi } from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {MatDialogModule} from "@angular/material/dialog";
import {UserModule} from "./user/user.module";

@NgModule({ declarations: [
        AppComponent,
    ],
    bootstrap: [AppComponent], imports: [BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        SharedModule,
        RepertoryModule,
        MatDialogModule,
        UserModule], providers: [
        DatePipe,
        provideHttpClient(withInterceptorsFromDi())
    ] })
export class AppModule {
}
