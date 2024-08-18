import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogBuyTicketComponent } from './dialog-buy-ticket.component';

describe('DialogBuyTicketComponent', () => {
  let component: DialogBuyTicketComponent;
  let fixture: ComponentFixture<DialogBuyTicketComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DialogBuyTicketComponent]
    });
    fixture = TestBed.createComponent(DialogBuyTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
