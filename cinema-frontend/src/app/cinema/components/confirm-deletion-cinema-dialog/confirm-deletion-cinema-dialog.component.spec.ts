import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeletionProjectionTechnologyDialogComponent } from './confirm-deletion-projection-technology-dialog.component';

describe('ConfirmDeletionProjectionTechnologyDialogComponent', () => {
  let component: ConfirmDeletionProjectionTechnologyDialogComponent;
  let fixture: ComponentFixture<ConfirmDeletionProjectionTechnologyDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmDeletionProjectionTechnologyDialogComponent]
    });
    fixture = TestBed.createComponent(ConfirmDeletionProjectionTechnologyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
