import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MovieService} from "../../services/movie.service";
import {map, Observable, switchMap, tap} from "rxjs";
import {MovieResponse} from "../../dtos/response/movie.response";
import {DomSanitizer} from '@angular/platform-browser';
import {AuthService} from "../../../auth/services/auth.service";


@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements OnInit {
  movie$!: Observable<MovieResponse>;
  title: string = '';
  isLoading = true;
  isUserRoleCinemaManager = true;

  constructor(
    private readonly movieService: MovieService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router,
    private readonly changeDetectorRef: ChangeDetectorRef,
    private readonly sanitizer: DomSanitizer,
    private readonly authService: AuthService
  ) {
  }

  ngOnInit(): void {
    this.isUserRoleCinemaManager = this.authService.checkIfLoggedInUserIsCinemaManager()
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

  getActorNames(movie: MovieResponse): string {
    return movie.productionDetails.actors
      .map(actor => actor.firstName + ' ' + actor.lastName)
      .join(', ');
  }

  getTrailerUrl(movie: MovieResponse) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(movie.trailer.url);
  }

  private getMovie() {
    this.movie$ = this.activatedRoute.paramMap.pipe(
      map((paramMap) => paramMap.get('title')!),
      tap((title) => {
        this.title = title;
        this.isLoading = false;
        this.changeDetectorRef.detectChanges();
      }),
      switchMap((title) => this.movieService.getMovie(title)),
    );
  }
}
