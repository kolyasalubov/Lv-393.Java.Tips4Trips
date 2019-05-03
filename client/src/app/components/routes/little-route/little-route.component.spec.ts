import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LittleRouteComponent } from './little-route.component';

describe('LittleRouteComponent', () => {
  let component: LittleRouteComponent;
  let fixture: ComponentFixture<LittleRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LittleRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LittleRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
