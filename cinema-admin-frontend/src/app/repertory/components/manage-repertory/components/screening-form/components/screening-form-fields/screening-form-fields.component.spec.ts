import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ScreeningFormFieldsComponent} from './screening-form-fields.component';

describe('ScreeningFormFrameComponent', () => {
  let component: ScreeningFormFieldsComponent;
  let fixture: ComponentFixture<ScreeningFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ScreeningFormFieldsComponent]
    });
    fixture = TestBed.createComponent(ScreeningFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
