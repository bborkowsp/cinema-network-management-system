import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ScreeningResponse} from "../../dtos/response/screening.response";
import {images} from "../../../../assets/environment";
import {MovieVariantResponse} from "../../dtos/response/movie-variant.response";
import {getEnumValueByKey} from "../../enums/projection-technology";

@Component({
  selector: 'app-screening-list',
  templateUrl: './screening-list.component.html',
  styleUrls: ['./screening-list.component.scss']
})
export class ScreeningListComponent implements OnInit, OnChanges {
  readonly POSTERS_SERVER_DIRECTORY_URL = `${images.IMAGES_SERVER_DIRECTORY_URL}/posters/`;
  @Input() repertory: { [movieTitle: string]: ScreeningResponse[] } = {};
  @Input() isLoading: boolean = false;
  @Input() isCinemaSelected: boolean = false;
  @Input() movieTitles: string[] = [];
  @Output() buyTicket = new EventEmitter<ScreeningResponse>();
  @Input() date!: Date;
  formattedDate = '';
  protected readonly getEnumValueByKey = getEnumValueByKey;

  ngOnInit() {
    this.formatDate();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['date']) {
      this.formatDate();
    }
  }

  handleBuyTicket(screening: ScreeningResponse) {
    this.buyTicket.emit(screening);
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

  // Pobierz seanse dla danego wariantu
  getScreeningsByVariant(screenings: ScreeningResponse[], variant: MovieVariantResponse): ScreeningResponse[] {
    return screenings.filter(screening =>
      screening.movieVariant.projectionTechnology === variant.projectionTechnology &&
      screening.movieVariant.language === variant.language
    );
  }

  getFirstTenWords(description: string): string {
    if (!description) {
      return '';
    }
    const words = description.split(' ');
    return words.slice(0, 10).join(' ') + (words.length > 10 ? ' ...' : '');
  }

  private formatDate() {
    const year = this.date.getFullYear();
    const month = (this.date.getMonth() + 1).toString().padStart(2, '0');
    const day = this.date.getDate().toString().padStart(2, '0');
    this.formattedDate = `${year}-${month}-${day}`;
  }
}
