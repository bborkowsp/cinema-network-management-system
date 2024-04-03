import {Component} from '@angular/core';

@Component({
  selector: 'app-screening-room',
  templateUrl: './screening-room.component.html',
  styleUrls: ['./screening-room.component.scss']
})
export class ScreeningRoomComponent {
  rows!: number;
  columns!: number;
  seatingPlan: any[][] = [];

  // currentScreeningRoom: any[][] = [];

  createSeatingGrid() {
    this.seatingPlan = [];
    for (let i = 0; i < this.rows; i++) {
      const row = [];
      for (let j = 0; j < this.columns; j++) {
        row.push({selected: 'Standard', isSeat: true});
      }
      this.seatingPlan.push(row);
    }
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

  // saveScreeningRoom() {
  //   this.seatingPlan.push(this.currentScreeningRoom);
  //   this.createSeatingGrid();
  // }
}
