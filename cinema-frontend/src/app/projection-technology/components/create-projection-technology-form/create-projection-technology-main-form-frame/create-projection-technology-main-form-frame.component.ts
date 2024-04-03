import {Component, Input} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-projection-technology-main-form-frame',
  templateUrl: './create-projection-technology-main-form-frame.component.html',
  styleUrls: ['./create-projection-technology-main-form-frame.component.scss']
})
export class CreateProjectionTechnologyMainFormFrameComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input() technologyControl!: FormControl;
  @Input() descriptionControl!: FormControl;
}
