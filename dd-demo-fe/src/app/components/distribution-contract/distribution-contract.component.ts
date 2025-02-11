import { Component } from '@angular/core';
import {DistributionContractService} from '../../../services/distribution-contract-service/distribution-contract.service';
import {DistributionContract} from '../../model/distribution-contract';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatGridList} from '@angular/material/grid-list';
import {MatChipGrid} from '@angular/material/chips';
import {NgForOf} from '@angular/common';
import {DetailComponent} from '../detail/detail.component';

@Component({
  selector: 'app-distribution-contract',
  standalone: true,
  imports: [
    MatFormField,
    MatLabel,
    MatInput,
    MatGridList,
    MatChipGrid,
    NgForOf,
    DetailComponent,
    // MatButton,
    // RouterLink
  ],
  templateUrl: './distribution-contract.component.html',
  styleUrl: './distribution-contract.component.css'
})
export class DistributionContractComponent {
  protected contract: DistributionContract;
  private distributionContractService: DistributionContractService;
  private activatedRoute: ActivatedRoute;

  constructor(distributionContractService: DistributionContractService, activatedRoute: ActivatedRoute) {
    this.activatedRoute = activatedRoute;
    this.distributionContractService = distributionContractService;
    this.contract = new DistributionContract(1, '', 1, 1, 1, 1, new Date(), new Date(), '', []);
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.contract = this.distributionContractService.getById(Number(params['id'])) as DistributionContract;
    })
  }

}
