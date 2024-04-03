import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProjectionTechnologyComponent } from './edit-projection-technology.component';

describe('EditProjectionTechnologyComponent', () => {
  let component: EditProjectionTechnologyComponent;
  let fixture: ComponentFixture<EditProjectionTechnologyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditProjectionTechnologyComponent]
    });
    fixture = TestBed.createComponent(EditProjectionTechnologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
