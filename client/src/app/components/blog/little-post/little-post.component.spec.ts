import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LittlePostComponent } from './little-post.component';

describe('LittlePostComponent', () => {
  let component: LittlePostComponent;
  let fixture: ComponentFixture<LittlePostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LittlePostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LittlePostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
