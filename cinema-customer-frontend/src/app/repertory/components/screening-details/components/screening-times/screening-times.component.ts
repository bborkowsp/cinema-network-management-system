import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ScreeningResponse} from 'src/app/repertory/dtos/response/screening.response';
import {getEnumValueByKey} from "../../../../enums/projection-technology";
import {MovieVariantResponse} from "../../../../dtos/response/movie-variant.response";
import {Router} from "@angular/router";

@Component({
  selector: 'app-screening-times',
  templateUrl: './screening-times.component.html',
  styleUrl: './screening-times.component.scss'
})
export class ScreeningTimesComponent {
  @Input() screeningTimes!: { [cinemaName: string]: ScreeningResponse[] };
  @Output() buyTicket = new EventEmitter<ScreeningResponse>();
  protected readonly getEnumValueByKey = getEnumValueByKey;

  constructor(
    private readonly router: Router
  ) {
  }

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
    return Array.from(uniqueVariants.values());
  }

  getScreeningsByVariant(screenings: ScreeningResponse[], variant: MovieVariantResponse): ScreeningResponse[] {
    return screenings
      .filter(screening =>
        screening.movieVariant.projectionTechnology === variant.projectionTechnology &&
        screening.movieVariant.language === variant.language
      )
      .sort((a, b) => new Date(a.startTime).getTime() - new Date(b.startTime).getTime());
  }

  handleBuyTicket(screening: ScreeningResponse) {
    this.router.navigateByUrl(`buy-ticket/${screening.id}`, {state: {data: screening}});
  }
}
