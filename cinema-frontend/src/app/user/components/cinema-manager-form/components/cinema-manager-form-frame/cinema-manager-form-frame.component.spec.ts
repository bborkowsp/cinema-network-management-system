import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaManagerFormFrameComponent } from './cinema-manager-form-frame.component';

describe('CinemaManagerFormFrameComponent', () => {
  let component: CinemaManagerFormFrameComponent;
  let fixture: ComponentFixture<CinemaManagerFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaManagerFormFrameComponent]
    });
    fixture = TestBed.createComponent(CinemaManagerFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
