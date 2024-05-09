import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MovieService} from "../../services/movie.service";
import {CreateMovieFormComponent} from "./create-movie-form.component";

@Component({
  selector: 'app-create-movie-form',
  templateUrl: './create-movie.component.html',
  styleUrls: ['./create-movie.component.scss']
})
export class CreateMovieComponent implements OnInit {

  protected createMovieFormComponent!: CreateMovieFormComponent;
  protected isLoading = false;

  constructor(
    private readonly movieService: MovieService,
    private readonly router: Router,
    private readonly formBuilder: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.createMovieFormComponent = new CreateMovieFormComponent(this.formBuilder);
  }

  handleCancelClicked() {
    this.goBack()
  }

  protected onSubmit(): void {
    this.isLoading = true;

    this.createMovieFormComponent.createMovieRequestFromForm().then(movie => {
      if (movie !== null) {
        this.movieService.createMovie(movie).subscribe({
          next: () => this.goBack(),
        });
      } else {
        this.isLoading = false;
      }
    });
  }


  private goBack() {
    this.router.navigate(['/movies']);
  }
}