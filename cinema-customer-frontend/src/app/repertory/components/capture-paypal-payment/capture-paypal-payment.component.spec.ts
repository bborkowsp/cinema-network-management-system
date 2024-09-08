import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapturePaypalPaymentComponent } from './capture-paypal-payment.component';

describe('CapturePaypalPaymentComponent', () => {
  let component: CapturePaypalPaymentComponent;
  let fixture: ComponentFixture<CapturePaypalPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CapturePaypalPaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CapturePaypalPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
