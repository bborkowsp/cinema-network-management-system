import {Component, Input} from '@angular/core';
import {FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.scss']
})
export class StepTwoComponent {
  @Input({required: true}) stepOneFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createCinemaForm!: FormGroup;

}
