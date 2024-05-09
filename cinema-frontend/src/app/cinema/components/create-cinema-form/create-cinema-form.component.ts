import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {CreateCinemaFormHelper} from "./create-cinema-form-helper";
import {CinemaService} from "../../services/cinema.service";

@Component({
  selector: 'app-create-cinema-form',
  templateUrl: './create-cinema-form.component.html',
  styleUrls: ['./create-cinema-form.component.scss']
})
export class CreateCinemaFormComponent implements OnInit {
  protected isLoading = false;
  protected cinemaForm!: CreateCinemaFormHelper;


  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private cinemaService: CinemaService
  ) {
  }

  ngOnInit() {
    this.setUpCinemaForm();
  }

  handleGoBackButtonAction() {
    this.router.navigateByUrl('/cinemas');
  }

  protected onSubmit(): void {
    const cinema = this.cinemaForm.createCinemaRequestFromForm;
    console.log(cinema);
    console.log("--------------------");
    const createCinema$ = this.cinemaService.createCinema(cinema);
    this.isLoading = true;
    createCinema$.subscribe({
      next: () => this.handleGoBackButtonAction(),
    });
  }

  private setUpCinemaForm() {
    this.cinemaForm = new CreateCinemaFormHelper(this.formBuilder);
  }
}
