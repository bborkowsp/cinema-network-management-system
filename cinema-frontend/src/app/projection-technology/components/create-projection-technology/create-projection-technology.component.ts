import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {Router} from "@angular/router";
import {ProjectionTechnologyRequest} from "../../dtos/request/projection-technology.request";

@Component({
  selector: 'app-create-projection-technology',
  templateUrl: './create-projection-technology.component.html',
  styleUrls: ['./create-projection-technology.component.scss']
})
export class CreateProjectionTechnologyComponent {
  projectionTechnologyFormGroup = new FormGroup({
    technology: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.MAX_INPUT_LENGTH
      )]),
    description: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.MAX_INPUT_LENGTH)
    ]),
  });
  protected isLoading = false;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
    private readonly router: Router,
  ) {
  }

  protected onSubmit(): void {
    const projectionTechnology = this.createProjectionTechnologyRequestFromForm();

  }

  private createProjectionTechnologyRequestFromForm(): ProjectionTechnologyRequest {
    const technology = this.projectionTechnologyFormGroup.get('technology')!.value;
    const description = this.projectionTechnologyFormGroup.get('description')!.value;
    if (technology === null || description === null) {
      throw new Error('Technology or description is null.');
    }
    return new ProjectionTechnologyRequest(technology, description);
  }


}
