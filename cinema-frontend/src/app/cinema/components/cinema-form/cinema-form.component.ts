import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import FormValidatorPatterns from "../../../shared/consts/form-validators-patterns";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cinema-form',
  templateUrl: './cinema-form.component.html',
  styleUrls: ['./cinema-form.component.scss']
})
export class CinemaFormComponent {

  aboutCinemaFormGroup = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH
      )]),
    description: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH)
    ]),
    streetAndBuildingNumber: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
      Validators.pattern(FormValidatorPatterns.STREET_AND_BUILDING_NUMBER)
    ]),
    city: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
    ]),
    postalCode: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
      Validators.pattern(FormValidatorPatterns.POSTAL_CODE)
    ]),
    country: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
    ]),
  });
  screeningRoomsFormGroup = new FormGroup({
    screeningRoomName: new FormControl('', [
      Validators.required,
      Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH)
    ]),
  });

  constructor(
    private router: Router,
  ) {
  }

  onSubmit() {
    if (this.aboutCinemaFormGroup.valid && this.screeningRoomsFormGroup.valid) {
      this.createCinema()
    }
  }

  createCinema() {
  }

  handleGoBackButtonAction() {
    this.router.navigateByUrl('/cinemas');
  }
}
