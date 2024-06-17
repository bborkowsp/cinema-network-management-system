import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-generic-details-field',
  templateUrl: './generic-details-field.component.html',
  styleUrls: ['./generic-details-field.component.scss']
})
export class GenericDetailsFieldComponent {
  @Input({required: true}) title!: string;
  @Input({required: true}) content!: string | number | Date | string[];
}
