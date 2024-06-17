import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-details-section-container',
  templateUrl: './details-section-container.component.html',
  styleUrls: ['./details-section-container.component.scss']
})
export class DetailsSectionContainerComponent {
  @Input() sectionTitle!: string;
}
