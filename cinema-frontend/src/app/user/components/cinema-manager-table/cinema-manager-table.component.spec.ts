import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaManagerTableComponent } from './cinema-manager-table.component';

describe('CinemaManagerTableComponent', () => {
  let component: CinemaManagerTableComponent;
  let fixture: ComponentFixture<CinemaManagerTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaManagerTableComponent]
    });
    fixture = TestBed.createComponent(CinemaManagerTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
