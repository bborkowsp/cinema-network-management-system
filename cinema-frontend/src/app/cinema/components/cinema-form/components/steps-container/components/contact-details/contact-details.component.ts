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
  allContactDetails: ContactDetailsResponse[] = [];
  createContactDetailFormGroup!: FormGroup;
  currentEditedContactDetailIndex: number = -1;

  ngOnInit() {
    this.updateContactDetails();
    this.createContactDetailFormGroup = this.createFormGroup();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['formArray']) {
      this.updateContactDetails();
    }
  }

  get departmentControl(): FormControl {
    return this.getFormControl('department');
  }

  get emailControl(): FormControl {
    return this.getFormControl('contactType', 'email');
  }

  get phoneNumberControl(): FormControl {
    return this.getFormControl('contactType', 'phoneNumber');
  }

  private getFormControl(controlName: string, secondControlName?: string): FormControl {
    const formGroup = this.currentEditedContactDetailIndex >= 0 ?
      this.formArray.at(this.currentEditedContactDetailIndex) : this.createContactDetailFormGroup;

    if (secondControlName) {
      return formGroup.get(controlName)?.get(secondControlName) as FormControl;
    } else {
      return formGroup.get(controlName) as FormControl;
    }
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
    const contactDetailsResponse = this.createFilledContactFormGroup();

    if (this.currentEditedContactDetailIndex === -1) {
      this.formArray.push(contactDetailsResponse);
      this.createContactDetailFormGroup.reset();
    } else {
      this.formArray.at(this.currentEditedContactDetailIndex).patchValue(contactDetailsResponse);
    }
    this.updateContactDetails();
    this.currentEditedContactDetailIndex = -1;
  }

  private updateContactDetails() {
    this.allContactDetails = this.formArray.value as ContactDetailsResponse[];
  }

  private createFormGroup(): FormGroup {
    return new FormGroup({
      department: new FormControl(''),
      contactType: new FormGroup({
        email: new FormControl(''),
        phoneNumber: new FormControl('')
      })
    });
  }

  private createFilledContactFormGroup(): FormGroup {
    return new FormGroup({
      department: new FormControl(this.departmentControl.value),
      contactType: new FormGroup({
        email: new FormControl(this.emailControl.value),
        phoneNumber: new FormControl(this.phoneNumberControl.value)
      })
    });
  }
}
