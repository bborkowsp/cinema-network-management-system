import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-image-and-trailer-form-frame-component',
  templateUrl: './image-and-trailer-form-frame-component.component.html',
  styleUrls: ['./image-and-trailer-form-frame-component.component.scss']
})
export class ImageAndTrailerFormFrameComponentComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  selectedFileName: string | null = null;

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

  get imageControl(): FormControl {
    return this.formGroup.get('image') as FormControl;
  }

  get trailerControl(): FormControl {
    return this.formGroup.get('trailer') as FormControl;
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.selectedFileName = file.name;
      this.imageControl.setValue(file);
    }
  }
}
