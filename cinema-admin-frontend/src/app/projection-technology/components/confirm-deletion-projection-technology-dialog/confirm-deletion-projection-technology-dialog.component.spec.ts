import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ConfirmDeletionProjectionTechnologyDialog} from './confirm-deletion-projection-technology-dialog.component';

describe('ConfirmDeletionProjectionTechnologyDialogComponent', () => {
  let component: ConfirmDeletionProjectionTechnologyDialog;
  let fixture: ComponentFixture<ConfirmDeletionProjectionTechnologyDialog>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmDeletionProjectionTechnologyDialog]
    });
    fixture = TestBed.createComponent(ConfirmDeletionProjectionTechnologyDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
