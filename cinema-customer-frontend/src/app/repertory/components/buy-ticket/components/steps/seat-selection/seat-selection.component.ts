import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ScreeningResponse} from "../../../../../dtos/response/screening.response";
import {SeatResponse} from "../../../../../dtos/response/seat.response";
import "../../../../../../_shared/styles/_colors.scss";
import {SeatLimitDialogComponent} from "./seat-limit-dialog/seat-limit-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-seat-selection',
  templateUrl: './seat-selection.component.html',
  styleUrls: ['./seat-selection.component.scss']
})
export class SeatSelectionComponent {
  data!: ScreeningResponse;
  selectedSeats: SeatResponse[] = [];
  totalCost: number = 0;
  zones = ['PROMO', 'STANDARD', 'VIP', 'WHEELCHAIR', 'CORRIDOR'];

  constructor(
    private readonly router: Router,
    private dialogRef: MatDialog,
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.data = navigation.extras.state['data'] as ScreeningResponse;
      console.log(this.data);
    }
  }

  selectSeat(seat: SeatResponse) {
    if (this.selectedSeats.includes(seat)) {
      this.selectedSeats = this.selectedSeats.filter(s => s !== seat);
      this.totalCost -= this.getPrice(seat);
      return;
    }
    if (this.selectedSeats.length >= 9) {
      this.open();
    }
    this.selectedSeats.push(seat);
    this.totalCost += this.getPrice(seat);
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


  private getPrice(seat: SeatResponse) {
    switch (seat.seatZone) {
      case 'STANDARD':
        return 10;
      case 'VIP':
        return 20;
      case 'PROMO':
        return 5;
      case 'WHEELCHAIR':
        return 19.90;
      default:
        return 0;
    }
  }

  private open() {
    this.dialogRef.open(SeatLimitDialogComponent);
  }
}
