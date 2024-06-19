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

  protected hidePassword: boolean = true;

  constructor() {
  }

  ngOnInit() {
    if (!this.isEditMode) {
      this.formGroup.addControl('password', new FormControl('', [Validators.required]));
    } else {
      this.formGroup.removeControl('password');
    }

    this.roleControl.valueChanges.subscribe(value => {
      if (value === 'CINEMA_NETWORK_MANAGER') {
        this.managedCinemaNameControl.setValue(-1);
      } else if (value === 'CINEMA_MANAGER') {
        this.managedCinemaNameControl.setValue(0);
      }
    });
  }

  get emailControl(): FormControl {
    return this.formGroup.get('email') as FormControl;
  }

  get firstNameControl(): FormControl {
    return this.formGroup.get('firstName') as FormControl;
  }

  get lastNameControl(): FormControl {
    return this.formGroup.get('lastName') as FormControl;
  }

  get passwordControl(): FormControl {
    return this.formGroup.get('password') as FormControl;
  }

  get managedCinemaNameControl(): FormControl {
    return this.formGroup.get('managedCinemaName') as FormControl;
  }

  get roleControl(): FormControl {
    return this.formGroup.get('role') as FormControl;
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
}
