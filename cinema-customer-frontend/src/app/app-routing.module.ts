import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./repertory/components/repertory.component";
import {BuyTicketComponent} from "./repertory/components/buy-ticket/buy-ticket.component";
import {LoginComponent} from "./user/components/login/login.component";
import {RegisterComponent} from "./user/components/register/register.component";
import {AccountPageComponent} from "./user/components/account-page/account-page.component";
import {ResetPasswordComponent} from "./user/components/reset-password/reset-password.component";
import {
  ResetPasswordFormComponent
} from "./user/components/reset-password/reset-password-form/reset-password-form.component";
import {ActivateAccountComponent} from "./user/components/register/activate-account/activate-account.component";
import {CheckEmailComponent} from "./user/components/reset-password/check-email/check-email.component";
import {AuthGuard} from "./auth/service/permission.service";
import {VerifyUserComponent} from "./user/components/register/verify-user/verify-user.component";

const routes: Routes = [
  {path: '', component: RepertoryComponent},
  {path: 'repertory', component: RepertoryComponent},
  {path: 'buy-ticket/:id', component: BuyTicketComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegisterComponent},
  {path: 'registration/verify-user', component: VerifyUserComponent},
  {path: 'account', component: AccountPageComponent, canActivate: [AuthGuard]},
  {path: 'check-email', component: CheckEmailComponent},
  {path: 'reset-password', component: ResetPasswordComponent},
  {path: 'activate-account', component: ActivateAccountComponent},
  {path: 'reset-password-form', component: ResetPasswordFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
