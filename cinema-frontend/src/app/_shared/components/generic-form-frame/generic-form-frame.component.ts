import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-generic-form-frame',
  templateUrl: './generic-form-frame.component.html',
  styleUrls: ['./generic-form-frame.component.scss']
})
export class GenericFormFrameComponent {
  @Input({required: false}) title!: string;
}
