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
    const email = this.form.value.email as string;
    this.authService.requestForPasswordReset(email).subscribe({
      next: () => {
        this.router.navigate(['/check-email']);
      },
      error: () => {
        this.router.navigate(['/check-email']);
      }
    })
  }
}
