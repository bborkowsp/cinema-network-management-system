import {Component, Input} from '@angular/core';
import {CinemaFormBuilder} from "../../cinema-form-builder";
import {FormGroup, FormGroupDirective} from "@angular/forms";

@Component({
  selector: 'app-steps-container',
  templateUrl: './steps-container.component.html',
  styleUrls: ['./steps-container.component.scss']
})
export class StepsContainerComponent {
  @Input({required: true}) cinemaFormBuilder!: CinemaFormBuilder;
  @Input({required: true}) cinemaForm!: FormGroupDirective;

  public get stepOneFormGroup() {
    return this.cinemaFormBuilder.form.get('stepOne') as FormGroup;
  }
 
  public get stepTwoFormGroup() {
    return this.cinemaFormBuilder.form.get('stepTwo') as FormGroup;
  }

  public get stepThreeFormGroup() {
    return this.cinemaFormBuilder.form.get('stepThree') as FormGroup;
  }

  public get stepFourFormGroup() {
    return this.cinemaFormBuilder.form.get('stepFour') as FormGroup;
  }
}
