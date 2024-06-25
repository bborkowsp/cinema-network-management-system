import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {FormBuilder} from "@angular/forms";
import {UserFormBuilder} from "./user-form-builder";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";
import {CinemaService} from "../../../cinema/services/cinema.service";
import {Observable, tap} from "rxjs";
import {CreateCinemaManagerRequest} from "../../dtos/request/create-cinema-manager.request";
import {UserResponse} from "../../dtos/response/user.response";
import {UpdateCinemaManagerRequest} from "../../dtos/request/update-cinema-manager.request";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
  isEditMode = false;
  isLoading = true;
  userFormBuilder !: UserFormBuilder;
  pageTitle !: string;
  cinemaNames!: Observable<string[]>
  protected readonly onsubmit = onsubmit;
  private userEmail !: string;
  private role !: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private readonly cinemaService: CinemaService,
  ) {
  }

  ngOnInit() {
    const params = this.activatedRoute.snapshot.params;
    this.cinemaNames = this.loadCinemaNames();
    if (params['email+role']) {
      this.setUpEditUserPage(params['email+role']);
    } else {
      this.setUpCreateUserPage();
    }
  }

  handleCancelClicked() {
    this.goBack();
  }

  onSubmit() {
    if (this.isEditMode) {
      this.handleEditSubmit();
    } else {
      this.handleCreateSubmit();
    }
  }

  private loadCinemaNames(): Observable<string[]> {
    this.isLoading = true;
    return this.cinemaService.getAllCinemaNames().pipe(
      tap(() => this.isLoading = false)
    );
  }

  private setUpEditUserPage(emailAndRoleParam: string) {
    this.isEditMode = true;
    [this.userEmail, this.role] = emailAndRoleParam.split('+');
    if (this.role === 'CINEMA_NETWORK_MANAGER') {
      this.pageTitle = 'Edit Cinema Network Manager';
      this.isLoading = false;
    } else if (this.role === 'CINEMA_MANAGER') {
      this.pageTitle = 'Edit Cinema Manager';
    } else {
      this.pageTitle = 'Edit Admin';
      this.isLoading = false;
    }
    this.setUpEditUserForm();
  }

  private setUpCreateUserPage() {
    this.pageTitle = 'Add new User'
    this.setUpCreateUserForm();
  }

  private setUpEditUserForm() {
    this.userFormBuilder = new UserFormBuilder(this.formBuilder, this.isEditMode);
    if (this.role === 'CINEMA_MANAGER') {
      this.loadCinemaManager();
    } else {
      this.loadCinemaNetworkManager();
    }
  }

  private setUpCreateUserForm() {
    this.userFormBuilder = new UserFormBuilder(this.formBuilder, this.isEditMode);
    this.isLoading = false;
  }

  private loadCinemaManager() {
    const cinemaManager$ = this.userService.getCinemaManager(this.userEmail);
    cinemaManager$.subscribe({
      next: (cinemaManager: CinemaManagerResponse) => {
        this.userFormBuilder.fillFormWithCinemaManager(cinemaManager);
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private loadCinemaNetworkManager() {
    const cinemaNetworkManager$ = this.userService.getCinemaNetworkManager(this.userEmail);
    cinemaNetworkManager$.subscribe({
      next: (cinemaNetworkManager: UserResponse) => {
        this.userFormBuilder.fillFormWithCinemaNetworkManager(cinemaNetworkManager);
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private handleEditSubmit() {
    let editUserRequest$;
    const editUserRequest = this.userFormBuilder.editUserRequestFromForm();
    if (editUserRequest instanceof UpdateCinemaManagerRequest) {
      editUserRequest$ = this.userService.updateCinemaManager(this.userEmail, editUserRequest);
    } else {
      editUserRequest$ = this.userService.updateCinemaNetworkManager(this.userEmail, editUserRequest);
    }
    this.subscribeWithLoading(editUserRequest$);
  }

  private handleCreateSubmit() {
    let createUserRequest$;
    const createUserRequest = this.userFormBuilder.createUserRequestFromForm();
    if (createUserRequest instanceof CreateCinemaManagerRequest) {
      createUserRequest$ = this.userService.createCinemaManager(createUserRequest);
    } else {
      createUserRequest$ = this.userService.createCinemaNetworkManager(createUserRequest);
    }
    this.subscribeWithLoading(createUserRequest$);
  }

  private subscribeWithLoading(cinemaManager$: Observable<any>) {
    this.isLoading = true;
    cinemaManager$.subscribe({
      next: () => {
        this.isLoading = false;
        this.goBack();
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  private goBack() {
    this.router.navigate(['/users']);
  }
}
