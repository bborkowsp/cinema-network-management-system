import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-page-content-container',
  templateUrl: './page-content-container.component.html',
  styleUrls: ['./page-content-container.component.scss']
})
export class PageContentContainerComponent {
  @Input({required: true}) pageTitle!: string;
  @Input() isLoading = false;
  @Input() hasCreateNewButton = false;
  @Input() buttonMatToolTip = '';
  @Input() routerLink = '';
  @Input() hasDetailsActionButtons = false;
  @Input() buttonEditMatToolTip = '';
  @Input() buttonDeleteMatToolTip = '';

  @Input() floatingButtonDeleteAriaLabel = '';
  @Output() floatingButtonDeleteAction: EventEmitter<void> = new EventEmitter();
  @Output() floatingButtonEditAction: EventEmitter<void> = new EventEmitter();
  @Input() floatingButtonEditAriaLabel = '';

}
