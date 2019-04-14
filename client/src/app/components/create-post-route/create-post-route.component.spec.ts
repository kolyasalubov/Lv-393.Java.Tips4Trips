import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePostRouteComponent } from './create-post-route.component';

describe('CreatePostRouteComponent', () => {
  let component: CreatePostRouteComponent;
  let fixture: ComponentFixture<CreatePostRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePostRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePostRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
