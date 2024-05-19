import {ScreeningResponse} from "../../../../dtos/screening.response";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ScreeningRequest} from "../../../../dtos/screening.request";

export class ScreeningFormHelper {
  form: FormGroup;

  public get mainFormGroup() {
    return this.form.get('main') as FormGroup;
  }

  constructor(private readonly formBuilder: FormBuilder) {
    this.form = this.createForm();
  }

  fillFormWithScreening(screening: ScreeningResponse) {
    this.form.setValue({
      main: {
        movieTitle: screening.movie.title,
        startTime: screening.startTime,
        endTime: screening.endTime,
        screeningRoom: screening.screeningRoom.name
      }
    })
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

  screeningRequestFromForm(): ScreeningRequest {
    return new ScreeningRequest(
      this.mainFormGroup.get('movieTitle')!.value,
      this.mainFormGroup.get('startTime')!.value,
      this.mainFormGroup.get('endTime')!.value,
      this.mainFormGroup.get('screeningRoom')!.value
    )
  }
}
