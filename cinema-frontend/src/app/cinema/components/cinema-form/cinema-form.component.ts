import {Component, OnInit} from '@angular/core';
import {CinemaFormBuilder} from "./cinema-form-builder";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {CinemaService} from "../../services/cinema.service";

@Component({
  selector: 'app-cinema-form',
  templateUrl: './cinema-form.component.html',
  styleUrls: ['./cinema-form.component.scss']
})
export class CinemaFormComponent implements OnInit {
  protected isEditMode = false;
  protected isLoading = true;
  protected pageTitle !: string;
  protected cinemaFormBuilder !: CinemaFormBuilder;
  private static readonly GO_BACK_NAVIGATION_PATH = '/cinemas';
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
      this.pageTitle = 'Edit Projection Technology';
      this.setUpEditCinemaForm();
    } else {
      this.pageTitle = 'Add Projection Technology';
      this.setUpCreateCinemaForm();
    }
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

  protected onSubmit() {
    const cinema = this.isEditMode ?
      this.cinemaFormBuilder.getUpdateCinemaRequestFromForm() :
      this.cinemaFormBuilder.getCreateCinemaRequestFromForm();

    const cinemaRequest$ = this.isEditMode ?
      this.cinemaService.updateCinema(this.cinemaName, cinema) :
      this.cinemaService.createCinema(cinema);

    this.isLoading = true;
    cinemaRequest$.subscribe({
      next: () => {
        this.isLoading = false;
        this.goBack();
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  protected handleCancelClicked() {
    this.goBack();
  }

  private goBack() {
    this.router.navigate([CinemaFormComponent.GO_BACK_NAVIGATION_PATH]);
  }
}
