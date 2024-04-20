import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {LoginFormComponent} from './components/login-form/login-form.component';
import {RegisterFormComponent} from './components/register-form/register-form.component';
import {SharedModule} from "../shared/shared.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {
  LoginMainFormFrameComponent
} from './components/login-form/login-main-form-frame/login-main-form-frame.component';
import {MatButtonModule} from "@angular/material/button";
import { RegisterMainFormFrameComponent } from './components/register-form/register-main-form-frame/register-main-form-frame.component';


@NgModule({
  declarations: [
    LoginFormComponent,
    RegisterFormComponent,
    LoginMainFormFrameComponent,
    RegisterMainFormFrameComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgOptimizedImage
  ]
})
export class UserModule {
}
