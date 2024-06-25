import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRepertoryComponent } from './manage-repertory.component';

describe('ManageRepertoryComponent', () => {
  let component: ManageRepertoryComponent;
  let fixture: ComponentFixture<ManageRepertoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageRepertoryComponent]
    });
    fixture = TestBed.createComponent(ManageRepertoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
