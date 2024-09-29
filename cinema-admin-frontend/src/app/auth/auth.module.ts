import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {LoginFormComponent} from "./components/login-form/login-form.component";
import {LoginFormFieldsComponent} from "./components/login-form/login-form-fields/login-form-fields.component";

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
