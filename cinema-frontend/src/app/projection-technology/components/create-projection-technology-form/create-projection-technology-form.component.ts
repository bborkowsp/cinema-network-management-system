import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {Router} from "@angular/router";
import {ProjectionTechnologyRequest} from "../../dtos/request/projection-technology.request";

@Component({
  selector: 'app-create-projection-technology-form',
  templateUrl: './create-projection-technology-form.component.html',
  styleUrls: ['./create-projection-technology-form.component.scss']
})
export class CreateProjectionTechnologyFormComponent {
  projectionTechnologyFormGroup = new FormGroup({
    technology: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.PROJECTION_TECHNOLOGY_MAX_INPUT_LENGTH
      )]),
    description: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.PROJECTION_TECHNOLOGY_MAX_DESCRIPTION_LENGTH)
    ]),
  });
  protected isLoading = false;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
    private readonly router: Router,
  ) {
  }

  handleCancelClicked() {
    this.goBack()
  }

  protected onSubmit(): void {
    const projectionTechnology = this.createProjectionTechnologyRequestFromForm();
    const createProjectionTechnology$ = this.projectionTechnologyService.createProjectionTechnology(projectionTechnology);
    this.isLoading = true;
    createProjectionTechnology$.subscribe({
      next: () => this.goBack(),
    });
  }

  private createProjectionTechnologyRequestFromForm(): ProjectionTechnologyRequest {
    const technology = this.projectionTechnologyFormGroup.get('technology')!.value;
    const description = this.projectionTechnologyFormGroup.get('description')!.value;
    if (technology === null || description === null) {
      throw new Error('Technology or description is null.');
    }
    return new ProjectionTechnologyRequest(technology, description);
  }

  private goBack() {
    this.router.navigate(['/projection-technologies']);
  }
}
