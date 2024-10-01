import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContactDetailsFormFieldsComponent} from './contact-details-form-fields.component';

describe('ContactDetailsComponent', () => {
  let component: ContactDetailsFormFieldsComponent;
  let fixture: ComponentFixture<ContactDetailsFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactDetailsFormFieldsComponent]
    });
    fixture = TestBed.createComponent(ContactDetailsFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
