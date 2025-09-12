import { TestBed } from '@angular/core/testing';

import { RhService } from './rh-service.service';

describe('RhServiceService', () => {
  let service: RhService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RhService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
