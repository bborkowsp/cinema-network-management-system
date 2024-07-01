import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDialogModule} from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatToolbarModule} from "@angular/material/toolbar";
import {RouterLink} from "@angular/router";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatListModule} from "@angular/material/list";
import {ScaffoldComponent} from "./components/scaffold/scaffold.component";
import {AccountComponent} from "./components/scaffold/components/account/account.component";
import {ToolbarComponent} from "./components/scaffold/components/toolbar/toolbar.component";
import {MatSidenavModule} from "@angular/material/sidenav";

const sharedModules = [
  CommonModule,
  ReactiveFormsModule,
  MatButtonModule,
  MatInputModule,
  MatFormFieldModule,
  MatSelectModule,
  MatDialogModule,
  MatIconModule,
  FormsModule,
  MatSnackBarModule,
];


@NgModule({
  declarations: [
    ScaffoldComponent,
    AccountComponent,
    ToolbarComponent,
  ],
  imports: [
    ...sharedModules,
    MatMenuModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatCheckboxModule,
    MatToolbarModule,
    RouterLink,
    MatTooltipModule,
    MatDialogModule,
    MatListModule,
    MatSidenavModule,
  ],
  exports: [
    ScaffoldComponent
  ],
})
export class SharedModule {
}
