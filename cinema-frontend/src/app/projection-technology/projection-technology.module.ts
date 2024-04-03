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
  CreateProjectionTechnologyComponent
} from './components/create-projection-technology/create-projection-technology.component';
import {
  EditProjectionTechnologyComponent
} from './components/edit-projection-technology/edit-projection-technology.component';
import {
  CreateProjectionTechnologyMainFormFrameComponent
} from './components/create-projection-technology/create-projection-technology-main-form-frame/create-projection-technology-main-form-frame.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";


@NgModule({
  declarations: [
    ProjectionTechnologyListComponent,
    ProjectionTechnologyDetailsComponent,
    CreateProjectionTechnologyComponent,
    EditProjectionTechnologyComponent,
    CreateProjectionTechnologyMainFormFrameComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatTableModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class ProjectionTechnologyModule {
}
