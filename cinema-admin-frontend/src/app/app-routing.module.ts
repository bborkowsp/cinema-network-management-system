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
import {ManageRepertoryComponent} from "./repertory/components/manage-repertory/manage-repertory.component";
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
import {UserFormComponent} from "./user/components/user-form/user-form.component";
import {UserTableComponent} from "./user/components/user-table/user-table.component";
import {CinemaManagerTableComponent} from "./user/components/cinema-manager-table/cinema-manager-table.component";
import {CinemaManagerFormComponent} from "./user/components/cinema-manager-form/cinema-manager-form.component";

const routes: Routes = [
  {
    path: 'cinemas',
    component: CinemaTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'cinemas/create',
    component: CinemaFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'cinemas/edit/:name',
    component: CinemaFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'cinemas/details/:name',
    component: CinemaDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'projection-technologies',
    component: ProjectionTechnologyTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_CINEMA_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'projection-technologies/create',
    component: ProjectionTechnologyFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'projection-technologies/edit/:technology',
    component: ProjectionTechnologyFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'projection-technologies/details/:technology',
    component: ProjectionTechnologyDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_CINEMA_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'movies',
    component: MovieTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_CINEMA_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'movies/create',
    component: MovieFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'movies/edit/:title',
    component: MovieFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'movies/details/:title',
    component: MovieDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_CINEMA_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'cinema-managers',
    component: CinemaManagerTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'cinema-managers/create',
    component: CinemaManagerFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'cinema-managers/edit/:email',
    component: CinemaManagerFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
  {
    path: 'users',
    component: UserTableComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_ADMIN]}
  },
  {
    path: 'users/create',
    component: UserFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_ADMIN]}
  },
  {
    path: 'users/edit/:email+role',
    component: UserFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_ADMIN]}
  },
  {
    path: 'repertory',
    component: ManageRepertoryComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_MANAGER]}
  },
  {
    path: 'repertory/edit/:id',
    component: ScreeningFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_MANAGER]}
  },
  {
    path: 'repertory/create',
    component: ScreeningFormComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_MANAGER]}
  },
  {
    path: 'home', component: HomeComponent, canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CINEMA_MANAGER, Role.ROLE_CINEMA_NETWORK_MANAGER, Role.ROLE_ADMIN]}
  },
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
