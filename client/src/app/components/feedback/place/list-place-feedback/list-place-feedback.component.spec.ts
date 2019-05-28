import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPlaceFeedbackComponent } from './list-place-feedback.component';

describe('ListPlaceFeedbackComponent', () => {
  let component: ListPlaceFeedbackComponent;
  let fixture: ComponentFixture<ListPlaceFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPlaceFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPlaceFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
