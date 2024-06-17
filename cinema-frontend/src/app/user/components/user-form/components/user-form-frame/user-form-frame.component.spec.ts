import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UserFormFrameComponent} from './user-form-frame.component';

describe('CinemaManagerFormFrameComponent', () => {
  let component: UserFormFrameComponent;
  let fixture: ComponentFixture<UserFormFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserFormFrameComponent]
    });
    fixture = TestBed.createComponent(UserFormFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
