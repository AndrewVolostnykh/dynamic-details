import {Injectable} from '@angular/core';
import {DistributionContractOverview} from './distribution-contract-overview';
import {DistributionContract} from '../../app/model/distribution-contract';
import {Detail} from '../../app/model/detail';
import {DetailValueType} from '../../app/model/detail-value-type';

@Injectable({
  providedIn: 'root'
})
export class DistributionContractService {
  contracts: DistributionContract[];


  constructor() {
    let firstContractDetails = [
      new Detail(1, 'FIRST_DETAIL_CODE', 'Just decimal detail', 100000, DetailValueType.DECIMAL, null, null, null),
      new Detail(2, 'THE_SECOND_DETAIL', 'Some text detail', 'Mocked text', DetailValueType.TEXT, null, null, null)
    ]
    let secondContractDetails = [
      new Detail(3, 'DECIMAL_DETAIL', 'Just decimal detail', 100000, DetailValueType.DECIMAL, null, null, null),
      new Detail(4, 'SIMPLE_TEXT_DETAIL', 'Some text detail', 'Mocked text', DetailValueType.TEXT, null, null, null),
      new Detail(5, 'COMPUTABLE DETAIL', 'Text + date detail', 'Some text detail 100000', DetailValueType.COMPUTABLE, null, null, "<SIMPLE_TEXT_DETAIL> + <DECIMAL_DETAIL>.toString()")
    ]
    this.contracts = [
      new DistributionContract(
        1, 'First contract', 0.25, 0.15, 0.01, 200000, new Date('2025-02-28'), new Date('2025-01-01'), 'Some description', firstContractDetails
      ),
      new DistributionContract(
        1, 'First contract', 0.27, 0, 0.2, 30000, new Date('2025-03-31'), new Date('2025-02-01'), 'Some description', secondContractDetails
      ),
      new DistributionContract(
        2, 'Second contract', 0.25, 0.10, 0.2, 5000, new Date('2025-04-30'), new Date('2025-03-01'), 'Some description', []
      ),
      new DistributionContract(
        1, 'Test contract', 0.09, 0, 0.2, 0, new Date('2025-05-15'), new Date('2025-04-01'), 'Some description', []
      )
    ];
  }

  getContractsOverview():DistributionContractOverview[] {
    return this.contracts.map(contract => new DistributionContractOverview(contract.id, contract.name, contract.startDate));
  }

  // remove undefined lol
  getById(id: number): DistributionContractOverview|undefined {
    return this.contracts.find(contract => contract.id = id);
  }
}
