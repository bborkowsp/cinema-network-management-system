import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-information-form-frame-component',
  templateUrl: './information-form-frame-component.component.html',
  styleUrls: ['./information-form-frame-component.component.scss']
})
export class InformationFormFrameComponentComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  get durationControl(): FormControl {
    return this.formGroup.get('duration') as FormControl;
  }

  get releaseDateControl(): FormControl {
    return this.formGroup.get('releaseDate') as FormControl;
  }

  get descriptionControl(): FormControl {
    return this.formGroup.get('description') as FormControl;
  }

}
