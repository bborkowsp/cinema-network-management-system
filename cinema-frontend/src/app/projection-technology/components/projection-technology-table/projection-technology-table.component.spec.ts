import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ProjectionTechnologyTableComponent} from './projection-technology-table.component';

describe('ProjectionTechnologyListComponent', () => {
  let component: ProjectionTechnologyTableComponent;
  let fixture: ComponentFixture<ProjectionTechnologyTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectionTechnologyTableComponent]
    });
    fixture = TestBed.createComponent(ProjectionTechnologyTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
