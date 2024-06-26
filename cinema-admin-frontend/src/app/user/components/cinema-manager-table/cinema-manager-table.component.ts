import {Component, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {CinemaManagerTableResponse} from "../../dtos/response/cinema-manager-table.response";

@Component({
  selector: 'app-cinema-manager-table',
  templateUrl: './cinema-manager-table.component.html',
  styleUrls: ['./cinema-manager-table.component.scss']
})
export class CinemaManagerTableComponent {
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  displayedColumns = ['options', 'firstName', 'lastName', 'email', 'managedCinema'];
  cinemaManagers$!: Observable<CinemaManagerTableResponse[]>;
  dataLength = 0;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  isLoading = true;

  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
  ) {
    this.cinemaManagers$ = this.getData();
  }
 
  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.cinemaManagers$ = this.getData();
  }

  handleEdit(cinemaManager: CinemaManagerTableResponse): void {
    const url = `cinema-managers/edit/${cinemaManager.email}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(cinemaManager: CinemaManagerTableResponse): void {
    this.userService.deleteCinemaManager(cinemaManager.email).subscribe({
      next: () => (this.cinemaManagers$ = this.getData()),
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
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((cinemaManagerPage) => cinemaManagerPage.content),
    );
  }
}
