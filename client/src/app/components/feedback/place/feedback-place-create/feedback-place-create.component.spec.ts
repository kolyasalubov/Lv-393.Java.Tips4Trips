import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackPlaceCreateComponent } from './feedback-place-create.component';

describe('FeedbackPlaceCreateComponent', () => {
  let component: FeedbackPlaceCreateComponent;
  let fixture: ComponentFixture<FeedbackPlaceCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeedbackPlaceCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackPlaceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
