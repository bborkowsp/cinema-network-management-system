import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {CinemaModule} from "./cinema/cinema.module";
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {SharedModule} from "./_shared/shared.module";
import {HomeModule} from "./home/home.module";
import {ErrorHandlerInterceptor} from "./_shared/interceptors/error-handler.interceptor";
import {MovieModule} from "./movie/movie.module";
import {JwtInterceptor} from "./_shared/interceptors/jwt.interceptor";
import {UserModule} from "./user/user.module";
import {RepertoryModule} from "./repertory/repertory.module";
import {AuthService} from "./auth/services/auth.service";
import {AuthModule} from "./auth/auth.module";
import {LogsModule} from "./logs/logs.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  bootstrap: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
    FormsModule,
    NgIf,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatProgressSpinnerModule,
    SharedModule,
    CinemaModule,
    HomeModule,
    MovieModule,
    UserModule,
    RepertoryModule,
    AuthModule,
    LogsModule,
  ], providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlerInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
    AuthService,
    provideHttpClient(withInterceptorsFromDi())
  ]
})
export class AppModule {
}
