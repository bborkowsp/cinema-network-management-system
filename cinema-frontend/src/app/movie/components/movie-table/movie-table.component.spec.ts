import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MovieTableComponent} from './movie-table.component';

describe('MovieListComponent', () => {
  let component: MovieTableComponent;
  let fixture: ComponentFixture<MovieTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieTableComponent]
    });
    fixture = TestBed.createComponent(MovieTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
