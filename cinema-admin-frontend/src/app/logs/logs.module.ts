import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable,
  MatTableModule
} from "@angular/material/table";
import {SharedModule} from "../_shared/shared.module";
import {LogsTableComponent} from "./components/logs-table/logs-table.component";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSelectModule} from "@angular/material/select";
import {MatNativeDateModule} from "@angular/material/core";
import {MatList, MatListItem} from "@angular/material/list";
import {MatInput} from "@angular/material/input";
import {MatSort} from "@angular/material/sort";

@NgModule({
  declarations: [
    LogsTableComponent
  ],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatPaginator,
    MatRow,
    MatRowDef,
    MatTable,
    SharedModule,
    MatHeaderCellDef,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatSelectModule,
    MatNativeDateModule,
    MatListItem,
    MatList,
    MatInput,
    MatSort,
  ]
})
export class LogsModule {
}
