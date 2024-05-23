import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormControl, FormGroupDirective, NgForm} from "@angular/forms";
import {ContactDetailsResponse} from "../../../../../../dtos/response/contact-details.response";
import {ContactTypeResponse} from "../../../../../../dtos/response/contact-type.response";

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

  get currentDepartmentControl(): FormControl | null {
    if (
      this.currentEditedContactDetailIndex >= 0 &&
      this.currentEditedContactDetailIndex < this.formArray.length
    ) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('department') as FormControl;
    } else {
      return null;
    }
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

  protected deleteContact(i: number) {
    this.formArray.removeAt(i);
    this.updateContactDetails();
    this.currentEditedContactDetailIndex = -1;
  }

  protected saveContactDetail() {
    const contactDetailsResponse = new ContactDetailsResponse(
      this.currentDepartmentControl?.value,
      new ContactTypeResponse(
        this.currentEmailControl?.value,
        this.currentPhoneNumberControl?.value
      )
    );
    if (this.currentEditedContactDetailIndex === -1) {
      this.formArray.value.push(contactDetailsResponse);
      this.updateContactDetails();
    } else {
      this.formArray.at(this.currentEditedContactDetailIndex).patchValue(contactDetailsResponse);
      this.updateContactDetails();
      this.currentEditedContactDetailIndex = -1;
    }
  }

  private updateContactDetails() {
    this.allContactDetails = this.formArray.value as ContactDetailsResponse[];
  }
}
