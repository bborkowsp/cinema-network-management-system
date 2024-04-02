import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CinemaListComponent} from "./cinema/components/cinema-list/cinema-list.component";
import {CinemaFormComponent} from "./cinema/components/cinema-form/cinema-form.component";
import {CinemaDetailsComponent} from "./cinema/components/cinema-details/cinema-details.component";

const routes: Routes = [
  {path: 'cinemas', component: CinemaListComponent},
  {path: 'cinemas/create', component: CinemaFormComponent},
  {path: 'cinemas/details/:name', component: CinemaDetailsComponent},
  {path: '', redirectTo: '/cinemas', pathMatch: 'full'},
  {path: '**', redirectTo: '/cinemas', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
