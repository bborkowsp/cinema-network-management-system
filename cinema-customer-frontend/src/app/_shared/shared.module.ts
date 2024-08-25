import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from "@angular/material/toolbar";
import {RouterLink} from "@angular/router";
import {MatListModule} from "@angular/material/list";
import {AccountComponent} from "./components/scaffold/components/account/account.component";
import {ToolbarComponent} from "./components/scaffold/components/toolbar/toolbar.component";
import {MatSidenavModule} from "@angular/material/sidenav";
import {PageBottomSectionComponent} from './components/page-bottom-section/page-bottom-section.component';


@NgModule({
  declarations: [
    AccountComponent,
    ToolbarComponent,
    PageBottomSectionComponent,
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
  ],
  exports: [
    ToolbarComponent,
    PageBottomSectionComponent
  ],
})
export class SharedModule {
}
