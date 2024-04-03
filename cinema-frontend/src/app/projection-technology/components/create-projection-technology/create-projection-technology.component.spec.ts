import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProjectionTechnologyComponent } from './create-projection-technology.component';

describe('CreateProjectionTechnologyComponent', () => {
  let component: CreateProjectionTechnologyComponent;
  let fixture: ComponentFixture<CreateProjectionTechnologyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateProjectionTechnologyComponent]
    });
    fixture = TestBed.createComponent(CreateProjectionTechnologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
