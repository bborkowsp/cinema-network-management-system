import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StepsContainerComponent } from './steps-container.component';

describe('StepsContainerComponent', () => {
  let component: StepsContainerComponent;
  let fixture: ComponentFixture<StepsContainerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StepsContainerComponent]
    });
    fixture = TestBed.createComponent(StepsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
