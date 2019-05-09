import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaptestComponent } from './maptest.component';

describe('MaptestComponent', () => {
  let component: MaptestComponent;
  let fixture: ComponentFixture<MaptestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaptestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaptestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
