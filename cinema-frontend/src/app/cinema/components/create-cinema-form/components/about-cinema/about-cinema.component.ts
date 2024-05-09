import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-about-cinema',
  templateUrl: './about-cinema.component.html',
  styleUrls: ['./about-cinema.component.scss']
})
export class AboutCinemaComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;


  get nameControl(): FormControl {
    return this.formGroup.get('aboutCinema')?.get('name') as FormControl;
  }

  get descriptionControl(): FormControl {
    return this.formGroup.get('aboutCinema')?.get('description') as FormControl;
  }
}
