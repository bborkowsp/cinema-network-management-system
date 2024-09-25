import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from "@angular/material/toolbar";
import {RouterLink} from "@angular/router";
import {MatListModule} from "@angular/material/list";
import {MatSidenavModule} from "@angular/material/sidenav";
import {PageBottomSectionComponent} from './components/page-bottom-section/page-bottom-section.component';
import {ToolbarComponent} from "./components/toolbar/toolbar.component";
import {SnackBarComponent} from "./components/snackbar/snack-bar.component";
import {MatSnackBarAction, MatSnackBarActions, MatSnackBarLabel} from "@angular/material/snack-bar";


@NgModule({
  declarations: [
    ToolbarComponent,
    PageBottomSectionComponent,
    SnackBarComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    RouterLink,
    MatSidenavModule,
    MatMenuModule,
    MatSnackBarLabel,
    MatSnackBarActions,
    MatSnackBarAction,
  ],
  exports: [
    ToolbarComponent,
    PageBottomSectionComponent
  ],
})
export class SharedModule {
}
