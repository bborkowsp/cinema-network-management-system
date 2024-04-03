import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";

@Component({
  selector: 'app-cinema-form',
  templateUrl: './cinema-form.component.html',
  styleUrls: ['./cinema-form.component.scss']
})
export class CinemaFormComponent {

  aboutCinemaFormGroup = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.MAX_INPUT_LENGTH
      )]),
    description: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.MAX_INPUT_LENGTH)
    ]),
  });

  screeningRoomsFormGroup = new FormGroup({
    screeningRoomName: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.MAX_INPUT_LENGTH)
    ]),
  });

  onSubmit() {
    console.log(this.aboutCinemaFormGroup.value);
  }

  checkInvalid() {
    console.log(this.aboutCinemaFormGroup.invalid, this.aboutCinemaFormGroup);
  }

}
