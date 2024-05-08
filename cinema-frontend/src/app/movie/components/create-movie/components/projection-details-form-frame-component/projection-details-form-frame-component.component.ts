import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {ProjectionTechnologyService} from "../../../../../projection-technology/services/projection-technology.service";
import {map, Observable} from "rxjs";
import {
  ProjectionTechnologyResponse
} from "../../../../../projection-technology/dtos/response/projection-technology.response";

@Component({
  selector: 'app-projection-details-form-frame-component',
  templateUrl: './projection-details-form-frame-component.component.html',
  styleUrls: ['./projection-details-form-frame-component.component.scss']
})
export class ProjectionDetailsFormFrameComponentComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  subtitlesAndSoundOptions: string[] = ['Subtitles', 'Dubbing', 'Voice Over', 'Original Language'];
  projectionTechnologies!: Observable<string[]>;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
  ) {
  }

  get subtitlesAndSoundOptionsControl(): FormControl {
    return this.formGroup.get('subtitlesAndSoundOptions') as FormControl;
  }

  get projectionTechnologiesControl(): FormControl {
    return this.formGroup.get('projectionTechnologies') as FormControl;
  }

  ngOnInit() {
    this.projectionTechnologies = this.getOnlyTechnologyNames(this.projectionTechnologyService.getAllProjectionTechnologies());
  }

  private getOnlyTechnologyNames(allProjectionTechnologies: Observable<ProjectionTechnologyResponse[]>) {
    return allProjectionTechnologies.pipe(
      map((projectionTechnologies: ProjectionTechnologyResponse[]) => {
        return projectionTechnologies.map((projectionTechnology: ProjectionTechnologyResponse) => {
          return projectionTechnology.technology;
        });
      })
    );
  }
}
