import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BigPostComponent } from './big-post.component';

describe('BigPostComponent', () => {
  let component: BigPostComponent;
  let fixture: ComponentFixture<BigPostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BigPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BigPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
