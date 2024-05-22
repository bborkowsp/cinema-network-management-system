import {Component, Input} from '@angular/core';
import {FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.scss']
})
export class StepOneComponent {
  @Input({required: true}) stepOneFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createCinemaForm!: FormGroup;

}
