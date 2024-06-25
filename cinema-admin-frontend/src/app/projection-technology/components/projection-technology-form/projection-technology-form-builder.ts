import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectionTechnologyResponse} from "../../dtos/response/projection-technology.response";
import {CreateProjectionTechnologyRequest} from "../../dtos/request/create-projection-technology.request";
import {UpdateProjectionTechnologyRequest} from "../../dtos/request/update-projection-technology.request";

export class ProjectionTechnologyFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.createForm();
  }

  get mainFormGroup() {
    return this.form.get('main') as FormGroup;
  }

  createForm(): FormGroup {
    return this.formBuilder.group({
      main: this.formBuilder.group({
        technology: ['', [Validators.required]],
        description: ['', [Validators.required]],
      })
    });
  }

  fillFormWithProjectionTechnology(projectionTechnology: ProjectionTechnologyResponse) {
    this.form.setValue({
      main: {
        technology: projectionTechnology.technology,
        description: projectionTechnology.description,
      }
    });
  }

  getCreateProjectionTechnologyRequestFromForm() {
    return new CreateProjectionTechnologyRequest(
      this.mainFormGroup.get('technology')!.value,
      this.mainFormGroup.get('description')!.value
    )
  }

  getUpdateProjectionTechnologyRequestFromForm(): UpdateProjectionTechnologyRequest {
    return new UpdateProjectionTechnologyRequest(
      this.mainFormGroup.get('technology')!.value,
      this.mainFormGroup.get('description')!.value
    )
  }
}
