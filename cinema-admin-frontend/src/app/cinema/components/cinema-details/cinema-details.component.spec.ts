import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaDetailsComponent } from './cinema-details.component';

describe('CinemaDetailsComponent', () => {
  let component: CinemaDetailsComponent;
  let fixture: ComponentFixture<CinemaDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaDetailsComponent]
    });
    fixture = TestBed.createComponent(CinemaDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
