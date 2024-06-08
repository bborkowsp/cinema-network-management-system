import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CinemaTableComponent} from './cinema-table.component';

describe('CinemaListComponent', () => {
  let component: CinemaTableComponent;
  let fixture: ComponentFixture<CinemaTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaTableComponent]
    });
    fixture = TestBed.createComponent(CinemaTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
