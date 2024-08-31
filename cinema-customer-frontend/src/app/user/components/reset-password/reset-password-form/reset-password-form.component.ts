import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../../auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ResetPasswordRequest} from "../../../dtos/request/reset-password.request";

@Component({
  selector: 'app-reset-password-form',
  templateUrl: './reset-password-form.component.html',
  styleUrls: ['./reset-password-form.component.scss']
})
export class ResetPasswordFormComponent {
  passwordControl = new FormControl('', [Validators.required]);
  form = new FormGroup({
    password: this.passwordControl,
  });

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly route: ActivatedRoute,
  ) {
  }

  submit() {
    const email = this.form.value.password as string;
    const token = this.route.snapshot.queryParamMap.get('token') as string;
    this.authService.resetPassword(new ResetPasswordRequest(email, token)).subscribe({
      next: () => {
        this.router.navigate(['/check-email']);
      },
      error: () => {
        this.router.navigate(['/check-email']);
      }
    })
  }
}
