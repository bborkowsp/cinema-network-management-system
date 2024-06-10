import {Component, OnInit, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../shared/dtos/paginator-request-params";
import {Router} from "@angular/router";
import {MovieListResponse} from "../../dtos/response/movie-list.response";
import {MovieService} from "../../services/movie.service";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeletionMovieDialog} from "../confirm-deletion-movie-dialog/confirm-deletion-movie-dialog.component";
import {AuthService} from "../../../user/services/auth.service";

@Component({
  selector: 'app-movie-table',
  templateUrl: './movie-table.component.html',
  styleUrls: ['./movie-table.component.scss']
})
export class MovieTableComponent implements OnInit {
  displayedColumns = ['options', 'poster', 'title', 'originalTitle', 'releaseDate', 'director'];
  movies$!: Observable<MovieListResponse[]>;
  dataLength = 0;
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected isLoading = true;
  protected isUserRoleCinemaManager = true;

  constructor(
    private readonly movieService: MovieService,
    private readonly router: Router,
    private readonly dialog: MatDialog,
    private readonly authService: AuthService
  ) {
    this.movies$ = this.getData();
  }

  ngOnInit() {
    this.isUserRoleCinemaManager = this.authService.checkIfLoggedInUserIsCinemaManager()
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.movies$ = this.getData();
  }

  goToCreate(): void {
    const url = 'movies/create';
    this.router.navigateByUrl(url);
  }

  handleEdit(movie: MovieListResponse): void {
    const url = `movies/edit/${movie.title}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(movie: MovieListResponse): void {
    const matDialog = this.dialog.open(ConfirmDeletionMovieDialog, {
      data: movie
    })

    matDialog.afterClosed().subscribe({
      next: (result) => {
        if (result) {
          this.movieService.deleteMovie(movie.title).subscribe({
            next: () => (this.movies$ = this.getData()),
          });
        }
      },
    });
  }

  handleShowDetails(movie: MovieListResponse): void {
    const url = `movies/details/${movie.title}`;
    this.router.navigateByUrl(url);
  }

  private getData(): Observable<MovieListResponse[]> {
    return this.movieService.getMovies(this.paginatorRequestParams).pipe(
      tap({
        next: (moviePage) => {
          this.dataLength = moviePage.totalElements;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((moviePage) => moviePage.content),
    );
  }

}
