import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieTableComponent} from "./components/movie-table/movie-table.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatDividerModule} from "@angular/material/divider";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule} from "@angular/material/core";
import {MovieFormComponent} from './components/movie-form/movie-form.component';
import {
  CreateTitleFormFrameComponentComponent
} from "./components/movie-form/components/create-title-form-frame-component/create-title-form-frame-component.component";
import {
  InformationFormFrameComponentComponent
} from "./components/movie-form/components/information-form-frame-component/information-form-frame-component.component";
import {
  ProductionDetailsFormFrameComponentComponent
} from "./components/movie-form/components/production-details-form-frame-component/production-details-form-frame-component.component";
import {
  ImageAndTrailerFormFrameComponentComponent
} from "./components/movie-form/components/image-and-trailer-form-frame-component/image-and-trailer-form-frame-component.component";
import {
  ProjectionDetailsFormFrameComponentComponent
} from "./components/movie-form/components/projection-details-form-frame-component/projection-details-form-frame-component.component";
import {
  AgeRestrictionAndGenresFormFrameComponentComponent
} from "./components/movie-form/components/age-restriction-and-genres-form-frame-component/age-restriction-and-genres-form-frame-component.component";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatIconModule} from "@angular/material/icon";
import {MatSelectModule} from "@angular/material/select";
import {
  ConfirmDeletionMovieDialog
} from "./components/confirm-deletion-movie-dialog/confirm-deletion-movie-dialog.component";
import {SharedModule} from "../_shared/shared.module";


@NgModule({
  declarations: [
    MovieDetailsComponent,
    MovieTableComponent,
    CreateTitleFormFrameComponentComponent,
    InformationFormFrameComponentComponent,
    ProductionDetailsFormFrameComponentComponent,
    ImageAndTrailerFormFrameComponentComponent,
    AgeRestrictionAndGenresFormFrameComponentComponent,
    ProjectionDetailsFormFrameComponentComponent,
    MovieFormComponent,
    ConfirmDeletionMovieDialog,
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
    MatNativeDateModule,
    SharedModule
  ]
})
export class MovieModule {
}
