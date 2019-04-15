import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LessPostComponent } from './less-post.component';

describe('LessPostComponent', () => {
  let component: LessPostComponent;
  let fixture: ComponentFixture<LessPostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LessPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LessPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
