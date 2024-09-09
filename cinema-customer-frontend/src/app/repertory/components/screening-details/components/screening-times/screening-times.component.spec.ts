import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreeningTimesComponent } from './screening-times.component';

describe('ScreeningTimesComponent', () => {
  let component: ScreeningTimesComponent;
  let fixture: ComponentFixture<ScreeningTimesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScreeningTimesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScreeningTimesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
