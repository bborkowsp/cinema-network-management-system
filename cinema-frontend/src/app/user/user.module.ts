import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {RegisterFormComponent} from './components/register-form/register-form.component';
import {SharedModule} from "../_shared/shared.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {
  RegisterMainFormFrameComponent
} from './components/register-form/register-main-form-frame/register-main-form-frame.component';
import {CinemaManagerTableComponent} from './components/cinema-manager-table/cinema-manager-table.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {CinemaManagerFormComponent} from './components/cinema-manager-form/cinema-manager-form.component';
import {
  CinemaManagerFormFrameComponent
} from './components/cinema-manager-form/components/cinema-manager-form-frame/cinema-manager-form-frame.component';


@NgModule({
  declarations: [
    RegisterFormComponent,
    RegisterMainFormFrameComponent,
    CinemaManagerTableComponent,
    CinemaManagerFormComponent,
    CinemaManagerFormFrameComponent,
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
