import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
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

  get departmentControl(): FormControl {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('department') as FormControl;
    } else {
      return this.createContactDetailFormGroup$.get('department') as FormControl;
    }
  }

  get emailControl(): FormControl {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('contactType')?.get('email') as FormControl;
    }
    return this.createContactDetailFormGroup$.get('contactType')?.get('email') as FormControl;
  }

  get phoneNumberControl(): FormControl {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex)
        .get('contactType')?.get('phoneNumber') as FormControl;
    }
    return this.createContactDetailFormGroup$.get('contactType')?.get('phoneNumber') as FormControl;
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
    if (this.currentEditedContactDetailIndex === -1) {
      const contactDetailsResponse = new FormGroup(
        {
          department: new FormControl(this.departmentControl.value),
          contactType: new FormGroup({
            email: new FormControl(this.emailControl.value),
            phoneNumber: new FormControl(this.phoneNumberControl.value)
          })
        }
      )
      this.formArray.push(contactDetailsResponse);
      this.updateContactDetails();
      this.currentEditedContactDetailIndex = -1;
      this.createContactDetailFormGroup$.reset();

    } else {
      const contactDetailsResponse = new FormGroup({
        department: new FormControl(this.departmentControl?.value),
        contactType: new FormGroup({
          email: new FormControl(this.emailControl?.value),
          phoneNumber: new FormControl(this.phoneNumberControl?.value)
        })
      });

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
