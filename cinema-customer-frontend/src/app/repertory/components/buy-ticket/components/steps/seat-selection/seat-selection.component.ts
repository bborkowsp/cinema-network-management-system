import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ScreeningResponse} from "../../../../../dtos/response/screening.response";
import {SeatResponse} from "../../../../../dtos/response/seat.response";
import "../../../../../../_shared/styles/_colors.scss";

@Component({
  selector: 'app-seat-selection',
  templateUrl: './seat-selection.component.html',
  styleUrls: ['./seat-selection.component.scss']
})
export class SeatSelectionComponent {
  data!: ScreeningResponse;
  selectedSeats: SeatResponse[] = [];

  constructor(private readonly router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.data = navigation.extras.state['data'] as ScreeningResponse;
      console.log(this.data);
    }
  }

  selectSeat(seat: SeatResponse) {
    if (this.selectedSeats.includes(seat)) {
      this.selectedSeats = this.selectedSeats.filter(s => s !== seat);
      return;
    }
    this.selectedSeats.push(seat);
  }

  getSeatBackgroundColor(seat: SeatResponse): string {
    if (this.selectedSeats.includes(seat)) {
      switch (seat.seatZone) {
        case 'STANDARD':
          return '#c28dff';
        case 'VIP':
          return '#ffd24d';
        case 'PROMO':
          return '#66ff66';
        case 'WHEELCHAIR':
          return '#97d3f1';
        default:
          return 'white';
      }
    }
    return 'white';
  }
}
