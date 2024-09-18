import {Component, Input} from '@angular/core';
import {MovieResponse} from "../../../../dtos/response/movie.response";
import {images} from "../../../../../../assets/environment";
import {MatDialog} from "@angular/material/dialog";
import {TrailerDialogComponent} from "./trailer-dialog/trailer-dialog.component";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.scss'
})
export class MovieDetailsComponent {
  @Input({required: true}) movie!: MovieResponse;
  readonly POSTERS_SERVER_DIRECTORY_URL = `${images.IMAGES_SERVER_DIRECTORY_URL}/posters/`;

  constructor(public dialog: MatDialog) {
  }

  openTrailer(): void {
    this.dialog.open(TrailerDialogComponent, {
      data: {trailerUrl: this.movie.trailer},
      width: '80vw',
      height: '60vh',
      maxWidth: '900px',
      maxHeight: '600px'
    });
  }
}
