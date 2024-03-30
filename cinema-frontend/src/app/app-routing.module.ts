import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CinemaListComponent} from "./cinema/components/cinema-list/cinema-list.component";

const routes: Routes = [
  {path: 'cinemas', component: CinemaListComponent},
  {path: '', redirectTo: '/cinemas', pathMatch: 'full'},
  {path: '**', redirectTo: '/cinemas', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
