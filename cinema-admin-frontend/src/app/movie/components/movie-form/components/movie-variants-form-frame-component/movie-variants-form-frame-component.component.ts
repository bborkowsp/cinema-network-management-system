import {Component, Input} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {ProjectionTechnology} from "../../../../dtos/response/projection-technology";
import {Language} from "../../../../dtos/response/language";

@Component({
  selector: 'app-movie-variants-form-frame-component',
  templateUrl: './movie-variants-form-frame-component.component.html',
  styleUrl: './movie-variants-form-frame-component.component.scss'
})
export class MovieVariantsFormFrameComponentComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  projectionTechnologies = Object.values(ProjectionTechnology);
  languages = Object.values(Language);

  constructor(private formBuilder: FormBuilder) {
  }

  get projectionTechnologyControl() {
    return this.formGroup.get('projectionTechnology');
  }

  get languageControl() {
    return this.formGroup.get('language');
  }

  get variants() {
    return this.formGroup.get('variants') as FormArray;
  }

  addVariant(): void {
    if (this.projectionTechnologyControl?.value && this.languageControl?.value) {
      const variantGroup = this.formBuilder.group({
        projectionTechnology: [this.projectionTechnologyControl?.value],
        language: [this.languageControl?.value]
      });

      this.variants.push(variantGroup);
    }
  }

  removeVariant(index: number): void {
    this.variants.removeAt(index);
  }
}
