import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelPaypalPaymentComponent } from './cancel-paypal-payment.component';

describe('CancelPaypalPaymentComponent', () => {
  let component: CancelPaypalPaymentComponent;
  let fixture: ComponentFixture<CancelPaypalPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CancelPaypalPaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CancelPaypalPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
