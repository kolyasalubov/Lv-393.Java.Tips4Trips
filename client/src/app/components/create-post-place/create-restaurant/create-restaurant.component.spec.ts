import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRestaurantComponent } from './create-restaurant.component';

describe('CreateRestaurantComponent', () => {
  let component: CreateRestaurantComponent;
  let fixture: ComponentFixture<CreateRestaurantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateRestaurantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
