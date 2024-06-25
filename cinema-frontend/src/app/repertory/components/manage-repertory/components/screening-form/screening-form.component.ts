import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {ScreeningService} from "../../../../services/screening.service";
import {ScreeningFormBuilder} from "./screening-form-builder";
import {AuthService} from "../../../../../auth/services/auth.service";

@Component({
  selector: 'app-screening-form',
  templateUrl: './screening-form.component.html',
  styleUrls: ['./screening-form.component.scss']
})
export class ScreeningFormComponent implements OnInit {
  isEditMode = false;
  isLoading = true;
  screeningFormBuilder !: ScreeningFormBuilder;
  pageTitle !: string;
  id !: number;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private screeningService: ScreeningService,
    private readonly authService: AuthService
  ) {
  }

  ngOnInit() {
    const params = this.activatedRoute.snapshot.params;

    if (params['id']) {
      this.isEditMode = true;
      this.id = params['id'];
      this.pageTitle = 'Edit Screening';
      this.setUpEditScreeningForm();
    } else {
      this.pageTitle = 'Add Screening';
      this.setUpScreeningForm();
    }
  }

  onSubmit() {
    const screening = this.screeningFormBuilder.screeningRequestFromForm();
    this.isLoading = true;

    if (this.isEditMode) {
      this.screeningService.updateScreening(this.id, screening).subscribe({
        next: () => {
          this.isLoading = false;
          this.goBack();
        },
        error: () => {
          this.isLoading = false;
        }
      });
    } else {
      this.screeningService.createScreening(screening).subscribe({
        next: () => {
          this.isLoading = false;
          this.goBack();
        },
        error: () => {
          this.isLoading = false;
        }
      });
    }
  }

  handleCancelClicked() {
    this.goBack();
  }

  private setUpEditScreeningForm() {
    this.screeningFormBuilder = new ScreeningFormBuilder(this.formBuilder, this.authService);
    this.loadScreening();
  }

  private setUpScreeningForm() {
    this.screeningFormBuilder = new ScreeningFormBuilder(this.formBuilder, this.authService);
    this.isLoading = false;
  }

  private loadScreening() {
    const screening$ = this.screeningService.getScreening(this.activatedRoute.snapshot.params['id']);
    screening$.subscribe({
      next: (screening) => {
        this.screeningFormBuilder.fillFormWithScreening(screening);
        this.isLoading = false;
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private goBack() {
    this.router.navigate(['/repertory']);
  }
}
