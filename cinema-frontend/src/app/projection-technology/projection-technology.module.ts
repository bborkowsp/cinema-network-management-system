import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  ProjectionTechnologyListComponent
} from './components/projection-technology-list/projection-technology-list.component';
import {
  ProjectionTechnologyDetailsComponent
} from './components/projection-technology-details/projection-technology-details.component';

import {SharedModule} from "../shared/shared.module";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {
  EditProjectionTechnologyComponent
} from './components/edit-projection-technology/edit-projection-technology.component';

import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {
  CreateProjectionTechnologyFormComponent
} from "./components/create-projection-technology-form/create-projection-technology-form.component";
import {
  CreateProjectionTechnologyMainFormFrameComponent
} from "./components/create-projection-technology-form/create-projection-technology-main-form-frame/create-projection-technology-main-form-frame.component";
import {MatButtonModule} from "@angular/material/button";
import {
  ConfirmDeletionProjectionTechnologyDialogComponent
} from './components/confirm-deletion-projection-technology-dialog/confirm-deletion-projection-technology-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";


@NgModule({
  declarations: [
    ProjectionTechnologyListComponent,
    ProjectionTechnologyDetailsComponent,
    EditProjectionTechnologyComponent,
    CreateProjectionTechnologyFormComponent,
    CreateProjectionTechnologyMainFormFrameComponent,
    ConfirmDeletionProjectionTechnologyDialogComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatTableModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class ProjectionTechnologyModule {
}
