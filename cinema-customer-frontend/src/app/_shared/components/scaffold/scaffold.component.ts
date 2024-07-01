import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-scaffold',
  templateUrl: './scaffold.component.html',
  styleUrls: ['./scaffold.component.scss']
})
export class ScaffoldComponent {

  constructor(
    private readonly router: Router,
  ) {
  }

}
