import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ConfirmDeletionCinemaDialog} from "./confirm-deletion-cinema-dialog.component";


describe('ConfirmDeletionCinemaDialog', () => {
  let component: ConfirmDeletionCinemaDialog;
  let fixture: ComponentFixture<ConfirmDeletionCinemaDialog>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmDeletionCinemaDialog]
    });
    fixture = TestBed.createComponent(ConfirmDeletionCinemaDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
