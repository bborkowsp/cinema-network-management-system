import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {CinemaService} from "../../../../../cinema/services/cinema.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-cinema-manager-form-frame',
  templateUrl: './cinema-manager-form-frame.component.html',
  styleUrls: ['./cinema-manager-form-frame.component.scss']
})
export class CinemaManagerFormFrameComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  cinemaNames!: Observable<string[]>;

  constructor(
    private readonly cinemaService: CinemaService,
  ) {
  }

  ngOnInit() {
    this.cinemaNames = this.cinemaService.getAllCinemaNames();

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

  get managedCinemaNameControl(): FormControl {
    return this.formGroup.get('managedCinemaName') as FormControl;
  }

}
