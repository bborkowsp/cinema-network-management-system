import {Component, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {UserResponse} from "../../dtos/response/user.response";

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent {
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  displayedColumns = ['options', 'firstName', 'lastName', 'email', 'role'];
  users$!: Observable<UserResponse[]>;
  dataLength = 0;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  isLoading = true;

  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
  ) {
    this.users$ = this.getAllUsers();
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.users$ = this.getAllUsers();
  }

  handleEdit(userResponse: UserResponse): void {
    const url = `users/edit/${userResponse.email}+${userResponse.role}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(userResponse: UserResponse): void {
    this.userService.deleteUser(userResponse.email).subscribe(() => {
      this.users$ = this.getAllUsers();
    });
  }

  handleShowDetails(userResponse: UserResponse): void {
    console.log("Not implemented yet")
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

