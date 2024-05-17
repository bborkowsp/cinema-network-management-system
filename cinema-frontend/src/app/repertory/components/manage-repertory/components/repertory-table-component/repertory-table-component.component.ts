// import {Component, Input, OnInit} from '@angular/core';
// import {ScreeningResponse} from "../../../../dtos/screening.response";
//
// @Component({
//   selector: 'app-repertory-table-component',
//   templateUrl: './repertory-table-component.component.html',
//   styleUrls: ['./repertory-table-component.component.scss']
// })
// export class RepertoryTableComponentComponent implements OnInit {
//   @Input() repertory!: ScreeningResponse[];
//   displayedColumns = ['movie', 'startTime', 'endTime'];
//   screeningResponses: ScreeningResponse[] = [];
//   screeningsGroupedByScreeningRoom: { [key: string]: ScreeningResponse[] } = {};
//
//
//   ngOnInit() {
//     this.groupScreeningsByScreeningRoom();
//     console.log(this.repertory);
//     console.log("RepertoryTableComponentComponent")
//   }
//
//   private groupScreeningsByScreeningRoom() {
//     this.screeningsGroupedByScreeningRoom = {};
//     this.screeningResponses.forEach((screening: ScreeningResponse) => {
//       const roomName = screening.screeningRoom.name;
//       if (!this.screeningsGroupedByScreeningRoom[roomName]) {
//         this.screeningsGroupedByScreeningRoom[roomName] = [];
//       }
//       this.screeningsGroupedByScreeningRoom[roomName].push(screening);
//     });
//   }
//
//   protected readonly Object = Object;
// }
