import { Routes } from '@angular/router';
import {DistributionContractComponent} from './components/distribution-contract/distribution-contract.component';
import {DistributionContractListComponent} from './components/distribution-contract-list/distribution-contract-list.component';

export const routes: Routes = [
  { path: 'distribution-contracts', component: DistributionContractListComponent },
  { path: 'distribution-contract/:id', component: DistributionContractComponent }
];
