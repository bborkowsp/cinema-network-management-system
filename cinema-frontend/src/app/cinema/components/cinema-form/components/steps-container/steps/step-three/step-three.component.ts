import {Component, Input} from '@angular/core';
import {FormArray, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.scss']
})
export class StepThreeComponent {
  @Input({required: true}) stepThreeFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createCinemaForm!: FormGroup;

  get contactDetailsArray(): FormArray {
    return this.createCinemaForm.get('contactDetails') as FormArray;
  }

}
