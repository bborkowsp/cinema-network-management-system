import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RepertoryListComponentComponent} from './repertory-list-component.component';

describe('RepertoryTableComponentComponent', () => {
  let component: RepertoryListComponentComponent;
  let fixture: ComponentFixture<RepertoryListComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RepertoryListComponentComponent]
    });
    fixture = TestBed.createComponent(RepertoryListComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
