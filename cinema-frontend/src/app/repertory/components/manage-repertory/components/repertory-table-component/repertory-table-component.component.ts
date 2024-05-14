import {Component, Input} from '@angular/core';
import {ScreeningResponse} from "../../../../dtos/screening.response";

@Component({
  selector: 'app-repertory-table-component',
  templateUrl: './repertory-table-component.component.html',
  styleUrls: ['./repertory-table-component.component.scss']
})
export class RepertoryTableComponentComponent {
  @Input() repertory!: ScreeningResponse[];
}
