import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaManagerListComponent } from './cinema-manager-list.component';

describe('CinemaManagerListComponent', () => {
  let component: CinemaManagerListComponent;
  let fixture: ComponentFixture<CinemaManagerListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaManagerListComponent]
    });
    fixture = TestBed.createComponent(CinemaManagerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
