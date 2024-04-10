import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutCinemaComponent } from './about-cinema.component';

describe('AboutCinemaComponent', () => {
  let component: AboutCinemaComponent;
  let fixture: ComponentFixture<AboutCinemaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AboutCinemaComponent]
    });
    fixture = TestBed.createComponent(AboutCinemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
