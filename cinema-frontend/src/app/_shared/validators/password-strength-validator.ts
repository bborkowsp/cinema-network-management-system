import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function createPasswordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {

    const value = control.value;

    if (!value) {
      return null;
    }

    const hasUpperCase = /[A-Z]+/.test(value); // at least one uppercase

    const hasLowerCase = /[a-z]+/.test(value); // at least one lowercase

    const hasNumeric = /[0-9]+/.test(value); // at least one number

    const passwordValid = hasUpperCase && hasLowerCase && hasNumeric;

    return !passwordValid ? {passwordStrength: true} : null;
  }
}
