import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditRouteComponent } from './edit-route.component';

describe('EditRouteComponent', () => {
  let component: EditRouteComponent;
  let fixture: ComponentFixture<EditRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
