import {Component, OnInit} from '@angular/core';
import {EditMovieForm} from "./edit-movie-form";
import {MovieService} from "../../services/movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {MovieResponse} from "../../dtos/response/movie.response";

@Component({
  selector: 'app-edit-movie-form',
  templateUrl: './edit-movie-form.component.html',
  styleUrls: ['./edit-movie-form.component.scss']
})
export class EditMovieFormComponent implements OnInit {
  protected isLoading = true;

  protected editMovieForm!: EditMovieForm;


  private title!: string;

  constructor(
    private readonly movieService: MovieService,
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly formBuilder: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const titleParam = params.get('title');
      if (titleParam !== null) {
        this.title = titleParam;
        this.setUpEditMovieForm();
      } else {
        this.goBack();
      }
    });
  }

  handleCancelClicked() {
    this.goBack();
  }

  protected goBack(): void {
    this.router.navigate(['/movies']);
  }

  protected onSubmit(): void {
    const moviePromise = this.editMovieForm.updateMovieRequestFromForm();
    moviePromise.then(movie => {
      const updateMovie = this.movieService.updateMovie(this.title, movie);
      this.isLoading = true;
      updateMovie.subscribe({
        next: () => {
          this.isLoading = false;
          this.goBack()
        },
        error: () => {
          this.isLoading = false;
        }
      });
    });
  }

  private setUpEditMovieForm(): void {
    this.editMovieForm = new EditMovieForm(this.formBuilder);
    this.loadBrand();
  }

  private loadBrand(): void {
    const movie$ = this.movieService.getMovie(this.title);
    movie$.subscribe({
      next: (movie: MovieResponse) => {
        this.editMovieForm.fillFormWithMovie(movie);
        this.isLoading = false;
      },
      error: () => {
        this.goBack();
      },
    });
  }
}
