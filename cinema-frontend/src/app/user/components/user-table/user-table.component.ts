import {Component, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../auth/services/auth.service";
import {UserResponse} from "../../dtos/response/user.response";
import {CinemaManagerTableResponse} from "../../dtos/response/cinema-manager-table.response";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent {
  displayedColumns = ['options', 'firstName', 'lastName', 'email'];
  users$!: Observable<UserResponse[]>;
  cinemaManagers$!: Observable<CinemaManagerTableResponse[]>;
  datasource !: any;
  dataLength = 0;
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected isLoading = true;
  protected pageTitle = '';

  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
    authService: AuthService,
  ) {
    const userRole = authService.getUserRole();
    console.log(userRole);
    if (userRole === 'ADMIN') {
      this.users$ = this.getAllUsers();
      this.datasource = this.users$;
      this.pageTitle = 'All users'
      this.displayedColumns.push('role')
    } else {
      this.cinemaManagers$ = this.getCinemaManagers();
      this.datasource = this.cinemaManagers$;
      this.pageTitle = 'Cinema managers'
      this.displayedColumns.push('managedCinema')
    }
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    if (this.users$) {
      this.users$ = this.getAllUsers();
    } else {
      this.cinemaManagers$ = this.getCinemaManagers();
    }
  }

  handleEdit(cinemaNetworkManager: CinemaManagerResponse): void {
    const url = `users/edit/${cinemaNetworkManager.email}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(cinemaNetworkManager: any): void {

  }

  handleShowDetails(cinemaNetworkManager: CinemaManagerResponse): void {
    const url = `cinema-managers/details/${cinemaNetworkManager.email}`;
    this.router.navigateByUrl(url);
  }

  private getCinemaManagers(): Observable<CinemaManagerTableResponse[]> {
    return this.userService.getCinemaManagers(this.paginatorRequestParams).pipe(
      tap({
        next: (cinemaManagerPage) => {
          console.log("AA");
          this.dataLength = cinemaManagerPage.totalElements;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((cinemaManagerPage) => cinemaManagerPage.content),
    );
  }

  private getAllUsers(): Observable<UserResponse[]> {
    return this.userService.getUsers(this.paginatorRequestParams).pipe(
      tap({
        next: (userPage) => {
          this.dataLength = userPage.totalElements;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((userPage) => userPage.content),
    );
  }
}

