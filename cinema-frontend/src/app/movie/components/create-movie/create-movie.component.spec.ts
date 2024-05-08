import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateMovieComponent} from './create-movie.component';

describe('CreateMovieFormComponent', () => {
  let component: CreateMovieComponent;
  let fixture: ComponentFixture<CreateMovieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateMovieComponent]
    });
    fixture = TestBed.createComponent(CreateMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
