import { TestBed } from '@angular/core/testing';

import { CustomAuthService } from './custom-auth.service';

describe('AuthService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomAuthService = TestBed.get(CustomAuthService);
    expect(service).toBeTruthy();
  });
});
