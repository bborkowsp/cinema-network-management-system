import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginMainFormFrameComponent } from './login-main-form-frame.component';

describe('LoginMainFormFrameComponent', () => {
  let component: LoginMainFormFrameComponent;
  let fixture: ComponentFixture<LoginMainFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginMainFormFrameComponent]
    });
    fixture = TestBed.createComponent(LoginMainFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
