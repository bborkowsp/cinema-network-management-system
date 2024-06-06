import {Component, OnInit,} from '@angular/core';
import {ScreeningResponse} from "../../dtos/screening.response";
import {ScreeningService} from "../../services/screening.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-manage-repertory',
  templateUrl: './manage-repertory.component.html',
  styleUrls: ['./manage-repertory.component.scss'],
})
export class ManageRepertoryComponent implements OnInit {
  protected isLoading = true;
  repertory$ !: ScreeningResponse[];

  constructor(
    private readonly repertoryService: ScreeningService,
    private readonly router: Router,
  ) {
  }

  ngOnInit(): void {
    this.getData().subscribe((repertory) => {
      this.repertory$ = repertory;
      this.isLoading = false;
    });
  }

  private getData() {
    return this.repertoryService.getRepertory();
  }

  onDeleteScreening(screening: ScreeningResponse) {
    this.isLoading = true;
    this.repertoryService.deleteScreening(screening.id).subscribe(() => {
      this.getData().subscribe((repertory) => {
        this.repertory$ = repertory;
        this.isLoading = false;
      });
    });
  }

  onEditScreening(screening: ScreeningResponse) {
    const url = `repertory/edit/${screening.id}`;
    this.router.navigateByUrl(url);
  }

  onAddScreening(roomName: string) {
    const url = `repertory/create/${roomName}`;
    this.router.navigateByUrl(url);
  }
}
