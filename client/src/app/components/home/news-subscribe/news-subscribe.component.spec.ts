import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsSubscribeComponent } from './news-subscribe.component';

describe('NewsSubscribeComponent', () => {
  let component: NewsSubscribeComponent;
  let fixture: ComponentFixture<NewsSubscribeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsSubscribeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsSubscribeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
