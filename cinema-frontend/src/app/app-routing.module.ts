import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CinemaListComponent} from "./cinema/components/cinema-list/cinema-list.component";
import {CinemaFormComponent} from "./cinema/components/cinema-form/cinema-form.component";
import {CinemaDetailsComponent} from "./cinema/components/cinema-details/cinema-details.component";
import {
  ProjectionTechnologyListComponent
} from "./projection-technology/components/projection-technology-list/projection-technology-list.component";
import {
  ProjectionTechnologyDetailsComponent
} from "./projection-technology/components/projection-technology-details/projection-technology-details.component";
import {
  CreateProjectionTechnologyComponent
} from "./projection-technology/components/create-projection-technology/create-projection-technology.component";
import {
  EditProjectionTechnologyComponent
} from "./projection-technology/components/edit-projection-technology/edit-projection-technology.component";

const routes: Routes = [
  {path: 'cinemas', component: CinemaListComponent},
  {path: 'cinemas/create', component: CinemaFormComponent},
  {path: 'cinemas/details/:name', component: CinemaDetailsComponent},
  {path: 'projection-technologies', component: ProjectionTechnologyListComponent},
  {path: 'projection-technologies/create', component: CreateProjectionTechnologyComponent},
  {path: 'projection-technologies/edit/:name', component: EditProjectionTechnologyComponent},
  {path: 'projection-technologies/details/:name', component: ProjectionTechnologyDetailsComponent},
  {path: '', redirectTo: '/cinemas', pathMatch: 'full'},
  {path: '**', redirectTo: '/cinemas', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
