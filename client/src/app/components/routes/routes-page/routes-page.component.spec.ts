import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutesPageComponent } from './routes-page.component';

describe('RoutesPageComponent', () => {
  let component: RoutesPageComponent;
  let fixture: ComponentFixture<RoutesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoutesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
