import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectionTechnologyFormComponent } from './projection-technology-form.component';

describe('ProjectionTechnologyFormComponent', () => {
  let component: ProjectionTechnologyFormComponent;
  let fixture: ComponentFixture<ProjectionTechnologyFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectionTechnologyFormComponent]
    });
    fixture = TestBed.createComponent(ProjectionTechnologyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
