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
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatIconModule} from "@angular/material/icon";
import {MatSelectModule} from "@angular/material/select";
import {
  ConfirmDeletionMovieDialog
} from "./components/confirm-deletion-movie-dialog/confirm-deletion-movie-dialog.component";
import {SharedModule} from "../_shared/shared.module";
import {MatList, MatListItem} from "@angular/material/list";
import {
  ProductionDetailsFormFieldsComponent
} from "./components/movie-form/components/production-details-form-fields/production-details-form-fields.component";
import {
  InformationFormFieldsComponent
} from "./components/movie-form/components/information-form-fields/information-form-fields.component";
import {
  ImageAndTrailerFormFieldsComponent
} from "./components/movie-form/components/image-and-trailer-form-fields/image-and-trailer-form-fields.component";
import {
  CreateTitleFormFieldsComponent
} from "./components/movie-form/components/create-title-form-fields/create-title-form-fields.component";
import {
  AgeRestrictionAndGenresFormFieldsComponent
} from "./components/movie-form/components/age-restriction-and-genres-form-fields/age-restriction-and-genres-form-fields.component";
import {
  MovieVariantsFormFieldsComponent
} from "./components/movie-form/components/movie-variants-form-fields/movie-variants-form-fields.component";


@NgModule({
  declarations: [
    MovieDetailsComponent,
    MovieTableComponent,
    MovieFormComponent,
    ConfirmDeletionMovieDialog,
    ProductionDetailsFormFieldsComponent,
    MovieVariantsFormFieldsComponent,
    InformationFormFieldsComponent,
    ImageAndTrailerFormFieldsComponent,
    CreateTitleFormFieldsComponent,
    AgeRestrictionAndGenresFormFieldsComponent,
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
    MatListItem,
    MatList,
  ]
})
export class MovieModule {
}
