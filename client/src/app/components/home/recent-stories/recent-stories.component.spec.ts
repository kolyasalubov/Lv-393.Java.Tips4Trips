import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentStoriesComponent } from './recent-stories.component';

describe('RecentStoriesComponent', () => {
  let component: RecentStoriesComponent;
  let fixture: ComponentFixture<RecentStoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecentStoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecentStoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
