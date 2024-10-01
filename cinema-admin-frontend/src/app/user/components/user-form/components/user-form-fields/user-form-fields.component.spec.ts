import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UserFormFieldsComponent} from './user-form-fields.component';

describe('CinemaManagerFormFrameComponent', () => {
  let component: UserFormFieldsComponent;
  let fixture: ComponentFixture<UserFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserFormFieldsComponent]
    });
    fixture = TestBed.createComponent(UserFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
