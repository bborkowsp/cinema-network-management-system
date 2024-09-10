import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-cancel-paypal-payment',
  templateUrl: './cancel-paypal-payment.component.html',
  styleUrl: './cancel-paypal-payment.component.scss'
})
export class CancelPaypalPaymentComponent implements OnInit {
  returnLink: string = '/repertory';

  constructor(private router: Router) {
  }

  ngOnInit() {
    const linkFromLocalStorage = this.getReturnLinkFromLocalStorage();
    if (linkFromLocalStorage) {
      this.returnLink = linkFromLocalStorage;
    }
  }

  navigateAndReload() {
    this.router.navigate([this.returnLink]).then(() => {
      window.location.reload();
    });
  }

  private getReturnLinkFromLocalStorage(): string | null {
    return localStorage.getItem('returnLink');
  }
}
