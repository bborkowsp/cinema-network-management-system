import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionsTableButtonComponent } from './options-table-button.component';

describe('OptionsTableButtonComponent', () => {
  let component: OptionsTableButtonComponent;
  let fixture: ComponentFixture<OptionsTableButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OptionsTableButtonComponent]
    });
    fixture = TestBed.createComponent(OptionsTableButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
