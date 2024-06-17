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
import {RegisterFormComponent} from "./user/components/register-form/register-form.component";
import {CinemaManagerTableComponent} from "./user/components/cinema-manager-table/cinema-manager-table.component";
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
import {LoginFormComponent} from "./auth/components/login-form/login-form.component";
import {AuthGuard} from "./auth/services/permission.service";
import {Role} from "./auth/services/roles";

const routes: Routes = [
  {
    path: 'cinemas',
    component: CinemaTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'cinemas/create',
    component: CinemaFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'cinemas/edit/:name',
    component: CinemaFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'cinemas/details/:name',
    component: CinemaDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'projection-technologies',
    component: ProjectionTechnologyTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.CINEMA_MANAGER, Role.ADMIN]}
  },
  {
    path: 'projection-technologies/create',
    component: ProjectionTechnologyFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'projection-technologies/edit/:technology',
    component: ProjectionTechnologyFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'projection-technologies/details/:technology',
    component: ProjectionTechnologyDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.CINEMA_MANAGER, Role.ADMIN]}
  },
  {
    path: 'movies',
    component: MovieTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.CINEMA_MANAGER, Role.ADMIN]}
  },
  {
    path: 'movies/create',
    component: MovieFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'movies/edit/:title',
    component: MovieFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'movies/details/:title',
    component: MovieDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.CINEMA_MANAGER, Role.ADMIN]}
  },
  {
    path: 'home', component: HomeComponent, canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_MANAGER, Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'cinema-managers',
    component: CinemaManagerTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'cinema-managers/create',
    component: CinemaManagerFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'cinema-managers/edit/:email',
    component: CinemaManagerFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_NETWORK_MANAGER, Role.ADMIN]}
  },
  {
    path: 'repertory',
    component: ManageRepertoryComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_MANAGER]}
  },
  {
    path: 'repertory/edit/:id',
    component: ScreeningFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_MANAGER]}
  },
  {
    path: 'repertory/create',
    component: ScreeningFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CINEMA_MANAGER]}
  },
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
