import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./repertory/components/repertory.component";
import {BuyTicketComponent} from "./repertory/components/buy-ticket/buy-ticket.component";
import {LoginComponent} from "./user/components/login/login.component";
import {RegisterComponent} from "./user/components/register/register.component";
import {VerifyUserComponent} from "./user/components/verify-user/verify-user.component";
import {AccountPageComponent} from "./user/components/account-page/account-page.component";
import {AuthGuard} from "./auth/permission.service";
import {CheckEmailComponent} from "./user/components/check-email/check-email.component";
import {ResetPasswordComponent} from "./user/components/reset-password/reset-password.component";
import {ActivateAccountComponent} from "./user/components/activate-account/activate-account.component";

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
  {path: 'activate-account', component: ActivateAccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
