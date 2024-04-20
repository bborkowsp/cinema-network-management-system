import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMainFormFrameComponent } from './register-main-form-frame.component';

describe('RegisterMainFormFrameComponent', () => {
  let component: RegisterMainFormFrameComponent;
  let fixture: ComponentFixture<RegisterMainFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterMainFormFrameComponent]
    });
    fixture = TestBed.createComponent(RegisterMainFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
