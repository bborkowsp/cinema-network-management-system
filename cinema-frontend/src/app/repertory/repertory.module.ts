import {NgModule} from "@angular/core";
import {ManageRepertoryComponent} from "./components/manage-repertory/manage-repertory.component";
import {SharedModule} from "../_shared/shared.module";
import {MatTableModule} from "@angular/material/table";
import {AsyncPipe, DatePipe, KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {
  RepertoryListComponentComponent
} from "./components/manage-repertory/components/repertory-list-component/repertory-list-component.component";
import {MatButtonModule} from "@angular/material/button";
import {ScreeningFormComponent} from './components/manage-repertory/components/screening-form/screening-form.component';
import {MatIconModule} from "@angular/material/icon";
import {
  ScreeningFormFrameComponent
} from './components/manage-repertory/components/screening-form/components/screening-form-frame/screening-form-frame.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatAutocompleteModule} from "@angular/material/autocomplete";

@NgModule({
  declarations: [
    ManageRepertoryComponent,
    RepertoryListComponentComponent,
    ScreeningFormComponent,
    ScreeningFormFrameComponent
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
    MatAutocompleteModule
  ],
})
export class RepertoryModule {
}
