import {NgModule} from "@angular/core";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {SharedModule} from "../_shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {
  ResetPasswordFormComponent
} from "./components/reset-password/reset-password-form/reset-password-form.component";
import {ActivateAccountComponent} from "./components/register/activate-account/activate-account.component";
import {VerifyUserComponent} from "./components/register/verify-user/verify-user.component";
import {CheckEmailComponent} from "./components/reset-password/check-email/check-email.component";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatListModule} from "@angular/material/list";
import {MatStepperModule} from "@angular/material/stepper";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDialogModule} from "@angular/material/dialog";
import {MatTabsModule} from "@angular/material/tabs";
import {RouterLink} from "@angular/router";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    ResetPasswordFormComponent,
    ResetPasswordComponent,
    ActivateAccountComponent,
    VerifyUserComponent,
    CheckEmailComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatListModule,
    MatButtonModule,
    MatStepperModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDividerModule,
    MatTooltipModule,
    MatDialogModule,
    MatTabsModule,
    MatCardModule,
    RouterLink,
    NgOptimizedImage
  ],
})
export class AuthModule {
}
