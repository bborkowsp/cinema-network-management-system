import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CreateMovieFormComponent} from "./components/create-movie-form/create-movie-form.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {EditMovieFormComponent} from "./components/edit-movie-form/edit-movie-form.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    CreateMovieFormComponent,
    MovieDetailsComponent,
    MovieListComponent,
    EditMovieFormComponent
  ],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatTableModule,
    SharedModule
  ]
})
export class MovieModule {
}
