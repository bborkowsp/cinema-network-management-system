import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UpdateProjectionTechnologyRequest} from "../../dtos/request/update-projection-technology.request";

export class EditProjectionTechnologyForm {
  form: FormGroup;

  constructor(private readonly formBuilder: FormBuilder) {
    this.form = this.createForm();
  }

  updateProjectionTechnologyRequestFromForm() {
    return new UpdateProjectionTechnologyRequest(
      this.form.get('technology')!.value,
      this.form.get('description')!.value
    )
  }

  private createForm() {
    return this.formBuilder.group({
      technology: new FormControl('', [
        Validators.required,
        Validators.maxLength(FormValidatorLengths.PROJECTION_TECHNOLOGY_MAX_INPUT_LENGTH
        )]),
      description: new FormControl('', [
        Validators.required,
        Validators.maxLength(FormValidatorLengths.PROJECTION_TECHNOLOGY_MAX_DESCRIPTION_LENGTH)
      ]),
    });
  }
}
