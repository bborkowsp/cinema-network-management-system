import {Component, Input} from '@angular/core';
import {MovieResponse} from "../../../../dtos/response/movie.response";
import {images} from "../../../../../../assets/environment";

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.scss'
})
export class MovieDetailsComponent {
  @Input({required: true}) movie!: MovieResponse;
  readonly POSTERS_SERVER_DIRECTORY_URL = `${images.IMAGES_SERVER_DIRECTORY_URL}/posters/`;

}
