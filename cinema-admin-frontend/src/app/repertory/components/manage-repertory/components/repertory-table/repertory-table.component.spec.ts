import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RepertoryTableComponent} from './repertory-table.component';

describe('RepertoryTableComponentComponent', () => {
  let component: RepertoryTableComponent;
  let fixture: ComponentFixture<RepertoryTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RepertoryTableComponent]
    });
    fixture = TestBed.createComponent(RepertoryTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
