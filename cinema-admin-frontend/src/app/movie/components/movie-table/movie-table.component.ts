import {Component, OnInit} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../_shared/dtos/paginator-request-params";
import {Router} from "@angular/router";
import {MovieListResponse} from "../../dtos/response/movie-list.response";
import {MovieService} from "../../services/movie.service";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeletionMovieDialog} from "../confirm-deletion-movie-dialog/confirm-deletion-movie-dialog.component";
import {UserRoleService} from "../../../auth/services/user-role.service";
import {TableColumn} from "../../../_shared/components/generic-table/generic-table.component";

@Component({
  selector: 'app-movie-table',
  templateUrl: './movie-table.component.html',
  styleUrl: './movie-table.component.scss'
})
export class MovieTableComponent implements OnInit {
  protected isLoading = true;
  protected dataLength = 0;
  protected movies: MovieListResponse[] = [];
  protected paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected isUserRoleCinemaManager = true;
  protected displayedColumns: TableColumn[] = [
    {columnDefinition: 'options', header: 'Options', isOptionsColumn: true},
    {columnDefinition: 'poster', header: 'Poster', isPosterColumn: true},
    {columnDefinition: 'title', header: 'Title'},
    {columnDefinition: 'originalTitle', header: 'Original Title'},
    {columnDefinition: 'releaseDate', header: 'Release Date'},
  ]

  constructor(
    private readonly movieService: MovieService,
    private readonly router: Router,
    private readonly dialog: MatDialog,
    private readonly userRoleService: UserRoleService
  ) {
    this.getData().subscribe();
  }

  ngOnInit() {
    this.isUserRoleCinemaManager = this.userRoleService.isCinemaManager()
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.getData().subscribe();
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
            next: () => (
              this.getData().subscribe()
            ),
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
          this.movies = moviePage.content;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((moviePage) => moviePage.content),
    );
  }
}
