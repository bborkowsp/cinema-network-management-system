import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CinemaListComponent} from "./cinema/components/cinema-list/cinema-list.component";
import {CinemaDetailsComponent} from "./cinema/components/cinema-details/cinema-details.component";
import {
  ProjectionTechnologyListComponent
} from "./projection-technology/components/projection-technology-list/projection-technology-list.component";
import {
  ProjectionTechnologyDetailsComponent
} from "./projection-technology/components/projection-technology-details/projection-technology-details.component";
import {HomeComponent} from "./home/home.component";
import {MovieListComponent} from "./movie/components/movie-list/movie-list.component";
import {EditMovieFormComponent} from "./movie/components/edit-movie-form/edit-movie-form.component";
import {MovieDetailsComponent} from "./movie/components/movie-details/movie-details.component";
import {CreateCinemaFormComponent} from "./cinema/components/create-cinema-form/create-cinema-form.component";
import {LoginFormComponent} from "./user/components/login-form/login-form.component";
import {RegisterFormComponent} from "./user/components/register-form/register-form.component";
import {CreateMovieComponent} from "./movie/components/create-movie/create-movie.component";
import {CinemaManagerListComponent} from "./user/components/cinema-manager-list/cinema-manager-list.component";
import {AuthGuard} from "./user/services/permission.service";
import {ManageRepertoryComponent} from "./repertory/components/manage-repertory/manage-repertory.component";
import {CinemaManagerFormComponent} from "./user/components/cinema-manager-form/cinema-manager-form.component";
import {
  ScreeningFormComponent
} from "./repertory/components/manage-repertory/components/screening-form/screening-form.component";
import {
  ProjectionTechnologyFormComponent
} from "./projection-technology/components/projection-technology-form/projection-technology-form.component";


const routes: Routes = [
  {path: 'cinemas', component: CinemaListComponent, canActivate: [AuthGuard]},
  {path: 'cinemas/create', component: CreateCinemaFormComponent, canActivate: [AuthGuard]},
  {path: 'cinemas/details/:name', component: CinemaDetailsComponent, canActivate: [AuthGuard]},
  {path: 'projection-technologies', component: ProjectionTechnologyListComponent, canActivate: [AuthGuard]},
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
  {path: 'movies', component: MovieListComponent, canActivate: [AuthGuard]},
  {path: 'movies/create', component: CreateMovieComponent, canActivate: [AuthGuard]},
  {path: 'movies/edit/:title', component: EditMovieFormComponent, canActivate: [AuthGuard]},
  {path: 'movies/details/:title', component: MovieDetailsComponent, canActivate: [AuthGuard]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'cinema-managers', component: CinemaManagerListComponent, canActivate: [AuthGuard]},
  {path: 'cinema-managers/create', component: CinemaManagerFormComponent, canActivate: [AuthGuard]},
  {path: 'cinema-managers/edit/:email', component: CinemaManagerFormComponent, canActivate: [AuthGuard]},
  {path: 'repertory', component: ManageRepertoryComponent},
  {path: 'repertory/edit/:id', component: ScreeningFormComponent},
  {path: 'repertory/create/:roomName', component: ScreeningFormComponent},
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
