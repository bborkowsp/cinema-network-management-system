import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCinemaFormComponent } from './edit-cinema-form.component';

describe('EditCinemaFormComponent', () => {
  let component: EditCinemaFormComponent;
  let fixture: ComponentFixture<EditCinemaFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditCinemaFormComponent]
    });
    fixture = TestBed.createComponent(EditCinemaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
