import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {LoginFormFieldsComponent} from "./components/login-form/login-main-form-frame/login-form-fields.component";
import {LoginFormComponent} from "./components/login-form/login-form.component";

@NgModule({
  declarations: [
    LoginFormComponent,
    LoginFormFieldsComponent
  ],
  imports: [
    CommonModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatIconModule,
  ]
})
export class AuthModule {
}
