import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-form-frame',
  templateUrl: './user-form-frame.component.html',
  styleUrls: ['./user-form-frame.component.scss']
})
export class UserFormFrameComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  @Input({required: true}) cinemaNames!: Observable<string[]>;
  @Input({required: true}) isEditMode!: boolean;
  hidePassword: boolean = true;

  get firstNameControl(): FormControl {
    return this.formGroup.get('firstName') as FormControl;
  }

  get lastNameControl(): FormControl {
    return this.formGroup.get('lastName') as FormControl;
  }

  get emailControl(): FormControl {
    return this.formGroup.get('email') as FormControl;
  }

  get passwordControl(): FormControl {
    return this.formGroup.get('password') as FormControl;
  }

  get currentPasswordControl(): FormControl {
    return this.formGroup.get('currentPassword') as FormControl;
  }

  get newPasswordControl(): FormControl {
    return this.formGroup.get('newPassword') as FormControl;
  }

  get roleControl(): FormControl {
    return this.formGroup.get('role') as FormControl;
  }

  get managedCinemaNameControl(): FormControl {
    return this.formGroup.get('managedCinemaName') as FormControl;
  }

  ngOnInit() {
    this.configurePasswordControl();
    this.subscribeToRoleChanges();
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  private configurePasswordControl() {
    if (this.isEditMode) {
      this.formGroup.removeControl('password');
    } else {
      this.formGroup.addControl('password', new FormControl('', [Validators.required]));
    }
  }

  private subscribeToRoleChanges() {
    this.roleControl.valueChanges.subscribe(value => {
      this.updateManagedCinemaName(value);
    });
  }

  private updateManagedCinemaName(role: string) {
    if (role === 'ROLE_CINEMA_NETWORK_MANAGER') {
      this.managedCinemaNameControl.setValue(-1);
    } else if (role === 'ROLE_CINEMA_MANAGER') {
      this.managedCinemaNameControl.setValue(0);
    }
  }
}
