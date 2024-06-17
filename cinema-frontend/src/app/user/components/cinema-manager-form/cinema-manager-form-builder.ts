import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";
import {CinemaManagerRequest} from "../../dtos/request/cinema-manager.request";

export class CinemaManagerFormBuilder {
  form: FormGroup;

  constructor(private readonly formBuilder: FormBuilder) {
    this.form = this.createForm();
  }

  public get mainFormGroup() {
    return this.form.get('main') as FormGroup;
  }

  fillFormWithCinemaManager(cinemaManager: CinemaManagerResponse) {
    this.form.setValue({
      main: {
        firstName: cinemaManager.firstName,
        lastName: cinemaManager.lastName,
        email: cinemaManager.email,
        managedCinemaName: cinemaManager.managedCinemaName
      }
    })
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      main: this.formBuilder.group({
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        managedCinemaName: ['', [Validators.required]]
      })
    });
  }

  cinemaManagerRequestFromForm() {
    let managedCinemaName = this.mainFormGroup.get('managedCinemaName')!.value;
    if (managedCinemaName == "0") {
      managedCinemaName = null;
    }

    return new CinemaManagerRequest(
      this.mainFormGroup.get('firstName')!.value,
      this.mainFormGroup.get('lastName')!.value,
      this.mainFormGroup.get('email')!.value,
      managedCinemaName
    );
  }
}
