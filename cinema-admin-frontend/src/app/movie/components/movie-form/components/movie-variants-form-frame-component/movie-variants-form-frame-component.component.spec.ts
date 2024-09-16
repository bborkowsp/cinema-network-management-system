import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieVariantsFormFrameComponentComponent } from './movie-variants-form-frame-component.component';

describe('MovieVariantsFormFrameComponentComponent', () => {
  let component: MovieVariantsFormFrameComponentComponent;
  let fixture: ComponentFixture<MovieVariantsFormFrameComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieVariantsFormFrameComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieVariantsFormFrameComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
