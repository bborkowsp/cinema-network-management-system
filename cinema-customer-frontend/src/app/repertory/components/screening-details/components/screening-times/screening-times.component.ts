import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ScreeningResponse} from 'src/app/repertory/dtos/response/screening.response';
import {getEnumValueByKey} from "../../../../enums/projection-technology";
import {MovieVariantResponse} from "../../../../dtos/response/movie-variant.response";

@Component({
  selector: 'app-screening-times',
  templateUrl: './screening-times.component.html',
  styleUrl: './screening-times.component.scss'
})
export class ScreeningTimesComponent {
  @Input() screeningTimes!: { [cinemaName: string]: ScreeningResponse[] };
  @Output() buyTicket = new EventEmitter<ScreeningResponse>();
  protected readonly getEnumValueByKey = getEnumValueByKey;

  getCinemas(): string[] {
    return Object.keys(this.screeningTimes);
  }

  getMovieVariants(screenings: ScreeningResponse[]): MovieVariantResponse[] {
    const uniqueVariants = new Map<string, MovieVariantResponse>();
    screenings.forEach(screening => {
      const key = `${screening.movieVariant.projectionTechnology}-${screening.movieVariant.language}`;
      if (!uniqueVariants.has(key)) {
        uniqueVariants.set(key, screening.movieVariant);
      }
    });
    const a = Array.from(uniqueVariants.values());
    console.log(a)
    return a;
  }

  getScreeningsByVariant(screenings: ScreeningResponse[], variant: MovieVariantResponse): ScreeningResponse[] {
    return screenings.filter(screening =>
      screening.movieVariant.projectionTechnology === variant.projectionTechnology &&
      screening.movieVariant.language === variant.language
    );
  }

  handleBuyTicket(screening: ScreeningResponse) {
    this.buyTicket.emit(screening);
  }
}
