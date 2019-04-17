import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonumentDetailsComponent } from './monument-details.component';

describe('MonumentDetailsComponent', () => {
  let component: MonumentDetailsComponent;
  let fixture: ComponentFixture<MonumentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonumentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonumentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
