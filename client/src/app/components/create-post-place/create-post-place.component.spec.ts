import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePostPlaceComponent } from './create-post-place.component';

describe('CreatePostPlaceComponent', () => {
  let component: CreatePostPlaceComponent;
  let fixture: ComponentFixture<CreatePostPlaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePostPlaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePostPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
