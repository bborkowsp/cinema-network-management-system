import {Component, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {map, Observable, tap} from "rxjs";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {LogResponse} from "../../dto/log.response";
import {LogsService} from "../../services/logs.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-logs-table',
  templateUrl: './logs-table.component.html',
  styleUrls: ['./logs-table.component.scss']
})
export class LogsTableComponent {
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  displayedColumns = ['timestamp', 'level', 'logger', 'message'];
  logs$!: Observable<LogResponse[]>;
  dataLength = 0;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  isLoading = true;
  dataSource: MatTableDataSource<LogResponse>;

  constructor(
    private readonly logsService: LogsService,
  ) {
    this.logs$ = this.getData();
    this.dataSource = new MatTableDataSource();
    this.logs$.subscribe(logs => {
      this.dataSource.data = logs;
      this.dataLength = logs.length;
      this.isLoading = false;
    });
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.logs$ = this.getData();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  private getData(): Observable<LogResponse[]> {
    return this.logsService.getLogs(this.paginatorRequestParams).pipe(
      tap({
        next: (moviePage) => {
          this.dataLength = moviePage.totalElements;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((logPage) => logPage.content),
    );
  }
}

