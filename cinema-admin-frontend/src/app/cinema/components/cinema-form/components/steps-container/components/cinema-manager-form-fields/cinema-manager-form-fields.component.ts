import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {CinemaManagerResponse} from "../../../../../../../user/dtos/response/cinema-manager.response";
import {UserService} from "../../../../../../../user/services/user.service";

@Component({
  selector: 'app-cinema-manager-form-fields',
  templateUrl: './cinema-manager-form-fields.component.html',
  styleUrls: ['./cinema-manager-form-fields.component.scss']
})
export class CinemaManagerFormFieldsComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  cinemaManagers!: Observable<CinemaManagerResponse[]>;

  constructor(
    private readonly userService: UserService,
  ) {
  }

  get cinemaManagerControl(): FormControl {
    return this.formGroup.get('cinemaManager') as FormControl;
  }

  ngOnInit(): void {
    this.cinemaManagers = this.userService.getCinemaManagersList();
  }
}
