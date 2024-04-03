import {NgModule} from "@angular/core";
import {CinemaListComponent} from "./components/cinema-list/cinema-list.component";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {SharedModule} from "../shared/shared.module";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatDialogModule} from "@angular/material/dialog";
import {MatMenuModule} from "@angular/material/menu";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {CinemaDetailsComponent} from "./components/cinema-details/cinema-details.component";
import {MatSortModule} from "@angular/material/sort";
import {CinemaFormComponent} from './components/cinema-form/cinema-form.component';
import {MatStepperModule} from "@angular/material/stepper";
import { AboutCinemaComponent } from './components/cinema-form/about-cinema/about-cinema.component';
import { AddressComponent } from './components/cinema-form/address/address.component';
import { ScreeningRoomComponent } from './components/cinema-form/screening-room/screening-room.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    SharedModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSortModule,
    MatInputModule,
    MatSelectModule,
    MatToolbarModule,
    MatIconModule,
    MatDialogModule,
    MatMenuModule,
    MatCheckboxModule,
    MatListModule,
    MatExpansionModule,
    MatStepperModule,
  ],
  declarations: [
    CinemaListComponent,
    CinemaDetailsComponent,
    CinemaFormComponent,
    AboutCinemaComponent,
    AddressComponent,
    ScreeningRoomComponent,
  ],
})

export class CinemaModule {
}