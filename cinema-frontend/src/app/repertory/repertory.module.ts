import {NgModule} from "@angular/core";
import {ManageRepertoryComponent} from "./components/manage-repertory/manage-repertory.component";
import {
  RepertoryTableComponentComponent
} from './components/manage-repertory/components/repertory-table-component/repertory-table-component.component';
import {SharedModule} from "../shared/shared.module";

@NgModule({
  declarations: [
    ManageRepertoryComponent,
    RepertoryTableComponentComponent
  ],
  imports: [
    SharedModule
  ],
})
export class RepertoryModule {
}
