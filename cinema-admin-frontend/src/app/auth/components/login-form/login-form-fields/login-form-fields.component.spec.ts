import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginFormFieldsComponent} from './login-form-fields.component';

describe('LoginMainFormFrameComponent', () => {
  let component: LoginFormFieldsComponent;
  let fixture: ComponentFixture<LoginFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginFormFieldsComponent]
    });
    fixture = TestBed.createComponent(LoginFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
