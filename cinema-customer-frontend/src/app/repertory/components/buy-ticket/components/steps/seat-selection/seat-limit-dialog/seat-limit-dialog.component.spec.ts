import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatLimitDialogComponent } from './seat-limit-dialog.component';

describe('SeatLimitDialogComponent', () => {
  let component: SeatLimitDialogComponent;
  let fixture: ComponentFixture<SeatLimitDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SeatLimitDialogComponent]
    });
    fixture = TestBed.createComponent(SeatLimitDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
