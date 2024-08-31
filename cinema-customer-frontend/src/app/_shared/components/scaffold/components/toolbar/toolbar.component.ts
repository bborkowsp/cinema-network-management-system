import {ChangeDetectionStrategy, ChangeDetectorRef, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {AuthService} from "../../../../../auth/service/auth.service";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToolbarComponent implements OnInit {
  @Output() toggleDrawerButtonClick = new EventEmitter<void>();
  @Output() logoutButtonClick = new EventEmitter<void>();
  isLoggedIn: boolean = false;
  private authSubscription: Subscription = new Subscription();

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly changeDetectorRef: ChangeDetectorRef
  ) {
  }

  ngOnInit(): void {
    this.authSubscription = this.authService.loggedIn.subscribe(
      (loggedIn: boolean) => {
        this.isLoggedIn = loggedIn;
        this.changeDetectorRef.detectChanges();
      }
    );
  }

  getAccountRouterLink(): string {
    return this.isLoggedIn ? '/account' : '/login';
  }

  protected logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
