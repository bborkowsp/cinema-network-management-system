import {ChangeDetectionStrategy, Component, OnInit,} from '@angular/core';
import {ScreeningResponse} from "../../dtos/screening.response";
import {Observable} from "rxjs";
import {ScreeningService} from "../../services/screening.service";


@Component({
  selector: 'app-manage-repertory',
  templateUrl: './manage-repertory.component.html',
  styleUrls: ['./manage-repertory.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ManageRepertoryComponent implements OnInit {
  protected isLoading = true;
  repertory$ !: Observable<ScreeningResponse[]>;

  constructor(
    private readonly repertoryService: ScreeningService,
  ) {
  }

  ngOnInit(): void {
    this.repertory$ = this.getData();
    this.repertory$.subscribe({
      next: () => {
        this.isLoading = false;
      }, error: (error) => {
        //TODO
      }
    });
  }

  private getData(): Observable<ScreeningResponse[]> {
    return this.repertoryService.getRepertory();
  }
}
