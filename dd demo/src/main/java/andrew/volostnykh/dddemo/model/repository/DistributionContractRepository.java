package andrew.volostnykh.dddemo.model.repository;

import andrew.volostnykh.dddemo.model.DistributionContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionContractRepository extends JpaRepository<DistributionContract, Long> {
}
