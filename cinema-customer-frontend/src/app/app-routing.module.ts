import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./repertory/components/repertory.component";
import {BuyTicketComponent} from "./repertory/components/buy-ticket/buy-ticket.component";

const routes: Routes = [
  {path: 'repertory', component: RepertoryComponent},
  {path: 'buy-ticket/:id', component: BuyTicketComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
