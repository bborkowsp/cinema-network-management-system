import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ScreeningDetailsComponent} from './screening-details.component';

describe('MovieDetailsComponent', () => {
  let component: ScreeningDetailsComponent;
  let fixture: ComponentFixture<ScreeningDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScreeningDetailsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ScreeningDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
