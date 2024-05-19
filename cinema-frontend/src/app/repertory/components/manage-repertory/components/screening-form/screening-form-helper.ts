import {ScreeningResponse} from "../../../../dtos/screening.response";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

export class ScreeningFormHelper {
  form: FormGroup;

  public get mainFormGroup() {
    return this.form.get('main') as FormGroup;
  }

  constructor(private readonly formBuilder: FormBuilder) {
    this.form = this.createForm();
  }

  fillFormWithScreening(screening: ScreeningResponse) {

  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      main: this.formBuilder.group({
        movieTitle: ['', [Validators.required]],
        startTime: ['', [Validators.required]],
        endTime: ['', [Validators.required]],
        screeningRoom: ['', [Validators.required]],
      })
    });
  }
}
