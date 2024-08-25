import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageBottomSectionComponent } from './page-bottom-section.component';

describe('PageBottomSectionComponent', () => {
  let component: PageBottomSectionComponent;
  let fixture: ComponentFixture<PageBottomSectionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageBottomSectionComponent]
    });
    fixture = TestBed.createComponent(PageBottomSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
