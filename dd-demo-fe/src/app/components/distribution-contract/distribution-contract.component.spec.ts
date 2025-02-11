import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistributionContractComponent } from './distribution-contract.component';

describe('DistributionContractComponent', () => {
  let component: DistributionContractComponent;
  let fixture: ComponentFixture<DistributionContractComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DistributionContractComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistributionContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
