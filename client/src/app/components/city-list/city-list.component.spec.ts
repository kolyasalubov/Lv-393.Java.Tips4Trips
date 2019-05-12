import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityListComponent } from './city-list.component';

describe('CityListComponent', () => {
  let component: CityListComponent;
  let fixture: ComponentFixture<CityListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
