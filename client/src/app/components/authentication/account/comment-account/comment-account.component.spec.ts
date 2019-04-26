import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentAccountComponent } from './comment-account.component';

describe('CommentAccountComponent', () => {
  let component: CommentAccountComponent;
  let fixture: ComponentFixture<CommentAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
