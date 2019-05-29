import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapPickerComponent } from './map-picker.component';

describe('MapPickerComponent', () => {
  let component: MapPickerComponent;
  let fixture: ComponentFixture<MapPickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapPickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
