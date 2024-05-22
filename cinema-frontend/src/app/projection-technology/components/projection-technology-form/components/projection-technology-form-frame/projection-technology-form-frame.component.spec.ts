import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectionTechnologyFormFrameComponent } from './projection-technology-form-frame.component';

describe('ProjectionTechnologyFormFrameComponent', () => {
  let component: ProjectionTechnologyFormFrameComponent;
  let fixture: ComponentFixture<ProjectionTechnologyFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectionTechnologyFormFrameComponent]
    });
    fixture = TestBed.createComponent(ProjectionTechnologyFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
