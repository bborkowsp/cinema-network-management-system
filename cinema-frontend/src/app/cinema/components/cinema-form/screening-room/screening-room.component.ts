import {Component} from '@angular/core';
import {SeatRequest} from "../../../dtos/request/seat.request";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-screening-room',
  templateUrl: './screening-room.component.html',
  styleUrls: ['./screening-room.component.scss']
})
export class ScreeningRoomComponent {
  rows: number = 0;
  columns: number = 0;
  currentScreeningRoom: SeatRequest[][] = [];
  seatingPlan: any[][][] = [];
  screeningRooms: { index: number, rows: number, columns: number }[] = [];
  showCurrentScreeningRoom: boolean = true;

  projectionTechnologies = new FormControl('');
  projectionTechnologiesList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  createSeatingGrid() {
    console.log(this.rows, this.columns);
    this.showCurrentScreeningRoom = true;
    this.seatingPlan = [];
    for (let i = 0; i < this.rows; i++) {
      const row: SeatRequest[] = [];
      for (let j = 0; j < this.columns; j++) {
        row.push(new SeatRequest(i, j, 'STANDARD'));
      }
      this.currentScreeningRoom.push(row);
    }
  }

  saveScreeningRoom() {
    this.showCurrentScreeningRoom = false;
    this.seatingPlan.push(this.currentScreeningRoom);
    this.screeningRooms.push({index: this.screeningRooms.length + 1, rows: this.rows, columns: this.columns});
  }

  handleStandardClicked(cell: any) {
    cell.selected = 'Standard';
  }

  handleVIPClicked(cell: any) {
    cell.selected = 'VIP';
  }

  handlePromoClicked(cell: any) {
    cell.selected = 'Promo';
  }

  handleWheelchairClicked(cell: any) {
    cell.selected = 'Wheelchair';
  }


  handleCorridorClicked(cell: any) {
    cell.selected = 'Corridor';
  }
}
