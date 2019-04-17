import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMonumentComponent } from './create-monument.component';

describe('CreateMonumentComponent', () => {
  let component: CreateMonumentComponent;
  let fixture: ComponentFixture<CreateMonumentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMonumentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMonumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
