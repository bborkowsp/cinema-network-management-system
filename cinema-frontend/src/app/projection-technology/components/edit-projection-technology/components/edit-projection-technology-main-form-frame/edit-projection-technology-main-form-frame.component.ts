import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-edit-projection-technology-main-form-frame',
  templateUrl: './edit-projection-technology-main-form-frame.component.html',
  styleUrls: ['./edit-projection-technology-main-form-frame.component.scss']
})
export class EditProjectionTechnologyMainFormFrameComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  get technologyControl(): FormControl {
    return this.formGroup.get('technology') as FormControl;
  }

  get descriptionControl(): FormControl {
    return this.formGroup.get('description') as FormControl;
  }
}
