import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ScreeningRoomResponse} from "../../../../../../../repertory/dtos/screening-room.response";
import {SeatResponse} from "../../../../../../dtos/response/seat.response";

@Component({
  selector: 'app-screening-room',
  templateUrl: './screening-room.component.html',
  styleUrls: ['./screening-room.component.scss']
})
export class ScreeningRoomComponent implements OnInit, OnChanges {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formArray!: FormArray;
  protected allScreeningRooms: ScreeningRoomResponse[] = [];
  createScreeningRoomFormGroup!: FormGroup;
  currentEditedContactDetailIndex: number = -1;
  rowsNumberControl = new FormControl(0, Validators.required);
  columnsNumberControl = new FormControl(0, Validators.required);
  currentScreeningRoom: SeatResponse[][] = [];
  showCurrentScreeningRoom = false;

  ngOnInit() {
    this.updateScreeningRooms();
    this.createScreeningRoomFormGroup = this.createFormGroup();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['formArray']) {
      this.updateScreeningRooms();
      this.updateRowsAndColumnsNumberControl();
    }
  }

  private updateRowsAndColumnsNumberControl() {
    const seatsControl = this.seatRowsControl;
    if (!seatsControl) {
      this.rowsNumberControl.setValue(0);
      this.columnsNumberControl.setValue(0);
      return;
    }

    this.rowsNumberControl.setValue(seatsControl.length);
    this.columnsNumberControl.setValue(seatsControl.at(0).value.length);
  }

  private updateScreeningRooms() {
    this.allScreeningRooms = this.formArray.value as ScreeningRoomResponse[];
  }

  editScreeningRoom(i: number) {
    this.currentEditedContactDetailIndex = i;
    this.showCurrentScreeningRoom = true;
    this.currentScreeningRoom = (this.formArray.at(i).get('seats') as FormArray).value;

    console.log(this.currentScreeningRoom);

    const row = this.currentScreeningRoom[0];
    console.log(row);
    const row2 = this.currentScreeningRoom[1];
    console.log(row2);

    this.updateRowsAndColumnsNumberControl();
  }

  deleteScreeningRoom(i: number) {
    this.formArray.removeAt(i);
    this.updateScreeningRooms();
    this.currentEditedContactDetailIndex = -1;
  }

  saveScreeningRoom() {
    const screeningRoom = this.createFilledScreeningRoomGroup();

    if (this.currentEditedContactDetailIndex === -1) {
      this.formArray.push(screeningRoom);
      this.createScreeningRoomFormGroup.reset();
    } else {
      this.formArray.at(this.currentEditedContactDetailIndex).patchValue(screeningRoom);
    }
    this.updateScreeningRooms();
    this.currentEditedContactDetailIndex = -1;
  }

  private createFilledScreeningRoomGroup(): FormGroup {
    return new FormGroup({
      name: new FormControl(this.nameControl.value),
    });
  }

  private createFormGroup(): FormGroup {
    return new FormGroup({
      name: new FormControl(''),
      seats: new FormArray([
        new FormArray([
          new FormGroup({
            seatRow: new FormControl(''),
            seatColumn: new FormControl(''),
            seatZone: new FormControl(''),
            seatType: new FormControl(''),
          })
        ])
      ]),
      supportedTechnologies: new FormArray([
        new FormGroup({
          technology: new FormControl(''),
          description: new FormControl(''),
        })
      ])
    });
  }


  get nameControl(): FormControl {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('name') as FormControl;
    }
    return this.createScreeningRoomFormGroup.get('name') as FormControl;
  }

  get seatRowsControl(): FormArray {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('seats') as FormArray;
    }
    return '' as unknown as FormArray;
  }

  handleStandardClicked(cell: any) {
    cell.seatZone = 'STANDARD';
  }

  handleVIPClicked(cell: any) {
    cell.seatZone = 'VIP';
  }

  handlePromoClicked(cell: any) {
    cell.seatZone = 'PROMO';
  }

  handleWheelchairClicked(cell: any) {
    cell.seatZone = 'WHEELCHAIR';
  }

  handleCorridorClicked(cell: any) {
    cell.seatZone = 'Corridor';
  }

  createGridOfSeats() {
    if (this.currentEditedContactDetailIndex >= 0) {
      const numberOfRowsInOldScreeningRoom = this.currentScreeningRoom.length;
      const numberOfColumnsInOldScreeningRoom = this.currentScreeningRoom[0].length;

      const newNumberOfRows = this.rowsNumberControl.value;
      const newNumberOfColumns = this.columnsNumberControl.value;

      let actualNumberOfNewRows = 0;
      let actualNumberOfNewColumns = 0;

      if (newNumberOfRows && newNumberOfColumns) {
        actualNumberOfNewRows = newNumberOfRows - numberOfRowsInOldScreeningRoom;
        actualNumberOfNewColumns = newNumberOfColumns - numberOfColumnsInOldScreeningRoom;

        // Dodanie nowych wierszy
        for (let i = 0; i < actualNumberOfNewRows; i++) {
          const newRow: SeatResponse[] = [];
          for (let j = 0; j < numberOfColumnsInOldScreeningRoom; j++) {
            const newSeat: SeatResponse = {
              seatRow: numberOfRowsInOldScreeningRoom + i + 1,
              seatColumn: j + 1,
              seatZone: 'STANDARD',
              seatType: 'AVAILABLE'
            };
            newRow.push(newSeat);
          }
          this.currentScreeningRoom.push(newRow);
        }

        // Dodanie nowych kolumn do istniejących wierszy
        for (let i = 0; i < numberOfRowsInOldScreeningRoom; i++) {
          const row = this.currentScreeningRoom[i];
          for (let j = 0; j < actualNumberOfNewColumns; j++) {
            const newSeat: SeatResponse = {
              seatRow: i + 1,
              seatColumn: numberOfColumnsInOldScreeningRoom + j + 1,
              seatZone: 'STANDARD',
              seatType: 'AVAILABLE'
            };
            row.push(newSeat);
          }
        }
      }
    }
  }
}
