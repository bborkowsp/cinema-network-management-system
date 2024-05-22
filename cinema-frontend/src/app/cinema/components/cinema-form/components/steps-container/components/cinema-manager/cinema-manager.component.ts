import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {CinemaManagerResponse} from "../../../../../../../user/dtos/response/cinema-manager.response";
import {UserService} from "../../../../../../../user/services/user.service";

@Component({
  selector: 'app-cinema-manager',
  templateUrl: './cinema-manager.component.html',
  styleUrls: ['./cinema-manager.component.scss']
})
export class CinemaManagerComponent implements OnInit {
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
