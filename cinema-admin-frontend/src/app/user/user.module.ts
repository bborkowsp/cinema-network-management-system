import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {SharedModule} from "../_shared/shared.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {UserFormComponent} from "./components/user-form/user-form.component";
import {CinemaManagerFormComponent} from './components/cinema-manager-form/cinema-manager-form.component';
import {
  CinemaManagerFormFieldsComponent
} from './components/cinema-manager-form/components/cinema-manager-form-frame/cinema-manager-form-fields.component';
import {UserTableComponent} from './components/user-table/user-table.component';
import {CinemaManagerTableComponent} from './components/cinema-manager-table/cinema-manager-table.component';
import {UserFormFieldsComponent} from "./components/user-form/components/user-form-fields/user-form-fields.component";


@NgModule({
  declarations: [
    UserFormComponent,
    UserFormFieldsComponent,
    CinemaManagerFormComponent,
    CinemaManagerFormFieldsComponent,
    UserTableComponent,
    CinemaManagerTableComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgOptimizedImage,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule
  ]
})
export class UserModule {
}
