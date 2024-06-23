import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";
import {CreateCinemaManagerRequest} from "../../dtos/request/create-cinema-manager.request";
import {UpdateCinemaManagerRequest} from "../../dtos/request/update-cinema-manager.request";

export class CinemaManagerFormBuilder {
  form: FormGroup;
  readonly isEditMode: boolean;

  constructor(private readonly formBuilder: FormBuilder, isEditMode: boolean) {
    this.form = this.createForm();
    this.isEditMode = isEditMode;
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
    const formGroupConfig: any = {
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      managedCinemaName: ['', [Validators.required]]
    };

    if (!this.isEditMode) {
      formGroupConfig.password = ['', [Validators.required]];
    }

    return this.formBuilder.group({
      main: this.formBuilder.group(formGroupConfig)
    });
  }


  cinemaManagerRequestFromForm() {
    let managedCinemaName = this.mainFormGroup.get('managedCinemaName')!.value;
    if (managedCinemaName == "0") {
      managedCinemaName = null;
    }

    if (this.isEditMode) {
      return new UpdateCinemaManagerRequest(
        this.mainFormGroup.get('firstName')!.value,
        this.mainFormGroup.get('lastName')!.value,
        this.mainFormGroup.get('email')!.value,
        '', '',
        this.mainFormGroup.get('managedCinemaName')!.value
      );
    }
    return new CreateCinemaManagerRequest(
      this.mainFormGroup.get('firstName')!.value,
      this.mainFormGroup.get('lastName')!.value,
      this.mainFormGroup.get('email')!.value,
      this.mainFormGroup.get('password')!.value,
      managedCinemaName
    );
  }
}
