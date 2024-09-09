import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ScreeningResponse} from "../../dtos/response/screening.response";
import {images} from "../../../../assets/environment";

@Component({
  selector: 'app-screening-list',
  templateUrl: './screening-list.component.html',
  styleUrls: ['./screening-list.component.scss']
})
export class ScreeningListComponent implements OnInit {
  readonly POSTERS_SERVER_DIRECTORY_URL = `${images.IMAGES_SERVER_DIRECTORY_URL}/posters/`;
  @Input() repertory: { [movieTitle: string]: ScreeningResponse[] } = {};
  @Input() isLoading: boolean = false;
  @Input() isCinemaSelected: boolean = false;
  @Input() movieTitles: string[] = [];
  @Output() buyTicket = new EventEmitter<ScreeningResponse>();
  @Input() date!: Date;
  formattedDate = '';

  ngOnInit() {
    this.formatDate();
  }

  handleBuyTicket(screening: ScreeningResponse) {
    this.buyTicket.emit(screening);
  }

  private formatDate() {
    const year = this.date.getFullYear();
    const month = (this.date.getMonth() + 1).toString().padStart(2, '0');
    const day = this.date.getDate().toString().padStart(2, '0');
    this.formattedDate = `${year}-${month}-${day}`;
  }
}
