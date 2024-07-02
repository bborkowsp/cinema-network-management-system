import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./repertory/components/repertory.component";

const routes: Routes = [
  {path: 'repertory', component: RepertoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
