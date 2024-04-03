import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProjectionTechnologyMainFormFrameComponent } from './create-projection-technology-main-form-frame.component';

describe('CreateProjectionTechnologyMainFormFrameComponent', () => {
  let component: CreateProjectionTechnologyMainFormFrameComponent;
  let fixture: ComponentFixture<CreateProjectionTechnologyMainFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateProjectionTechnologyMainFormFrameComponent]
    });
    fixture = TestBed.createComponent(CreateProjectionTechnologyMainFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
