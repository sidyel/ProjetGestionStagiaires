import { TestBed } from '@angular/core/testing';
import {EncadrantService} from "./encadrant-service.service";


describe('EncadrantServiceService', () => {
  let service: EncadrantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncadrantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
