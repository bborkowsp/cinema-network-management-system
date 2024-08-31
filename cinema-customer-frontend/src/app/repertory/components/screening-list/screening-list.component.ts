import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ScreeningResponse} from "../../dtos/response/screening.response";

@Component({
  selector: 'app-screening-list',
  templateUrl: './screening-list.component.html',
  styleUrls: ['./screening-list.component.scss']
})
export class ScreeningListComponent {
  @Input() repertory: { [movieTitle: string]: ScreeningResponse[] } = {};
  @Input() isLoading: boolean = false;
  @Input() isCinemaSelected: boolean = false;
  @Input() movieTitles: string[] = [];
  @Output() buyTicket = new EventEmitter<ScreeningResponse>();

  handleBuyTicket(screening: ScreeningResponse) {
    this.buyTicket.emit(screening);
  }
}
