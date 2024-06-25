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
import {UserFormFrameComponent} from "./components/user-form/components/user-form-frame/user-form-frame.component";
import {UserFormComponent} from "./components/user-form/user-form.component";
import {CinemaManagerFormComponent} from './components/cinema-manager-form/cinema-manager-form.component';
import {
  CinemaManagerFormFrameComponent
} from './components/cinema-manager-form/components/cinema-manager-form-frame/cinema-manager-form-frame.component';
import {UserTableComponent} from './components/user-table/user-table.component';
import {CinemaManagerTableComponent} from './components/cinema-manager-table/cinema-manager-table.component';


@NgModule({
  declarations: [
    UserFormComponent,
    UserFormFrameComponent,
    CinemaManagerFormComponent,
    CinemaManagerFormFrameComponent,
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
