import {NgModule} from "@angular/core";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {SharedModule} from "../_shared/shared.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {
  LoginMainFormFrameComponent
} from "./components/login-form/login-main-form-frame/login-main-form-frame.component";
import {LoginFormComponent} from "./components/login-form/login-form.component";

@NgModule({
  declarations: [
    LoginFormComponent,
    LoginMainFormFrameComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgOptimizedImage,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    FormsModule,
  ]
})
export class AuthModule {
}
