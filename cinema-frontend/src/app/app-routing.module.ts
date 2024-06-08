import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CinemaTableComponent} from "./cinema/components/cinema-table/cinema-table.component";
import {CinemaDetailsComponent} from "./cinema/components/cinema-details/cinema-details.component";
import {
  ProjectionTechnologyTableComponent
} from "./projection-technology/components/projection-technology-table/projection-technology-table.component";
import {
  ProjectionTechnologyDetailsComponent
} from "./projection-technology/components/projection-technology-details/projection-technology-details.component";
import {HomeComponent} from "./home/home.component";
import {MovieTableComponent} from "./movie/components/movie-table/movie-table.component";
import {MovieDetailsComponent} from "./movie/components/movie-details/movie-details.component";
import {LoginFormComponent} from "./user/components/login-form/login-form.component";
import {RegisterFormComponent} from "./user/components/register-form/register-form.component";
import {CinemaManagerTableComponent} from "./user/components/cinema-manager-table/cinema-manager-table.component";
import {AuthGuard} from "./user/services/permission.service";
import {ManageRepertoryComponent} from "./repertory/components/manage-repertory/manage-repertory.component";
import {CinemaManagerFormComponent} from "./user/components/cinema-manager-form/cinema-manager-form.component";
import {
  ScreeningFormComponent
} from "./repertory/components/manage-repertory/components/screening-form/screening-form.component";
import {
  ProjectionTechnologyFormComponent
} from "./projection-technology/components/projection-technology-form/projection-technology-form.component";
import {CinemaFormComponent} from "./cinema/components/cinema-form/cinema-form.component";
import {MovieFormComponent} from "./movie/components/movie-form/movie-form.component";

const routes: Routes = [
  {path: 'cinemas', component: CinemaTableComponent, canActivate: [AuthGuard]},
  {path: 'cinemas/create', component: CinemaFormComponent, canActivate: [AuthGuard]},
  {path: 'cinemas/edit/:name', component: CinemaFormComponent, canActivate: [AuthGuard]},
  {path: 'cinemas/details/:name', component: CinemaDetailsComponent, canActivate: [AuthGuard]},
  {path: 'projection-technologies', component: ProjectionTechnologyTableComponent, canActivate: [AuthGuard]},
  {
    path: 'projection-technologies/create',
    component: ProjectionTechnologyFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'projection-technologies/edit/:technology',
    component: ProjectionTechnologyFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'projection-technologies/details/:technology',
    component: ProjectionTechnologyDetailsComponent,
    canActivate: [AuthGuard]
  },
  {path: 'movies', component: MovieTableComponent, canActivate: [AuthGuard]},
  {path: 'movies/create', component: MovieFormComponent, canActivate: [AuthGuard]},
  {path: 'movies/edit/:title', component: MovieFormComponent, canActivate: [AuthGuard]},
  {path: 'movies/details/:title', component: MovieDetailsComponent, canActivate: [AuthGuard]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'cinema-managers', component: CinemaManagerTableComponent, canActivate: [AuthGuard]},
  {path: 'cinema-managers/create', component: CinemaManagerFormComponent, canActivate: [AuthGuard]},
  {path: 'cinema-managers/edit/:email', component: CinemaManagerFormComponent, canActivate: [AuthGuard]},
  {path: 'repertory', component: ManageRepertoryComponent, canActivate: [AuthGuard]},
  {path: 'repertory/edit/:id', component: ScreeningFormComponent, canActivate: [AuthGuard]},
  {path: 'repertory/create', component: ScreeningFormComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterFormComponent},
  {path: 'login', component: LoginFormComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: '**', redirectTo: '/login', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
