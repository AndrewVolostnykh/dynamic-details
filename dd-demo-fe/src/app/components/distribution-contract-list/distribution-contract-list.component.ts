import { Component } from '@angular/core';
import {DistributionContractOverview} from '../../../services/distribution-contract-service/distribution-contract-overview';
import {DistributionContractService} from '../../../services/distribution-contract-service/distribution-contract.service';
import {RouterLink} from '@angular/router';
import {NgForOf} from '@angular/common';
import {MatCard, MatCardContent} from '@angular/material/card';

@Component({
  selector: 'app-distribution-contract-list',
  standalone: true,
  imports: [
    MatCardContent,
    MatCard,
    NgForOf,
    RouterLink
  ],
  templateUrl: './distribution-contract-list.component.html',
  styleUrl: './distribution-contract-list.component.css'
})
export class DistributionContractListComponent {
  contracts: DistributionContractOverview[];

  constructor(distributionContractService: DistributionContractService) {
    this.contracts = distributionContractService.getContractsOverview();
  }

}
