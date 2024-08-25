import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./repertory/components/repertory.component";
import {BuyTicketComponent} from "./repertory/components/buy-ticket/buy-ticket.component";
import {LoginComponent} from "./user/components/login/login.component";
import {RegisterComponent} from "./user/components/register/register.component";
import {VerifyUserComponent} from "./user/components/verify-user/verify-user.component";

const routes: Routes = [
  {path: '', component: RepertoryComponent},
  {path: 'repertory', component: RepertoryComponent},
  {path: 'buy-ticket/:id', component: BuyTicketComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegisterComponent},
  {path: 'registration/verify-user', component: VerifyUserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
