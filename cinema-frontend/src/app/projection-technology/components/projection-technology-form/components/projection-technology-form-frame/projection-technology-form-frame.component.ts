import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-projection-technology-form-frame',
  templateUrl: './projection-technology-form-frame.component.html',
  styleUrls: ['./projection-technology-form-frame.component.scss']
})
export class ProjectionTechnologyFormFrameComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  get technologyControl() {
    return this.formGroup.get('technology') as FormControl;
  }

  get descriptionControl() {
    return this.formGroup.get('description') as FormControl;
  }
}
