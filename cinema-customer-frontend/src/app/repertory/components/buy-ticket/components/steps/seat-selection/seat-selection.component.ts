import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ScreeningResponse} from "../../../../../dtos/response/screening.response";
import {SeatResponse} from "../../../../../dtos/response/seat.response";

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
    this.selectedSeats.push(seat);

  }
}
