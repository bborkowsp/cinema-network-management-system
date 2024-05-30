import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {MovieFormBuilder} from "./movie-form-builder";
import {MovieService} from "../../services/movie.service";
import {CreateMovieRequest} from "../../dtos/request/create-movie.request";
import {UpdateMovieRequest} from "../../dtos/request/update-movie.request";

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.scss']
})
export class MovieFormComponent implements OnInit {

  protected isEditMode = false;
  protected isLoading = true;
  protected pageTitle !: string;
  protected movieFormBuilder !: MovieFormBuilder;
  private static readonly GO_BACK_NAVIGATION_PATH = '/movies';
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

  protected onSubmit() {
    let movieRequestPromise: Promise<CreateMovieRequest | UpdateMovieRequest>;

    if (this.isEditMode) {
      movieRequestPromise = this.movieFormBuilder.getUpdateMovieRequestFromForm();
    } else {
      movieRequestPromise = this.movieFormBuilder.getCreateMovieRequestFromForm();
    }

    this.handleFormSubmission(movieRequestPromise);
  }

  private handleFormSubmission(movieRequestPromise: Promise<CreateMovieRequest | UpdateMovieRequest>) {
    this.isLoading = true;
    movieRequestPromise.then((cinemaRequest) => {
      let movieServiceObservable;
      if (this.isEditMode) {
        movieServiceObservable = this.movieService.updateMovie(this.title, cinemaRequest as UpdateMovieRequest);
      } else {
        movieServiceObservable = this.movieService.createMovie(cinemaRequest as CreateMovieRequest);
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
    }).catch(() => {
      this.isLoading = false;
    });
  }


  private goBack() {
    this.router.navigate([MovieFormComponent.GO_BACK_NAVIGATION_PATH]);
  }

  handleCancelClicked() {
    this.goBack();
  }
}
