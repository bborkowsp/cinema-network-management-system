import {Component} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {CinemaManagerTableResponse} from "../../dtos/response/cinema-manager-table.response";
import {TableColumn} from "../../../_shared/components/generic-table/generic-table.component";

@Component({
  selector: 'app-cinema-manager-table',
  templateUrl: './cinema-manager-table.component.html',
  styleUrls: ['./cinema-manager-table.component.scss']
})
export class CinemaManagerTableComponent {
  protected isLoading = true;
  protected dataLength = 0;
  protected cinemaManagers: CinemaManagerTableResponse[] = [];
  protected paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected displayedColumns: TableColumn[] = [
    {columnDefinition: 'options', header: 'Options', isOptionsColumn: true},
    {columnDefinition: 'firstName', header: 'First Name'},
    {columnDefinition: 'lastName', header: 'Last Name'},
    {columnDefinition: 'email', header: 'Email'},
    {columnDefinition: 'managedCinema', header: 'Managed Cinema', nestedValue: 'name'},
  ]

  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
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

  handleEdit(cinemaManager: CinemaManagerTableResponse): void {
    const url = `cinema-managers/edit/${cinemaManager.email}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(cinemaManager: CinemaManagerTableResponse): void {
    this.userService.deleteCinemaManager(cinemaManager.email).subscribe({
      next: () => (this.getData().subscribe()),
    });
  }

  handleShowDetails(cinemaManager: CinemaManagerTableResponse): void {
    console.log("Not implemented yet")
  }

  private getData(): Observable<CinemaManagerTableResponse[]> {
    return this.userService.getCinemaManagers(this.paginatorRequestParams).pipe(
      tap({
        next: (cinemaManagerPage) => {
          this.dataLength = cinemaManagerPage.totalElements;
          this.cinemaManagers = cinemaManagerPage.content;
          console.log(this.cinemaManagers);
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((cinemaManagerPage) => cinemaManagerPage.content),
    );
  }
}
