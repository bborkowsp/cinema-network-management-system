import {NgModule} from "@angular/core";
import {ManageRepertoryComponent} from "./components/manage-repertory/manage-repertory.component";
import {SharedModule} from "../_shared/shared.module";
import {MatTableModule} from "@angular/material/table";
import {AsyncPipe, DatePipe, KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {ScreeningFormComponent} from './components/manage-repertory/components/screening-form/screening-form.component';
import {MatIconModule} from "@angular/material/icon";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {
  RepertoryTableComponent
} from "./components/manage-repertory/components/repertory-table/repertory-table.component";
import {MatSelect} from "@angular/material/select";
import {
  ScreeningFormFieldsComponent
} from "./components/manage-repertory/components/screening-form/components/screening-form-fields/screening-form-fields.component";

@NgModule({
  declarations: [
    ManageRepertoryComponent,
    RepertoryTableComponent,
    ScreeningFormComponent,
    ScreeningFormFieldsComponent,
  ],
  imports: [
    SharedModule,
    MatTableModule,
    DatePipe,
    NgIf,
    NgForOf,
    KeyValuePipe,
    AsyncPipe,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatAutocompleteModule,
    MatSelect
  ],
})
export class RepertoryModule {
}
