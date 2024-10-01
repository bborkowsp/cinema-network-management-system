import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MovieVariantsFormFieldsComponent} from './movie-variants-form-fields.component';

describe('MovieVariantsFormFrameComponentComponent', () => {
  let component: MovieVariantsFormFieldsComponent;
  let fixture: ComponentFixture<MovieVariantsFormFieldsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieVariantsFormFieldsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(MovieVariantsFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
