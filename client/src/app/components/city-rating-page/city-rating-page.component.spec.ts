import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityRatingPageComponent } from './city-rating-page.component';

describe('CityRatingPageComponent', () => {
  let component: CityRatingPageComponent;
  let fixture: ComponentFixture<CityRatingPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityRatingPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityRatingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
