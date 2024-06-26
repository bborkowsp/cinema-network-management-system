import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepertoryComponent } from './repertory.component';

describe('RepertoryComponent', () => {
  let component: RepertoryComponent;
  let fixture: ComponentFixture<RepertoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RepertoryComponent]
    });
    fixture = TestBed.createComponent(RepertoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
