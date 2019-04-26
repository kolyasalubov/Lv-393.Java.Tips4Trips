import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LikeAccountComponent } from './like-account.component';

describe('LikeAccountComponent', () => {
  let component: LikeAccountComponent;
  let fixture: ComponentFixture<LikeAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LikeAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LikeAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
