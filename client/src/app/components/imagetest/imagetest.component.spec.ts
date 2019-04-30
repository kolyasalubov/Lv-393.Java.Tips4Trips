import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagetestComponent } from './imagetest.component';

describe('ImagetestComponent', () => {
  let component: ImagetestComponent;
  let fixture: ComponentFixture<ImagetestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImagetestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImagetestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
