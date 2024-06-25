import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-about-cinema',
  templateUrl: './about-cinema.component.html',
  styleUrls: ['./about-cinema.component.scss']
})
export class AboutCinemaComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  selectedFileName: string | null = null;

  get nameControl(): FormControl {
    return this.formGroup.get('aboutCinema')?.get('name') as FormControl;
  }

  get descriptionControl(): FormControl {
    return this.formGroup.get('aboutCinema')?.get('description') as FormControl;
  }

  get imageControl(): FormControl {
    return this.formGroup.get('aboutCinema')?.get('image') as FormControl;
  }

  ngOnInit(): void {
    const file = this.imageControl.value;
    if (file) {
      this.selectedFileName = file.name;
    }

    this.imageControl.valueChanges.subscribe((value: File) => {
      if (value) {
        this.selectedFileName = value.name;
      } else {
        this.selectedFileName = null;
      }
    });
  }


  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.selectedFileName = file.name;
      this.imageControl.setValue(file);
    }
  }
}
