import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CinemaManagerResponse} from "../../dtos/response/cinema-manager.response";
import {CreateCinemaManagerRequest} from "../../dtos/request/create-cinema-manager.request";
import {CreateCinemaNetworkManagerRequest} from "../../dtos/request/create-cinema-network-manager.request";
import {UserResponse} from "../../dtos/response/user.response";
import {UpdateCinemaNetworkManagerRequest} from "../../dtos/request/update-cinema-network-manager.request";
import {UpdateCinemaManagerRequest} from "../../dtos/request/update-cinema-manager.request";

export class UserFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private isEditMode: boolean
  ) {
    this.form = this.createForm();
  }
 
  public get mainFormGroup() {
    return this.form.get('main') as FormGroup;
  }

  fillFormWithCinemaManager(cinemaManager: CinemaManagerResponse) {
    const formValue: any = {
      firstName: cinemaManager.firstName,
      lastName: cinemaManager.lastName,
      email: cinemaManager.email,
      role: cinemaManager.role,
      managedCinemaName: cinemaManager.managedCinemaName
    };
    this.mainFormGroup.patchValue(formValue);
  }

  fillFormWithCinemaNetworkManager(cinemaNetworkManager: UserResponse) {
    const formValue: any = {
      firstName: cinemaNetworkManager.firstName,
      lastName: cinemaNetworkManager.lastName,
      email: cinemaNetworkManager.email,
      role: cinemaNetworkManager.role,
      managedCinemaName: null
    };
    this.mainFormGroup.patchValue(formValue);
  }

  createUserRequestFromForm() {
    let managedCinemaName = this.mainFormGroup.get('managedCinemaName')!.value;
    if (managedCinemaName == "0") {
      managedCinemaName = null;
    }
    if (managedCinemaName == "-1") {
      return new CreateCinemaNetworkManagerRequest(
        this.mainFormGroup.get('firstName')!.value,
        this.mainFormGroup.get('lastName')!.value,
        this.mainFormGroup.get('email')!.value,
        this.mainFormGroup.get('password')!.value
      )
    }

    return new CreateCinemaManagerRequest(
      this.mainFormGroup.get('firstName')!.value,
      this.mainFormGroup.get('lastName')!.value,
      this.mainFormGroup.get('email')!.value,
      this.mainFormGroup.get('password')!.value,
      managedCinemaName
    );
  }

  editUserRequestFromForm() {
    let managedCinemaName = this.mainFormGroup.get('managedCinemaName')!.value;
    if (managedCinemaName == "-1" || managedCinemaName == null) {
      return new UpdateCinemaNetworkManagerRequest(
        this.mainFormGroup.get('firstName')!.value,
        this.mainFormGroup.get('lastName')!.value,
        this.mainFormGroup.get('email')!.value,
        this.mainFormGroup.get('currentPassword')!.value,
        this.mainFormGroup.get('newPassword')!.value,
      )
    }

    if (managedCinemaName == "0") {
      managedCinemaName = null;
    }

    return new UpdateCinemaManagerRequest(
      this.mainFormGroup.get('firstName')!.value,
      this.mainFormGroup.get('lastName')!.value,
      this.mainFormGroup.get('email')!.value,
      this.mainFormGroup.get('currentPassword')!.value,
      this.mainFormGroup.get('newPassword')!.value,
      managedCinemaName
    );
  }

  private createForm(): FormGroup {
    const formGroupConfig: any = {
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      role: ['', [Validators.required]],
      managedCinemaName: ['']
    };

    if (this.isEditMode) {
      formGroupConfig.currentPassword = [''];
      formGroupConfig.newPassword = [''];
    } else {
      formGroupConfig.password = ['', [Validators.required]];
    }

    return this.formBuilder.group({
      main: this.formBuilder.group(formGroupConfig)
    });
  }
}
