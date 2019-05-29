import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTripsComponent } from './list-trips.component';

describe('ListTripsComponent', () => {
  let component: ListTripsComponent;
  let fixture: ComponentFixture<ListTripsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListTripsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTripsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
