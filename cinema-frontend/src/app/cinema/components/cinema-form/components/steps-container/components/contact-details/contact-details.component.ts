import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormControl, FormGroupDirective, NgForm} from "@angular/forms";
import {ContactDetailsResponse} from "../../../../../../dtos/response/contact-details.response";

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.scss']
})
export class ContactDetailsComponent implements OnInit, OnChanges {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formArray!: FormArray;
  protected allContactDetails: ContactDetailsResponse[] = [];
  currentEditedContactDetailIndex: number = -1;

  ngOnInit() {
    this.updateContactDetails();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['formArray']) {
      this.updateContactDetails();
    }
  }

  private updateContactDetails() {
    console.log('this.formArray.value', this.formArray.value);
    this.allContactDetails = this.formArray.value as ContactDetailsResponse[];
  }

  get currentDepartmentControl(): FormControl | null {
    if (
      this.currentEditedContactDetailIndex >= 0 &&
      this.currentEditedContactDetailIndex < this.formArray.length
    ) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('department') as FormControl;
    }
    return null;
  }

  get currentEmailControl(): FormControl | null {
    if (
      this.currentEditedContactDetailIndex >= 0 &&
      this.currentEditedContactDetailIndex < this.formArray.length
    ) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('contactType')?.get('email') as FormControl;
    }
    return null;
  }

  get currentPhoneNumberControl(): FormControl | null {
    if (
      this.currentEditedContactDetailIndex >= 0 &&
      this.currentEditedContactDetailIndex < this.formArray.length
    ) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('contactType')?.get('phoneNumber') as FormControl;
    }
    return null;
  }

  protected editContact(i: number) {
    this.currentEditedContactDetailIndex = i;
  }
}
