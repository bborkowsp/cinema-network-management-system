import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent {
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  form = new FormGroup({
    email: this.emailControl,
  });

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router
  ) {
  }

  submit() {
    const email = this.form.value as string;
    this.authService.resetPassword(email).subscribe({
      next: () => {
        this.router.navigate(['/check-email']);
      },
      error: () => {
        this.router.navigate(['/check-email']);
      }
    })
  }
}
