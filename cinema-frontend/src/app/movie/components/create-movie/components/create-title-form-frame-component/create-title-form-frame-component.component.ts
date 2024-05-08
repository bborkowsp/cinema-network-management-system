import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-title-form-frame-component',
  templateUrl: './create-title-form-frame-component.component.html',
  styleUrls: ['./create-title-form-frame-component.component.scss']
})
export class CreateTitleFormFrameComponentComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  get titleControl(): FormControl {
    return this.formGroup.get('title') as FormControl;
  }

  get originalTitleControl(): FormControl {
    return this.formGroup.get('originalTitle') as FormControl;
  }
}
