import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {MovieFormBuilder} from "./movie-form-builder";
import {MovieService} from "../../services/movie.service";

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.scss']
})
export class MovieFormComponent implements OnInit {
  private static readonly GO_BACK_NAVIGATION_PATH = '/movies';
  isEditMode = false;
  isLoading = true;
  pageTitle !: string;
  movieFormBuilder !: MovieFormBuilder;
  private title !: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private movieService: MovieService,
  ) {
  }

  ngOnInit() {
    const params = this.activatedRoute.snapshot.params;
    if (params['title']) {
      this.isEditMode = true;
      this.title = params['title'];
      this.pageTitle = 'Edit Movie';
      this.setUpEditProjectionTechnologyForm();
    } else {
      this.pageTitle = 'Add Movie';
      this.setUpCreateProjectionTechnologyForm();
    }
  }

  handleCancelClicked() {
    this.goBack();
  }

  getInvalidControls(): string[] {
    const invalidControls: string[] = [];
    const controls = this.movieFormBuilder.form.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        invalidControls.push(name);
      }
    }
    return invalidControls;
  }

  protected onSubmit() {
    let movieRequestPromise: FormData;

    if (this.isEditMode) {
      movieRequestPromise = this.movieFormBuilder.getUpdateMovieRequestFromForm();
    } else {
      movieRequestPromise = this.movieFormBuilder.getCreateMovieRequestFromForm();
    }

    this.handleFormSubmission(movieRequestPromise);
  }

  private setUpEditProjectionTechnologyForm() {
    this.movieFormBuilder = new MovieFormBuilder(this.formBuilder);
    this.loadMovie();
  }

  private setUpCreateProjectionTechnologyForm() {
    this.movieFormBuilder = new MovieFormBuilder(this.formBuilder);
    this.isLoading = false;
  }

  private loadMovie() {
    const movie$ = this.movieService.getMovie(this.title);
    movie$.subscribe({
      next: (movie) => {
        this.movieFormBuilder.fillFormWithMovie(movie);
        this.isLoading = false;
      },
      error: () => {
        this.goBack();
      }
    });
  }

  private handleFormSubmission(movieRequestPromise: FormData) {
    this.isLoading = true;
    let movieServiceObservable;
    if (this.isEditMode) {
      movieServiceObservable = this.movieService.updateMovie(this.title, movieRequestPromise);
    } else {
      movieServiceObservable = this.movieService.createMovie(movieRequestPromise);
    }
    movieServiceObservable.subscribe({
      next: () => {
        this.isLoading = false;
        this.goBack();
      },
      error: () => {
        this.isLoading = false;
      }
    });
  }

  private goBack() {
    this.router.navigate([MovieFormComponent.GO_BACK_NAVIGATION_PATH]);
  }
}
