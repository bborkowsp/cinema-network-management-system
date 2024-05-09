import {ChangeDetectorRef, Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {ContactDetailsRequest} from "../../../../dtos/request/contact-details.request";
import {CreateContactTypeRequest} from "../../../../dtos/request/create-contact-type.request";

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.scss']
})
export class ContactDetailsComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  currentDepartment: string = '';
  currentEmail: string = '';
  currentPhoneNumber: string = '';
  allContactDetails: ContactDetailsRequest[] = [];

  constructor(private changeDetectorRef: ChangeDetectorRef) {
  }

  get contactDetailsControl(): FormControl {
    return this.formGroup.get('contactDetails') as FormControl;
  }

  saveContactRequest() {
    const contactType = new CreateContactTypeRequest(this.currentEmail, this.currentPhoneNumber);
    this.allContactDetails.push(new ContactDetailsRequest(this.currentDepartment, contactType));
    this.contactDetailsControl.setValue(this.allContactDetails);
  }

  editContact(i: number) {
    this.currentDepartment = this.allContactDetails[i].department;
    this.currentEmail = this.allContactDetails[i].contactType.email;
    this.currentPhoneNumber = this.allContactDetails[i].contactType.phoneNumber;
  }

  deleteContact(i: number) {
    this.allContactDetails.splice(i, 1);
    this.contactDetailsControl.setValue(this.allContactDetails);
  }
}
