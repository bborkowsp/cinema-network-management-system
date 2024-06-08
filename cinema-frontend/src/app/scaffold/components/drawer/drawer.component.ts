import {ChangeDetectionStrategy, Component, Input} from '@angular/core';

export interface NavigationLink {
  label: string;
  path: string;
  icon: string;
}

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DrawerComponent {
  @Input({required: true}) navigationLinks!: NavigationLink[];
  @Input({required: true}) drawerOpened!: boolean;
}
