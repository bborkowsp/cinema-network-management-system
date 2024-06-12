import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {FormBuilder} from "@angular/forms";
import {CinemaManagerFormHelper} from "./cinema-manager-form-helper";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";
import {CinemaService} from "../../../cinema/services/cinema.service";
import {Observable, tap} from "rxjs";

@Component({
  selector: 'app-cinema-manager-form',
  templateUrl: './cinema-manager-form.component.html',
  styleUrls: ['./cinema-manager-form.component.scss']
})
export class CinemaManagerFormComponent implements OnInit {
  protected isEditMode = false;
  protected isLoading = true;
  protected cinemaManagerFormHelper !: CinemaManagerFormHelper;
  protected pageTitle !: string;
  protected cinemaNames!: Observable<string[]>
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

  private setUpEditCinemaManagerForm() {
    this.cinemaManagerFormHelper = new CinemaManagerFormHelper(this.formBuilder);
    this.loadCinemaManager();
  }

  private setUpCinemaManagerForm() {
    this.cinemaManagerFormHelper = new CinemaManagerFormHelper(this.formBuilder);
    this.isLoading = false;
  }

  private loadCinemaManager() {
    const cinemaManager$ = this.userService.getCinemaManager(this.cinemaManagerEmail);
    cinemaManager$.subscribe({
      next: (cinemaManager: CinemaManagerResponse) => {
        this.cinemaManagerFormHelper.fillFormWithCinemaManager(cinemaManager);
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private goBack() {
    this.router.navigate(['/cinema-managers']);
  }

  protected readonly onsubmit = onsubmit;

  onSubmit() {
    const cinemaManager = this.cinemaManagerFormHelper.cinemaManagerRequestFromForm();
    const cinemaManager$ = this.isEditMode ?
      this.userService.updateCinemaManager(this.cinemaManagerEmail, cinemaManager) :
      this.userService.createCinemaManager(cinemaManager);

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

  private loadCinemaNames(): Observable<string[]> {
    this.isLoading = true;
    return this.cinemaService.getAllCinemaNames().pipe(
      tap(() => this.isLoading = false)
    );
  }
}
