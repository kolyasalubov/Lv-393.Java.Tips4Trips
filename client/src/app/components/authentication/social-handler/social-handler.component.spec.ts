import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialHandlerComponent } from './social-handler.component';

describe('SocialHandlerComponent', () => {
  let component: SocialHandlerComponent;
  let fixture: ComponentFixture<SocialHandlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SocialHandlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialHandlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
