import {NgModule} from "@angular/core";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {MatStepperModule} from "@angular/material/stepper";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDialogModule} from "@angular/material/dialog";
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from "./components/login/login.component";
import {SharedModule} from "../_shared/shared.module";
import {VerifyUserComponent} from './components/verify-user/verify-user.component';
import {AccountPageComponent} from './components/account-page/account-page.component';
import {MatTabsModule} from "@angular/material/tabs";
import {CheckEmailComponent} from './components/check-email/check-email.component';
import {RouterLink} from "@angular/router";
import {ResetPasswordComponent} from './components/reset-password/reset-password.component';
import {ActivateAccountComponent} from './components/activate-account/activate-account.component';
import {MatCardModule} from "@angular/material/card";

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    VerifyUserComponent,
    AccountPageComponent,
    CheckEmailComponent,
    ResetPasswordComponent,
    ActivateAccountComponent
  ],
  imports: [
    CommonModule,
    MatInputModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatListModule,
    MatButtonModule,
    MatStepperModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    NgOptimizedImage,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
    SharedModule,
    MatTabsModule,
    RouterLink,
    MatCardModule
  ],
})
export class UserModule {
}
