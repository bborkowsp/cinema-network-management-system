import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-image-and-trailer-form-fields-component',
  templateUrl: './image-and-trailer-form-fields.component.html',
  styleUrls: ['./image-and-trailer-form-fields.component.scss']
})
export class ImageAndTrailerFormFieldsComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  selectedFileName: string | null = null;

  get imageControl(): FormControl {
    return this.formGroup.get('image') as FormControl;
  }

  get trailerControl(): FormControl {
    return this.formGroup.get('trailer') as FormControl;
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
