import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistributionContractListComponent } from './distribution-contract-list.component';

describe('DistributionContractListComponent', () => {
  let component: DistributionContractListComponent;
  let fixture: ComponentFixture<DistributionContractListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DistributionContractListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistributionContractListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
