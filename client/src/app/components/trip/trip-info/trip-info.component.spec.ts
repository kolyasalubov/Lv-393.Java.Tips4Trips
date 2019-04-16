import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TripInfoComponent } from './trip-info.component';

describe('TripInfoComponent', () => {
  let component: TripInfoComponent;
  let fixture: ComponentFixture<TripInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TripInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TripInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
