import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MovieService} from "../../services/movie.service";
import {map, Observable, switchMap, tap} from "rxjs";
import {MovieResponse} from "../../dtos/response/movie.response";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements OnInit {
  movie$!: Observable<MovieResponse>;
  title: string = '';
  protected isLoading = true;

  constructor(
    private readonly movieService: MovieService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router,
  ) {
  }

  ngOnInit(): void {
    this.getMovie();
  }

  handleEditMovie() {
    this.router.navigateByUrl(`/movies/edit/${this.title}`);
  }

  handleDeleteMovie() {
    this.movieService.deleteMovie(this.title).subscribe(() => {
      this.router.navigateByUrl('/movies');
    });
  }

  handleGoBack() {
    this.router.navigateByUrl('/movies');
  }

  private getMovie() {
    this.movie$ = this.activatedRoute.paramMap.pipe(
      map((paramMap) => paramMap.get('title')!),
      tap((title) => (this.title = title)),
      switchMap((title) => this.movieService.getMovie(title)),
    );
    this.isLoading = false;
  }
}
