import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectionTechnologyListComponent } from './projection-technology-list.component';

describe('ProjectionTechnologyListComponent', () => {
  let component: ProjectionTechnologyListComponent;
  let fixture: ComponentFixture<ProjectionTechnologyListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectionTechnologyListComponent]
    });
    fixture = TestBed.createComponent(ProjectionTechnologyListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
