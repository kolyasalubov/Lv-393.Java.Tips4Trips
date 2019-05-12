import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfRoutesComponent } from './list-of-routes.component';

describe('ListOfRoutesComponent', () => {
  let component: ListOfRoutesComponent;
  let fixture: ComponentFixture<ListOfRoutesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOfRoutesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfRoutesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
