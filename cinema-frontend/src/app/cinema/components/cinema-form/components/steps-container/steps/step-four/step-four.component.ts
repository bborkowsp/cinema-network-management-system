import {Component, Input} from '@angular/core';
import {FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-step-four',
  templateUrl: './step-four.component.html',
  styleUrls: ['./step-four.component.scss']
})
export class StepFourComponent {
  @Input({required: true}) stepFourFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createCinemaForm!: FormGroup;
}
