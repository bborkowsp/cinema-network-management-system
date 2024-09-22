import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {ScreeningResponse} from "../../../../../dtos/response/screening.response";
import {SeatResponse} from "../../../../../dtos/response/seat.response";
import "../../../../../../_shared/styles/_colors.scss";
import {SeatLimitDialogComponent} from "./seat-limit-dialog/seat-limit-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {FormArray, FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {SeatStatus} from "../../../enums/seat-status";
import {SeatPrices, SeatPricesAsNumbers, SeatZone} from "../../../enums/seat-zone";

@Component({
  selector: 'app-seat-selection',
  templateUrl: './seat-selection.component.html',
  styleUrls: ['./seat-selection.component.scss']
})
export class SeatSelectionComponent {
  @Input({required: true}) seatSelectionFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createBuyTicketForm!: FormGroup;
  data!: ScreeningResponse;
  selectedSeats: SeatResponse[] = [];
  totalCost: number = 0;
  protected readonly SeatStatus = SeatStatus;
  protected readonly SeatZone = SeatZone;
  protected readonly SeatPrices = SeatPrices;

  constructor(
    private readonly router: Router,
    private dialogRef: MatDialog,
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.data = navigation.extras.state['data'] as ScreeningResponse;
    } else {
      this.getScreeningRoomFromLocalStorage();
    }
  }

  get selectedSeatsControl(): FormArray {
    return this.createBuyTicketForm.get('selectedSeats') as FormArray;
  }

  selectSeat(seat: SeatResponse) {
    const seatIndex = this.selectedSeats.findIndex(s => s === seat);

    if (seatIndex > -1) {
      this.selectedSeats.splice(seatIndex, 1);
      this.selectedSeatsControl.removeAt(seatIndex);
      this.totalCost -= this.getPrice(seat);
    } else {
      if (this.selectedSeats.length >= 9) {
        this.open();
        return;
      }
      this.selectedSeats.push(seat);
      this.selectedSeatsControl.push(new FormControl(seat));
      this.totalCost += this.getPrice(seat);
    }
  }

  getSeatBackgroundColor(seat: SeatResponse): string {
    if (seat.seatStatus === SeatStatus.RESERVED || seat.seatStatus === SeatStatus.SOLD) {
      switch (seat.seatZone) {
        case 'STANDARD':
          return 'rgba(194,141,255,0.4)';
        case 'VIP':
          return 'rgba(255,210,77,0.4)';
        case 'PROMO':
          return 'rgba(102,255,102,0.4)';
        case 'WHEELCHAIR':
          return 'rgba(151,211,241,0.4)';
        default:
          return 'white';
      }
    }
    if (this.selectedSeats.includes(seat)) {
      switch (seat.seatZone) {
        case 'STANDARD':
          return '#7925d5';
        case 'VIP':
          return '#ffd24d';
        case 'PROMO':
          return '#11b211';
        case 'WHEELCHAIR':
          return '#4eb0e1';
        default:
          return 'white';
      }
    }
    return 'white';
  }

  private getPrice(seat: SeatResponse): number {
    return SeatPricesAsNumbers[seat.seatZone];
  }

  private open() {
    this.dialogRef.open(SeatLimitDialogComponent);
  }

  private getScreeningRoomFromLocalStorage() {
    const screening = localStorage.getItem('screening');
    if (screening) {
      this.data = JSON.parse(screening);
    } else {
      this.router.navigate(['/repertory']);
    }
  }
}
