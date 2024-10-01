import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AboutCinemaFormFieldsComponent} from "./about-cinema-form-fields.component";


describe('AboutCinemaComponent', () => {
  let component: AboutCinemaFormFieldsComponent;
  let fixture: ComponentFixture<AboutCinemaFormFieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AboutCinemaFormFieldsComponent]
    });
    fixture = TestBed.createComponent(AboutCinemaFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
