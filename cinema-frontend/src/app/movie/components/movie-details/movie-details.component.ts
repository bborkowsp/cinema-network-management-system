import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MovieService} from "../../services/movie.service";
import {map, Observable, switchMap, tap} from "rxjs";
import {MovieResponse} from "../../dtos/response/movie.response";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements OnInit {
  movie$!: Observable<MovieResponse>;
  title: string = '';
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  imageUrl: string = '';
  protected isLoading = true;

  constructor(
    private readonly movieService: MovieService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router,
    private httpClient: HttpClient
  ) {
  }

  ngOnInit(): void {
    // this.getMovie();
    this.getImage();
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

  getImage() {
    this.httpClient.get('http://localhost:8080/api/v1/movies/test', {responseType: 'blob'})
      .subscribe((data: Blob) => {
        const reader = new FileReader();
        reader.onload = () => {
          this.imageUrl = reader.result as string;
        };
        reader.readAsDataURL(data);
      });
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
