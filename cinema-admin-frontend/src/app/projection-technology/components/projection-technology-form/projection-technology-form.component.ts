import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {ProjectionTechnologyFormBuilder} from "./projection-technology-form-builder";

@Component({
  selector: 'app-projection-technology-form',
  templateUrl: './projection-technology-form.component.html',
  styleUrls: ['./projection-technology-form.component.scss']
})
export class ProjectionTechnologyFormComponent implements OnInit {
  private static readonly GO_BACK_NAVIGATION_PATH = '/projection-technologies';
  isEditMode = false;
  isLoading = true;
  pageTitle !: string;
  projectionTechnologyFormBuilder !: ProjectionTechnologyFormBuilder;
  private technology !: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private projectionTechnologyService: ProjectionTechnologyService,
  ) {
  }

  ngOnInit() {
    const params = this.activatedRoute.snapshot.params;
    if (params['technology']) {
      this.isEditMode = true;
      this.technology = params['technology'];
      this.pageTitle = 'Edit Projection Technology';
      this.setUpEditProjectionTechnologyForm();
    } else {
      this.pageTitle = 'Add Projection Technology';
      this.setUpCreateProjectionTechnologyForm();
    }
  }

  protected onSubmit() {
    const projectionTechnology = this.isEditMode ?
      this.projectionTechnologyFormBuilder.getUpdateProjectionTechnologyRequestFromForm() :
      this.projectionTechnologyFormBuilder.getCreateProjectionTechnologyRequestFromForm();

    const projectionTechnologyRequest$ = this.isEditMode ?
      this.projectionTechnologyService.updateProjectionTechnology(this.technology, projectionTechnology) :
      this.projectionTechnologyService.createProjectionTechnology(projectionTechnology);

    this.isLoading = true;
    projectionTechnologyRequest$.subscribe({
      next: () => {
        this.isLoading = false;
        this.goBack();
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  protected handleCancelClicked() {
    this.goBack()
  }

  private setUpEditProjectionTechnologyForm() {
    this.projectionTechnologyFormBuilder = new ProjectionTechnologyFormBuilder(this.formBuilder);
    this.loadProjectionTechnology();
  }

  private setUpCreateProjectionTechnologyForm() {
    this.projectionTechnologyFormBuilder = new ProjectionTechnologyFormBuilder(this.formBuilder);
    this.isLoading = false;
  }

  private loadProjectionTechnology() {
    const projectionTechnology$ = this.projectionTechnologyService.getProjectionTechnology(this.technology);
    projectionTechnology$.subscribe({
      next: (projectionTechnology) => {
        this.projectionTechnologyFormBuilder.fillFormWithProjectionTechnology(projectionTechnology);
        this.isLoading = false;
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private goBack() {
    this.router.navigate([ProjectionTechnologyFormComponent.GO_BACK_NAVIGATION_PATH]);
  }
}
