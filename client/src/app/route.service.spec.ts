import { TestBed } from '@angular/core/testing';

import { RouteService } from './route.service';

describe('RouteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RouteService = TestBed.get(RouteService);
    expect(service).toBeTruthy();
  });
});
