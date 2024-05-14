import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepertoryTableComponentComponent } from './repertory-table-component.component';

describe('RepertoryTableComponentComponent', () => {
  let component: RepertoryTableComponentComponent;
  let fixture: ComponentFixture<RepertoryTableComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RepertoryTableComponentComponent]
    });
    fixture = TestBed.createComponent(RepertoryTableComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
