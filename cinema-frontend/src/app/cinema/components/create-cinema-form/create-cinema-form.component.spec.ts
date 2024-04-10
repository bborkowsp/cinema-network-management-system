import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateCinemaFormComponent} from './create-cinema-form.component';

describe('CinemaFormComponent', () => {
  let component: CreateCinemaFormComponent;
  let fixture: ComponentFixture<CreateCinemaFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateCinemaFormComponent]
    });
    fixture = TestBed.createComponent(CreateCinemaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
