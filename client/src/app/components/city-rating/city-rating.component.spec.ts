import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityRatingComponent } from './city-rating.component';

describe('CityRatingComponent', () => {
  let component: CityRatingComponent;
  let fixture: ComponentFixture<CityRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
