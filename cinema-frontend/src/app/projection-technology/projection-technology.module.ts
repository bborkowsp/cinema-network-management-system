import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  ProjectionTechnologyListComponent
} from './components/projection-technology-list/projection-technology-list.component';
import {
  ProjectionTechnologyDetailsComponent
} from './components/projection-technology-details/projection-technology-details.component';
import {
  ProjectionTechnologyFormComponent
} from './components/projection-technology-form/projection-technology-form.component';
import {SharedModule} from "../shared/shared.module";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";


@NgModule({
  declarations: [
    ProjectionTechnologyListComponent,
    ProjectionTechnologyDetailsComponent,
    ProjectionTechnologyFormComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatTableModule,
    MatPaginatorModule
  ]
})
export class ProjectionTechnologyModule {
}
