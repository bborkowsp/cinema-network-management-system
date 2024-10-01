import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-title-form-frame-component',
  templateUrl: './create-title-form-fields.component.html',
  styleUrls: ['./create-title-form-fields.component.scss']
})
export class CreateTitleFormFieldsComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  get titleControl(): FormControl {
    return this.formGroup.get('title') as FormControl;
  }

  get originalTitleControl(): FormControl {
    return this.formGroup.get('originalTitle') as FormControl;
  }
}
