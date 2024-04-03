import {Component, Input} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-about-cinema',
  templateUrl: './about-cinema.component.html',
  styleUrls: ['./about-cinema.component.scss']
})
export class AboutCinemaComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input() nameControl!: FormControl;
  @Input() descriptionControl!: FormControl;
}
