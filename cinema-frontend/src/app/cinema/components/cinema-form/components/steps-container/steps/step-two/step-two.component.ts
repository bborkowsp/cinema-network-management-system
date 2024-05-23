import {Component, Input} from '@angular/core';
import {FormArray, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.scss']
})
export class StepTwoComponent {
  @Input({required: true}) stepTwoFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createCinemaForm!: FormGroup;

  get screeningRoomsArray(): FormArray {
    return this.createCinemaForm.get('screeningRooms') as FormArray;
  }
}
