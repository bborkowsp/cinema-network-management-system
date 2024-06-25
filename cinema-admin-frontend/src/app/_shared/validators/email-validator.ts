import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function emailMatchValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const email = control.get('email');
    const repeatEmail = control.get('repeatEmail');
    return email && repeatEmail && email.value !== repeatEmail.value ? {'emailMismatch': true} : null;
  };
}
