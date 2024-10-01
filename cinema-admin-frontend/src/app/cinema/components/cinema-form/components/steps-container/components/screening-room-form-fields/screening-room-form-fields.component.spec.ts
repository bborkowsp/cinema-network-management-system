import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ScreeningRoomFormFieldsComponent} from './screening-room-form-fields.component';

describe('ScreeningRoomComponent', () => {
  let component: ScreeningRoomFormFieldsComponent;
  let fixture: ComponentFixture<ScreeningRoomFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ScreeningRoomFormFieldsComponent]
    });
    fixture = TestBed.createComponent(ScreeningRoomFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
