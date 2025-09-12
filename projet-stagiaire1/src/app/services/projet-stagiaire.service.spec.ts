import { TestBed } from '@angular/core/testing';

import { ProjetStagiaireService } from './projet-stagiaire.service';

describe('ProjetStagiaireService', () => {
  let service: ProjetStagiaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjetStagiaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
