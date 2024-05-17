import {NgModule} from "@angular/core";
import {ManageRepertoryComponent} from "./components/manage-repertory/manage-repertory.component";
import {SharedModule} from "../shared/shared.module";
import {MatTableModule} from "@angular/material/table";
import {AsyncPipe, DatePipe, KeyValuePipe, NgForOf, NgIf} from "@angular/common";

@NgModule({
  declarations: [
    ManageRepertoryComponent,

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
