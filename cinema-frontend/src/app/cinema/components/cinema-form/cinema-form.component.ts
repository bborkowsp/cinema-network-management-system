import {Component, OnInit} from '@angular/core';
import {CinemaFormBuilder} from "./cinema-form-builder";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {CinemaService} from "../../services/cinema.service";
import {CreateCinemaRequest} from "../../dtos/request/create-cinema.request";
import {UpdateCinemaRequest} from "../../dtos/request/update-cinema.request";

@Component({
  selector: 'app-cinema-form',
  templateUrl: './cinema-form.component.html',
  styleUrls: ['./cinema-form.component.scss']
})
export class CinemaFormComponent implements OnInit {
  private static readonly GO_BACK_NAVIGATION_PATH = '/cinemas';
  isEditMode = false;
  isLoading = true;
  pageTitle !: string;
  cinemaFormBuilder !: CinemaFormBuilder;
  private cinemaName !: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private cinemaService: CinemaService,
  ) {
  }

  ngOnInit() {
    const params = this.activatedRoute.snapshot.params;
    if (params['name']) {
      this.isEditMode = true;
      this.cinemaName = params['name'];
      this.pageTitle = 'Edit Cinema';
      this.setUpEditCinemaForm();
    } else {
      this.pageTitle = 'Add Cinema';
      this.setUpCreateCinemaForm();
    }
  }

  protected onSubmit() {
    let cinemaRequestPromise: Promise<CreateCinemaRequest | UpdateCinemaRequest>;

    if (this.isEditMode) {
      cinemaRequestPromise = this.cinemaFormBuilder.getUpdateCinemaRequestFromForm();
    } else {
      cinemaRequestPromise = this.cinemaFormBuilder.getCreateCinemaRequestFromForm();
    }

    this.handleFormSubmission(cinemaRequestPromise);
  }

  protected handleCancelClicked() {
    this.goBack();
  }

  private setUpEditCinemaForm() {
    this.cinemaFormBuilder = new CinemaFormBuilder(this.formBuilder);
    this.loadCinema();
  }

  private setUpCreateCinemaForm() {
    this.cinemaFormBuilder = new CinemaFormBuilder(this.formBuilder);
    this.isLoading = false;
  }

  private loadCinema() {
    const cinema$ = this.cinemaService.getCinema(this.cinemaName);
    cinema$.subscribe({
      next: (cinema) => {
        this.cinemaFormBuilder.fillFormWithCinema(cinema);
        this.isLoading = false;
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private handleFormSubmission(cinemaRequestPromise: Promise<CreateCinemaRequest | UpdateCinemaRequest>) {
    this.isLoading = true;
    cinemaRequestPromise.then((cinemaRequest) => {
      let cinemaServiceObservable;
      if (this.isEditMode) {
        cinemaServiceObservable = this.cinemaService.updateCinema(this.cinemaName, cinemaRequest as UpdateCinemaRequest);
      } else {
        cinemaServiceObservable = this.cinemaService.createCinema(cinemaRequest as CreateCinemaRequest);
      }
      cinemaServiceObservable.subscribe({
        next: () => {
          this.isLoading = false;
          this.goBack();
        },
        error: () => {
          this.isLoading = false;
        }
      });
    }).catch(() => {
      this.isLoading = false;
    });
  }

  private goBack() {
    this.router.navigate([CinemaFormComponent.GO_BACK_NAVIGATION_PATH]);
  }
}
