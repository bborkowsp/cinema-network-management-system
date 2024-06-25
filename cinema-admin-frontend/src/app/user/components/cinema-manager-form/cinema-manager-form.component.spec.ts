import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaManagerFormComponent } from './cinema-manager-form.component';

describe('CinemaManagerFormComponent', () => {
  let component: CinemaManagerFormComponent;
  let fixture: ComponentFixture<CinemaManagerFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaManagerFormComponent]
    });
    fixture = TestBed.createComponent(CinemaManagerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
