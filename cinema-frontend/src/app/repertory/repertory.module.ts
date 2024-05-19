import {NgModule} from "@angular/core";
import {ManageRepertoryComponent} from "./components/manage-repertory/manage-repertory.component";
import {SharedModule} from "../shared/shared.module";
import {MatTableModule} from "@angular/material/table";
import {AsyncPipe, DatePipe, KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {
  RepertoryTableComponentComponent
} from "./components/manage-repertory/components/repertory-table-component/repertory-table-component.component";

@NgModule({
  declarations: [
    ManageRepertoryComponent,
    RepertoryTableComponentComponent
  ],
  imports: [
    SharedModule,
    MatTableModule,
    DatePipe,
    NgIf,
    NgForOf,
    KeyValuePipe,
    AsyncPipe
  ],
})
export class RepertoryModule {
}
