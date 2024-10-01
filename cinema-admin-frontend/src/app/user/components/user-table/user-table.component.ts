import {Component} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {UserResponse} from "../../dtos/response/user.response";
import {TableColumn} from "../../../_shared/components/generic-table/generic-table.component";

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent {
  protected isLoading = true;
  protected dataLength = 0;
  protected users: UserResponse[] = [];
  protected paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected displayedColumns: TableColumn[] = [
    {columnDefinition: 'options', header: 'Options', isOptionsColumn: true},
    {columnDefinition: 'firstName', header: 'First Name'},
    {columnDefinition: 'lastName', header: 'Last Name'},
    {columnDefinition: 'email', header: 'Email'},
    {columnDefinition: 'role', header: 'Role'},
  ]

  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
  ) {
    this.getAllUsers().subscribe();
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.getAllUsers().subscribe();
  }

  handleEdit(userResponse: UserResponse): void {
    const url = `users/edit/${userResponse.email}+${userResponse.role}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(userResponse: UserResponse): void {
    this.userService.deleteUser(userResponse.email).subscribe(() => {
      this.getAllUsers().subscribe();
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
          this.users = userPage.content;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((userPage) => userPage.content),
    );
  }
}

