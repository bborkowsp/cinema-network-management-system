import {Component, Input} from '@angular/core';
import {ScreeningResponse} from 'src/app/repertory/dtos/response/screening.response';
import {images} from "../../../../../../assets/environment";

@Component({
  selector: 'app-screening-times',
  templateUrl: './screening-times.component.html',
  styleUrl: './screening-times.component.scss'
})
export class ScreeningTimesComponent {
  @Input() screeningTimes!: { [cinemaName: string]: ScreeningResponse[] };
  readonly POSTERS_SERVER_DIRECTORY_URL = `${images.IMAGES_SERVER_DIRECTORY_URL}/posters/`;

  getCinemas(): string[] {
    return Object.keys(this.screeningTimes);
  }
}
