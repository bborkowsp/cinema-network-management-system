import {Component, OnInit} from '@angular/core';
import {Observable, tap} from "rxjs";
import {CinemaManagerFormBuilder} from "./cinema-manager-form-builder";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {CinemaService} from "../../../cinema/services/cinema.service";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";
import {UpdateCinemaManagerRequest} from "../../dtos/request/update-cinema-manager.request";

@Component({
  selector: 'app-cinema-manager-form',
  templateUrl: './cinema-manager-form.component.html',
  styleUrls: ['./cinema-manager-form.component.scss']
})
export class CinemaManagerFormComponent implements OnInit {
  isEditMode = false;
  isLoading = true;
  cinemaManagerFormBuilder !: CinemaManagerFormBuilder;
  pageTitle !: string;
  cinemaNames!: Observable<string[]>
  protected readonly onsubmit = onsubmit;
  private cinemaManagerEmail !: string;

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
    if (params['email']) {
      this.isEditMode = true;
      this.cinemaManagerEmail = params['email'];
      this.pageTitle = 'Edit Cinema Manager';
      this.setUpEditCinemaManagerForm();
    } else {
      this.pageTitle = 'Add Cinema Manager';
      this.setUpCinemaManagerForm();
    }
  }

  onSubmit() {
    const cinemaManager = this.cinemaManagerFormBuilder.cinemaManagerRequestFromForm();
    let cinemaManager$;
    if (cinemaManager instanceof UpdateCinemaManagerRequest) {
      cinemaManager$ = this.userService.updateCinemaManager(this.cinemaManagerEmail, cinemaManager);
    } else {
      cinemaManager$ = this.userService.createCinemaManager(cinemaManager);
    }
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

  handleCancelClicked() {
    this.goBack();
  }

  private setUpEditCinemaManagerForm() {
    this.cinemaManagerFormBuilder = new CinemaManagerFormBuilder(this.formBuilder, this.isEditMode);
    this.loadCinemaManager();
  }

  private setUpCinemaManagerForm() {
    this.cinemaManagerFormBuilder = new CinemaManagerFormBuilder(this.formBuilder, this.isEditMode);
    this.isLoading = false;
  }

  private loadCinemaManager() {
    const cinemaManager$ = this.userService.getCinemaManager(this.cinemaManagerEmail);
    cinemaManager$.subscribe({
      next: (cinemaManager: CinemaManagerResponse) => {
        this.cinemaManagerFormBuilder.fillFormWithCinemaManager(cinemaManager);
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private goBack() {
    this.router.navigate(['/cinema-managers']);
  }

  private loadCinemaNames(): Observable<string[]> {
    this.isLoading = true;
    return this.cinemaService.getAllCinemaNames().pipe(
      tap(() => this.isLoading = false)
    );
  }
}
