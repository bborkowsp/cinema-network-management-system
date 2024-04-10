import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CreateMovieFormComponent} from "./components/create-movie-form/create-movie-form.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {EditMovieFormComponent} from "./components/edit-movie-form/edit-movie-form.component";


@NgModule({
  declarations: [
    CreateMovieFormComponent,
    MovieDetailsComponent,
    MovieListComponent,
    EditMovieFormComponent
  ],
  imports: [
    CommonModule
  ]
})
export class MovieModule {
}
