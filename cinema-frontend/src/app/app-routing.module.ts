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
import {
  EditProjectionTechnologyComponent
} from "./projection-technology/components/edit-projection-technology/edit-projection-technology.component";
import {
  CreateProjectionTechnologyFormComponent
} from "./projection-technology/components/create-projection-technology-form/create-projection-technology-form.component";
import {HomeComponent} from "./home/home.component";
import {MovieListComponent} from "./movie/components/movie-list/movie-list.component";
import {CreateMovieFormComponent} from "./movie/components/create-movie-form/create-movie-form.component";
import {EditMovieFormComponent} from "./movie/components/edit-movie-form/edit-movie-form.component";
import {MovieDetailsComponent} from "./movie/components/movie-details/movie-details.component";
import {CreateCinemaFormComponent} from "./cinema/components/create-cinema-form/create-cinema-form.component";

const routes: Routes = [
  {path: 'cinemas', component: CinemaListComponent},
  {path: 'cinemas/create', component: CreateCinemaFormComponent},
  {path: 'cinemas/details/:name', component: CinemaDetailsComponent},
  {path: 'projection-technologies', component: ProjectionTechnologyListComponent},
  {path: 'projection-technologies/create', component: CreateProjectionTechnologyFormComponent},
  {path: 'projection-technologies/edit/:technology', component: EditProjectionTechnologyComponent},
  {path: 'projection-technologies/details/:technology', component: ProjectionTechnologyDetailsComponent},
  {path: 'movies', component: MovieListComponent},
  {path: 'movies/create', component: CreateMovieFormComponent},
  {path: 'movies/edit/:name', component: EditMovieFormComponent},
  {path: 'movies/details/:name', component: MovieDetailsComponent},
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: '**', redirectTo: '/home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
