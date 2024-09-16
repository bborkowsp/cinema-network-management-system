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
    const projectionTechnology = this.projectionTechnologyControl?.value;
    const language = this.languageControl?.value;

    if (projectionTechnology && language) {
      if (!this.isDuplicateVariant(projectionTechnology, language)) {
        const variantGroup = this.formBuilder.group({
          projectionTechnology: [projectionTechnology],
          language: [language]
        });
        this.variants.push(variantGroup);
      } else {
        console.warn('Duplicate variant detected');
      }
    }
  }

  removeVariant(index: number): void {
    this.variants.removeAt(index);
  }

  isDuplicateVariant(projectionTechnology: string, language: string): boolean {
    return this.variants.controls.some((control) => {
      const currentProjectionTechnology = control.get('projectionTechnology')?.value;
      const currentLanguage = control.get('language')?.value;
      return currentProjectionTechnology === projectionTechnology && currentLanguage === language;
    });
  }
}
