import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-page-content-container',
  templateUrl: './page-content-container.component.html',
  styleUrls: ['./page-content-container.component.scss']
})
export class PageContentContainerComponent {
  @Input({required: true}) pageTitle!: string;
  @Input() isLoading = false;
}
