import {Component, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from "../../../shared/dtos/paginator-request-params";
import {Router} from "@angular/router";
import {MovieListResponse} from "../../dtos/response/movie-list.response";
import {MovieService} from "../../services/movie.service";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent {
  displayedColumns = ['options', 'title', 'originalTitle', 'releaseDate', 'poster', 'director'];
  movies$!: Observable<MovieListResponse[]>;
  dataLength = 0;
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected isLoading = true;

  constructor(
    private readonly movieService: MovieService,
    private readonly router: Router,
  ) {
    this.movies$ = this.getData();
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
    this.movieService.deleteMovie(movie.title).subscribe({
      next: () => (this.movies$ = this.getData()),
    });
  }

  handleShowDetails(movie: MovieListResponse): void {
    const url = `projection-technologies/details/${movie.title}`;
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
      map((projectionTechnologyPage) => projectionTechnologyPage.content),
    );
  }
}
