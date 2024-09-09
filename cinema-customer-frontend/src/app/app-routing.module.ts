import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./repertory/components/repertory.component";
import {BuyTicketComponent} from "./repertory/components/buy-ticket/buy-ticket.component";
import {AccountPageComponent} from "./user/components/account-page/account-page.component";
import {AuthGuard} from "./auth/service/permission.service";
import {
  CancelPaypalPaymentComponent
} from "./repertory/components/cancel-paypal-payment/cancel-paypal-payment.component";
import {
  CapturePaypalPaymentComponent
} from "./repertory/components/capture-paypal-payment/capture-paypal-payment.component";
import {ScreeningDetailsComponent} from "./repertory/components/screening-details/screening-details.component";
import {LoginComponent} from "./auth/components/login/login.component";
import {RegisterComponent} from "./auth/components/register/register.component";
import {VerifyUserComponent} from "./auth/components/register/verify-user/verify-user.component";
import {CheckEmailComponent} from "./auth/components/reset-password/check-email/check-email.component";
import {ResetPasswordComponent} from "./auth/components/reset-password/reset-password.component";
import {ActivateAccountComponent} from "./auth/components/register/activate-account/activate-account.component";
import {
  ResetPasswordFormComponent
} from "./auth/components/reset-password/reset-password-form/reset-password-form.component";

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
  {path: 'reset-password-form', component: ResetPasswordFormComponent},
  {path: 'cancel-paypal-payment', component: CancelPaypalPaymentComponent},
  {path: 'capture-paypal-payment', component: CapturePaypalPaymentComponent},
  {path: 'movie-details/:title/:date', component: ScreeningDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
