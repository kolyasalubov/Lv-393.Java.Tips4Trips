import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotVerifiedRoutesPageComponent } from './not-verified-routes-page.component';

describe('NotVerifiedRoutesPageComponent', () => {
  let component: NotVerifiedRoutesPageComponent;
  let fixture: ComponentFixture<NotVerifiedRoutesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotVerifiedRoutesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotVerifiedRoutesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
