import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ScaffoldComponent} from './scaffold.component';
import {ToolbarComponent} from './components/toolbar/toolbar.component';
import {DrawerComponent} from './components/drawer/drawer.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {RouterLink} from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {SharedModule} from "../shared/shared.module";
import { NavListComponent } from './components/nav-list/nav-list.component';

@NgModule({
  declarations: [
    ScaffoldComponent,
    ToolbarComponent,
    DrawerComponent,
    NavListComponent,
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
    SharedModule,
  ],
  exports: [ScaffoldComponent],
})
export class ScaffoldModule {
}
