import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
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
  createContactDetailFormGroup$!: FormGroup;
  currentEditedContactDetailIndex: number = -1;

  ngOnInit() {
    this.updateContactDetails();
    this.createContactDetailFormGroup$ = this.createContactDetailFormGroup();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['formArray']) {
      this.updateContactDetails();
    }
  }

  get currentDepartmentControl(): FormControl | null {
    console.log("currentindex", this.currentEditedContactDetailIndex)
    if (
      this.currentEditedContactDetailIndex >= 0
    ) {
      console.log(this.formArray.at(this.currentEditedContactDetailIndex).get('department'));
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('department') as FormControl;
    } else {
      console.log("currentDepartmentControl is null")
      return null;
    }
  }

  get currentEmailControl(): FormControl | null {
    if (
      this.currentEditedContactDetailIndex >= 0
    ) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('contactType')?.get('email') as FormControl;
    }
    return null;
  }

  get currentPhoneNumberControl(): FormControl | null {
    if (
      this.currentEditedContactDetailIndex >= 0
    ) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('contactType')?.get('phoneNumber') as FormControl;
    }
    return null;
  }

  get createDepartment(): FormControl {
    return this.createContactDetailFormGroup$.get('department') as FormControl;
  }

  get createEmail(): FormControl {
    return this.createContactDetailFormGroup$.get('contactType')?.get('email') as FormControl;
  }

  get createPhoneNumber(): FormControl {
    return this.createContactDetailFormGroup$.get('contactType')?.get('phoneNumber') as FormControl;
  }

  protected editContact(i: number) {
    console.log('editContact', i);
    this.currentEditedContactDetailIndex = i;
  }

  protected deleteContact(i: number) {
    console.log('deleteContact', i);
    this.formArray.removeAt(i);
    this.updateContactDetails();
    this.currentEditedContactDetailIndex = -1;
  }

  protected saveContactDetail() {
    if (this.currentEditedContactDetailIndex === -1) {
      const contactDetailsResponse = new FormGroup(
        {
          department: new FormControl(this.createDepartment.value),
          contactType: new FormGroup({
            email: new FormControl(this.createEmail.value),
            phoneNumber: new FormControl(this.createPhoneNumber.value)
          })
        }
      )
      this.formArray.push(contactDetailsResponse);
      console.log(this.formArray.value);
      this.updateContactDetails();
      this.currentEditedContactDetailIndex = -1;
      this.createContactDetailFormGroup$.reset();

    } else {
      const contactDetailsResponse = new ContactDetailsResponse(
        this.currentDepartmentControl?.value,
        new ContactTypeResponse(
          this.currentEmailControl?.value,
          this.currentPhoneNumberControl?.value
        )
      );
      this.formArray.at(this.currentEditedContactDetailIndex).patchValue(contactDetailsResponse);
      this.updateContactDetails();
      this.currentEditedContactDetailIndex = -1;
    }
  }

  private updateContactDetails() {
    this.allContactDetails = this.formArray.value as ContactDetailsResponse[];
  }

  private createContactDetailFormGroup(): FormGroup {
    return new FormGroup({
      department: new FormControl(''),
      contactType: new FormGroup({
        email: new FormControl(''),
        phoneNumber: new FormControl('')
      })
    });
  }

}
