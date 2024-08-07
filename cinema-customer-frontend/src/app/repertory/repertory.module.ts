import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./components/repertory.component";
import {CommonModule} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import { ScreeningComponent } from './components/screening/screening.component';

@NgModule({
  declarations: [
    RepertoryComponent,
    ScreeningComponent,
  ],
  imports: [
    CommonModule,
    MatInputModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatListModule,
    MatButtonModule
  ],
})
export class RepertoryModule {
}
