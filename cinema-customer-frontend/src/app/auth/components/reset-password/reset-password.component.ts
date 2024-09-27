import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent {
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email])
  });

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router
  ) {
  }

  get emailControl() {
    return this.form.get('email') as FormControl;
  }

  requestForResetPassword() {
    const email = this.getEmail();
    this.authService.requestForPasswordReset(email).subscribe({
      next: () => {
        this.router.navigate(['/check-email']);
      },
      error: () => {
        this.router.navigate(['/check-email']);
      }
    })
  }

  private getEmail() {
    return this.form.get('email')?.value as string;
  }
}
