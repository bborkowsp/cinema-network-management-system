import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AddressFormFieldsComponent} from './address-form-fields.component';

describe('AddressComponent', () => {
  let component: AddressFormFieldsComponent;
  let fixture: ComponentFixture<AddressFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddressFormFieldsComponent]
    });
    fixture = TestBed.createComponent(AddressFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
