import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackPlaceComponent } from './feedback-place.component';

describe('FeedbackPlaceComponent', () => {
  let component: FeedbackPlaceComponent;
  let fixture: ComponentFixture<FeedbackPlaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeedbackPlaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
