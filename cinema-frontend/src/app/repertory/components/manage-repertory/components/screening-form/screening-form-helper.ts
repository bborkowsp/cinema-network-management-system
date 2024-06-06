import {ScreeningResponse} from "../../../../dtos/screening.response";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ScreeningRequest} from "../../../../dtos/screening.request";
import {AuthService} from "../../../../../user/services/auth.service";

export class ScreeningFormHelper {
  form: FormGroup;

  public get mainFormGroup() {
    return this.form.get('main') as FormGroup;
  }

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly authService: AuthService
  ) {
    this.form = this.createForm();
  }

  fillFormWithScreening(screening: ScreeningResponse) {
    this.form.setValue({
      main: {
        movieTitle: screening.movie.title,
        startTime: this.formatDate(screening.startTime),
        endTime: this.formatDate(screening.endTime),
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
    const email = this.authService.getLoggedInUserEmail();
    return new ScreeningRequest(
      this.mainFormGroup.get('movieTitle')!.value,
      this.mainFormGroup.get('startTime')!.value,
      this.mainFormGroup.get('endTime')!.value,
      this.mainFormGroup.get('screeningRoom')!.value,
      email
    )
  }

  formatDate(date: Date): string {
    const dat2e = new Date(date);
    const year = dat2e.getFullYear();
    const month = ('0' + (dat2e.getMonth() + 1)).slice(-2);
    const day = ('0' + dat2e.getDate()).slice(-2);
    const hours = ('0' + dat2e.getHours()).slice(-2);
    const minutes = ('0' + dat2e.getMinutes()).slice(-2);

    // Return date in 'YYYY-MM-DDTHH:MM' format
    return `${year}-${month}-${day}T${hours}:${minutes}`;
  }


}
