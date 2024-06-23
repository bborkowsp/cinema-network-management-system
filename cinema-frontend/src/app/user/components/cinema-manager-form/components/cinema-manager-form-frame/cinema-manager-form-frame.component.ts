import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {Observable} from "rxjs";

@Component({
  selector: 'app-cinema-manager-form-frame',
  templateUrl: './cinema-manager-form-frame.component.html',
  styleUrls: ['./cinema-manager-form-frame.component.scss']
})
export class CinemaManagerFormFrameComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  @Input({required: true}) cinemaNames!: Observable<string[]>;
  @Input({required: true}) isEditMode: boolean = false;
  hidePassword: boolean = true;

  ngOnInit() {
    this.configurePasswordControl();
  }

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

  get managedCinemaNameControl(): FormControl {
    return this.formGroup.get('managedCinemaName') as FormControl;
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
}
