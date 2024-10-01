import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-production-details-form-fields-component',
  templateUrl: './production-details-form-fields.component.html',
  styleUrls: ['./production-details-form-fields.component.scss']
})
export class ProductionDetailsFormFieldsComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  get worldPremierDateControl(): FormControl {
    return this.formGroup.get('worldPremiereDate') as FormControl;
  }

  get directorControl(): FormControl {
    return this.formGroup.get('director') as FormControl;
  }

  get actorsControl(): FormControl {
    return this.formGroup.get('actors') as FormControl;
  }

  get originalLanguagesControl(): FormControl {
    return this.formGroup.get('originalLanguages') as FormControl;
  }

  get productionCountriesControl(): FormControl {
    return this.formGroup.get('productionCountries') as FormControl;
  }
}
