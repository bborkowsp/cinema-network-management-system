import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMovieFormComponent } from './create-movie-form.component';

describe('CreateMovieFormComponent', () => {
  let component: CreateMovieFormComponent;
  let fixture: ComponentFixture<CreateMovieFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateMovieFormComponent]
    });
    fixture = TestBed.createComponent(CreateMovieFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
