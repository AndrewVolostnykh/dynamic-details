import { TestBed } from '@angular/core/testing';

import { DistributionContractService } from './distribution-contract.service';

describe('DistributionContractService', () => {
  let service: DistributionContractService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DistributionContractService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
