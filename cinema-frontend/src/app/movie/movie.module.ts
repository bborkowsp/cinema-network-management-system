import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {EditMovieFormComponent} from "./components/edit-movie-form/edit-movie-form.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {SharedModule} from "../shared/shared.module";
import {MatDividerModule} from "@angular/material/divider";
import {CreateMovieComponent} from "./components/create-movie/create-movie.component";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {
  CreateTitleFormFrameComponentComponent
} from './components/create-movie/components/create-title-form-frame-component/create-title-form-frame-component.component';
import {
  InformationFormFrameComponentComponent
} from './components/create-movie/components/information-form-frame-component/information-form-frame-component.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {
  ProductionDetailsFormFrameComponentComponent
} from './components/create-movie/components/production-details-form-frame-component/production-details-form-frame-component.component';
import {
  ImageAndTrailerFormFrameComponentComponent
} from './components/create-movie/components/image-and-trailer-form-frame-component/image-and-trailer-form-frame-component.component';
import {MatIconModule} from "@angular/material/icon";
import {
  AgeRestrictionAndGenresFormFrameComponentComponent
} from './components/create-movie/components/age-restriction-and-genres-form-frame-component/age-restriction-and-genres-form-frame-component.component';
import {MatSelectModule} from "@angular/material/select";
import {
  ProjectionDetailsFormFrameComponentComponent
} from './components/create-movie/components/projection-details-form-frame-component/projection-details-form-frame-component.component';
import {MatNativeDateModule} from "@angular/material/core";
import { MovieFormComponent } from './components/movie-form/movie-form.component';


@NgModule({
  declarations: [
    MovieDetailsComponent,
    CreateMovieComponent,
    MovieListComponent,
    EditMovieFormComponent,
    CreateTitleFormFrameComponentComponent,
    InformationFormFrameComponentComponent,
    ProductionDetailsFormFrameComponentComponent,
    ImageAndTrailerFormFrameComponentComponent,
    AgeRestrictionAndGenresFormFrameComponentComponent,
    ProjectionDetailsFormFrameComponentComponent,
    MovieFormComponent,
  ],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatTableModule,
    SharedModule,
    MatDividerModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatIconModule,
    MatSelectModule,
    MatNativeDateModule
  ]
})
export class MovieModule {
}
