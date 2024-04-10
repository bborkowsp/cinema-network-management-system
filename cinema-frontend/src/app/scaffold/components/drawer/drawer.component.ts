import {ChangeDetectionStrategy, Component, Input} from '@angular/core';


export interface NavLink {
  label: string;
  path: string;
}

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DrawerComponent {
  @Input({required: true}) navLinks!: NavLink[];
  @Input({required: true}) opened!: boolean;

}
