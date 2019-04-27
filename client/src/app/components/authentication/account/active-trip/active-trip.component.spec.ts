import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveTripComponent } from './active-trip.component';

describe('ActiveTripComponent', () => {
  let component: ActiveTripComponent;
  let fixture: ComponentFixture<ActiveTripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActiveTripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveTripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
