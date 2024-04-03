import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateProjectionTechnologyFormComponent} from './create-projection-technology-form.component';

describe('CreateProjectionTechnologyComponent', () => {
  let component: CreateProjectionTechnologyFormComponent;
  let fixture: ComponentFixture<CreateProjectionTechnologyFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateProjectionTechnologyFormComponent]
    });
    fixture = TestBed.createComponent(CreateProjectionTechnologyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
