import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectionTechnologyDetailsComponent } from './projection-technology-details.component';

describe('ProjectionTechnologyDetailsComponent', () => {
  let component: ProjectionTechnologyDetailsComponent;
  let fixture: ComponentFixture<ProjectionTechnologyDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectionTechnologyDetailsComponent]
    });
    fixture = TestBed.createComponent(ProjectionTechnologyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
