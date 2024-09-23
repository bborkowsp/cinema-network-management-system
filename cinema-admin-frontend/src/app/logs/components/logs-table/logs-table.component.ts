import {Component, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {map, Observable, tap} from "rxjs";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {LogResponse} from "../../dto/log.response";
import {LogsService} from "../../services/logs.service";

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

  constructor(
    private readonly logsService: LogsService,
  ) {
    this.logs$ = this.getData();
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.logs$ = this.getData();
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

