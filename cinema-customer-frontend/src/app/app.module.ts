import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from "./_shared/shared.module";
import {RepertoryModule} from "./repertory/repertory.module";
import {AppRoutingModule} from "./app-routing.module";
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {MatDialogModule} from "@angular/material/dialog";
import {UserModule} from "./user/user.module";
import {JwtInterceptor} from "./_shared/interceptors/jwt.interceptor";
import {AuthModule} from "./auth/auth.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  bootstrap: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SharedModule,
    RepertoryModule,
    MatDialogModule,
    UserModule,
    AuthModule,
  ],
  providers: [
    DatePipe,
    provideHttpClient(withInterceptorsFromDi()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ]
})
export class AppModule {
}
