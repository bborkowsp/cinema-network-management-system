import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {EditProjectionTechnologyForm} from "./edit-projection-technology-form";
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectionTechnologyResponse} from "../../dtos/response/projection-technology.response";

@Component({
  selector: 'app-edit-projection-technology',
  templateUrl: './edit-projection-technology.component.html',
  styleUrls: ['./edit-projection-technology.component.scss']
})
export class EditProjectionTechnologyComponent implements OnInit {

  protected isLoading = false;
  protected projectionTechnologyForm!: EditProjectionTechnologyForm;
  private technology!: string;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly formBuilder: FormBuilder,) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const technology = params.get('technology');
      if (technology !== null) {
        this.technology = technology;
        this.setUpEditProjectionTechnologyForm();
      } else {
        this.goBack();
      }
    });
  }

  onSubmit() {
    const projectionTechnology = this.projectionTechnologyForm.updateProjectionTechnologyRequestFromForm();
    const updateProjectionTechnology$ = this.projectionTechnologyService.updateProjectionTechnology(this.technology, projectionTechnology);
    this.isLoading = true;
    updateProjectionTechnology$.subscribe({
      next: () => {
        this.isLoading = false;
        this.goBack();
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  handleCancelClicked() {
    this.goBack()
  }

  protected goBack(): void {
    this.router.navigate(['/projection-technologies']);
  }

  private setUpEditProjectionTechnologyForm() {
    this.projectionTechnologyForm = new EditProjectionTechnologyForm(this.formBuilder);
    this.loadProjectionTechnology();
  }

  private loadProjectionTechnology() {
    const projectionTechnology$ = this.projectionTechnologyService.getProjectionTechnology(this.technology);
    projectionTechnology$.subscribe({
      next: (projectionTechnology: ProjectionTechnologyResponse) => {
        this.projectionTechnologyForm.form.setValue({
          technology: projectionTechnology.technology,
          description: projectionTechnology.description,
        });
      },
      error: () => {
        this.goBack();
      }
    });
  }
}
