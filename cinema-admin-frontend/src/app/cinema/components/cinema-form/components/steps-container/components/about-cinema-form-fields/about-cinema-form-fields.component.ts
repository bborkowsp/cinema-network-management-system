import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-about-cinema-form-fields',
  templateUrl: './about-cinema-form-fields.component.html',
  styleUrls: ['./about-cinema-form-fields.component.scss']
})
export class AboutCinemaFormFieldsComponent implements OnInit {
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

    this.imageControl.valueChanges.subscribe((value: any) => {
      if (value) {
        if (value instanceof File)
          this.selectedFileName = value.name
        else
          this.selectedFileName = value
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
