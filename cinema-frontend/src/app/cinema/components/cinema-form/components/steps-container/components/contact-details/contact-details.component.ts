import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {ContactDetailsRequest} from "../../../../../../dtos/request/contact-details.request";
import {CreateContactTypeRequest} from "../../../../../../dtos/request/create-contact-type.request";

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.scss']
})
export class ContactDetailsComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  currentDepartment = new FormControl('');
  currentEmail = new FormControl('');
  currentPhoneNumber = new FormControl('');
  allContactDetails: ContactDetailsRequest[] = [];

  get contactDetailsControl(): FormControl {
    return this.formGroup.get('contactDetails') as FormControl;
  }

  saveContactRequest() {
    const email = this.currentEmail.value ?? '';
    const phoneNumber = this.currentPhoneNumber.value ?? '';
    const contactType = new CreateContactTypeRequest(email, phoneNumber);
    const departmentValue = this.currentDepartment.value ?? '';
    this.allContactDetails.push(new ContactDetailsRequest(departmentValue, contactType));
    this.contactDetailsControl.setValue(this.allContactDetails);
  }

  editContact(i: number) {
    this.currentDepartment.setValue(this.allContactDetails[i].department);
    this.currentEmail.setValue(this.allContactDetails[i].contactType.email);
    this.currentPhoneNumber.setValue(this.allContactDetails[i].contactType.phoneNumber);
  }

  deleteContact(i: number) {
    this.allContactDetails.splice(i, 1);
    this.contactDetailsControl.setValue(this.allContactDetails);
  }
}
