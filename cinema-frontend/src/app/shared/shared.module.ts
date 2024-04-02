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
import {PageContentContainerComponent} from './components/page-content-container/page-content-container.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatToolbarModule} from "@angular/material/toolbar";
import {OptionsTableButtonComponent} from './components/options-table-button/options-table-button.component';
import {RouterLink} from "@angular/router";
import {MatTooltipModule} from "@angular/material/tooltip";
import {GenericFormFrameComponent} from './components/generic-form-frame/generic-form-frame.component';

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

const declarations = [
  PageContentContainerComponent,
];

@NgModule({
  declarations: [declarations, OptionsTableButtonComponent, GenericFormFrameComponent],
  imports: [
    ...sharedModules,
    MatMenuModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatCheckboxModule,
    MatToolbarModule,
    RouterLink,
    MatTooltipModule,
  ],
  exports: [declarations, OptionsTableButtonComponent, GenericFormFrameComponent],
})
export class SharedModule {
}
