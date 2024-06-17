import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  ProjectionTechnologyTableComponent
} from './components/projection-technology-table/projection-technology-table.component';
import {
  ProjectionTechnologyDetailsComponent
} from './components/projection-technology-details/projection-technology-details.component';

import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";

import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {
  ConfirmDeletionProjectionTechnologyDialog
} from './components/confirm-deletion-projection-technology-dialog/confirm-deletion-projection-technology-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {
  ProjectionTechnologyFormComponent
} from './components/projection-technology-form/projection-technology-form.component';
import {
  ProjectionTechnologyFormFrameComponent
} from './components/projection-technology-form/components/projection-technology-form-frame/projection-technology-form-frame.component';
import {SharedModule} from "../_shared/shared.module";


@NgModule({
  declarations: [
    ProjectionTechnologyTableComponent,
    ProjectionTechnologyDetailsComponent,
    ConfirmDeletionProjectionTechnologyDialog,
    ProjectionTechnologyFormComponent,
    ProjectionTechnologyFormFrameComponent,
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
    MatDialogModule,
  ]
})
export class ProjectionTechnologyModule {
}
