import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaListComponent } from './cinema-list.component';

describe('CinemaListComponent', () => {
  let component: CinemaListComponent;
  let fixture: ComponentFixture<CinemaListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaListComponent]
    });
    fixture = TestBed.createComponent(CinemaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
