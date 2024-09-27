import {AbstractControl, ValidatorFn} from "@angular/forms";

export function passwordsMatchValidator(password: string, passwordConfirmation: string): ValidatorFn {
    return (formGroup: AbstractControl): { [key: string]: any } | null => {
        const newPassword = formGroup.get(password)?.value;
        const confirmPassword = formGroup.get(passwordConfirmation)?.value;
        return newPassword === confirmPassword ? null : {mismatch: true};
    };
}
