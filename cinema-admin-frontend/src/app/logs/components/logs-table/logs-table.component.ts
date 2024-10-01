import {Component} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {map, Observable, tap} from "rxjs";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {LogResponse} from "../../dto/log.response";
import {LogsService} from "../../services/logs.service";
import {TableColumn} from "../../../_shared/components/generic-table/generic-table.component";

@Component({
  selector: 'app-logs-table',
  templateUrl: './logs-table.component.html',
  styleUrls: ['./logs-table.component.scss']
})
export class LogsTableComponent {
  protected isLoading = true;
  protected dataLength = 0;
  protected logs: LogResponse[] = [];
  protected displayedColumns: TableColumn[] = [
    {columnDefinition: 'timestamp', header: 'Timestamp'},
    {columnDefinition: 'level', header: 'Level'},
    {columnDefinition: 'logger', header: 'Logger'},
    {columnDefinition: 'message', header: 'Message'},
  ]
  protected paginatorRequestParams = new PaginatorRequestParams(0, 10);

  constructor(
    private readonly logsService: LogsService,
  ) {
    this.getData().subscribe();
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.getData().subscribe();
  }

  private getData(): Observable<LogResponse[]> {
    return this.logsService.getLogs(this.paginatorRequestParams).pipe(
      tap({
        next: (logPage) => {
          this.dataLength = logPage.totalElements;
          this.logs = logPage.content;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((logPage) => logPage.content),
    );
  }
}

