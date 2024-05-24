import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {ScreeningService} from "../../../../services/screening.service";
import {ScreeningFormHelper} from "./screening-form-helper";

@Component({
  selector: 'app-screening-form',
  templateUrl: './screening-form.component.html',
  styleUrls: ['./screening-form.component.scss']
})
export class ScreeningFormComponent implements OnInit {
  protected isEditMode = false;
  protected isLoading = true;
  protected screeningFormHelper !: ScreeningFormHelper;
  protected pageTitle !: string;
  id !: number;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private screeningService: ScreeningService,
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

  private setUpEditScreeningForm() {
    this.screeningFormHelper = new ScreeningFormHelper(this.formBuilder);
    this.loadScreening();
  }

  private setUpScreeningForm() {
    this.screeningFormHelper = new ScreeningFormHelper(this.formBuilder);
    this.isLoading = false;
  }

  private loadScreening() {
    const screening$ = this.screeningService.getScreening(this.activatedRoute.snapshot.params['id']);
    screening$.subscribe({
      next: (screening) => {
        this.screeningFormHelper.fillFormWithScreening(screening);
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

  onSubmit() {
    const screening = this.screeningFormHelper.screeningRequestFromForm();
    const screening$ = this.isEditMode ?
      this.screeningService.updateScreening(this.id, screening) :
      this.screeningService.createScreening(screening);

    this.isLoading = true;
    screening$.subscribe({
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
}