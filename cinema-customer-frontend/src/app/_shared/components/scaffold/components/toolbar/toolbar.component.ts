import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output
} from '@angular/core';
import {AuthService} from "../../../../../auth/auth.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToolbarComponent implements OnInit, OnDestroy {
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

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  getAccountRouterLink(): string {
    return this.isLoggedIn ? '/account' : '/login';
  }

  protected logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
