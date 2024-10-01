import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CinemaManagerFormFieldsComponent} from './cinema-manager-form-fields.component';

describe('CinemaManagerFormFrameComponent', () => {
  let component: CinemaManagerFormFieldsComponent;
  let fixture: ComponentFixture<CinemaManagerFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CinemaManagerFormFieldsComponent]
    });
    fixture = TestBed.createComponent(CinemaManagerFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
