import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityPlacesComponent } from './city-places.component';

describe('CityPlacesComponent', () => {
  let component: CityPlacesComponent;
  let fixture: ComponentFixture<CityPlacesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityPlacesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityPlacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
