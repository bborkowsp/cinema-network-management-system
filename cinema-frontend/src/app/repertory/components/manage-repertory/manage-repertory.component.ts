import {Component, OnInit,} from '@angular/core';
import {ScreeningResponse} from "../../dtos/screening.response";
import {ScreeningService} from "../../services/screening.service";


@Component({
  selector: 'app-manage-repertory',
  templateUrl: './manage-repertory.component.html',
  styleUrls: ['./manage-repertory.component.scss'],
})
export class ManageRepertoryComponent implements OnInit {
  protected isLoading = true;
  repertory$ !: ScreeningResponse[];
  screeningsGroupedByScreeningRoom: { [key: string]: ScreeningResponse[] } = {};
  displayedColumns = ['movie', 'startTime', 'endTime'];

  constructor(
    private readonly repertoryService: ScreeningService,
  ) {
  }

  ngOnInit(): void {
    this.getData().subscribe((repertory) => {
      this.repertory$ = repertory;
      this.isLoading = false;
      console.log(this.repertory$);
      this.groupScreeningsByScreeningRoom();
    });
  }

  private groupScreeningsByScreeningRoom() {
    this.screeningsGroupedByScreeningRoom = {};
    this.repertory$.forEach((screening: ScreeningResponse) => {
      const roomName = screening.screeningRoom.name;
      if (!this.screeningsGroupedByScreeningRoom[roomName]) {
        this.screeningsGroupedByScreeningRoom[roomName] = [];
      }
      this.screeningsGroupedByScreeningRoom[roomName].push(screening);
    });
  }


  private getData() {
    return this.repertoryService.getRepertory();
  }

  protected readonly Object = Object;
}
