import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnderComponent } from './under.component';

describe('UnderComponent', () => {
  let component: UnderComponent;
  let fixture: ComponentFixture<UnderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
