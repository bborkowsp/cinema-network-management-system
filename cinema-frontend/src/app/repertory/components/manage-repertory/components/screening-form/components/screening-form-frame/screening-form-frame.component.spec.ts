import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreeningFormFrameComponent } from './screening-form-frame.component';

describe('ScreeningFormFrameComponent', () => {
  let component: ScreeningFormFrameComponent;
  let fixture: ComponentFixture<ScreeningFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ScreeningFormFrameComponent]
    });
    fixture = TestBed.createComponent(ScreeningFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
