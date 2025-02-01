package andrew.volostnykh.dddemo.model.repository;

import andrew.volostnykh.dddemo.model.DetailEntityRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailEntityRelationRepository extends JpaRepository<DetailEntityRelation, Long> {
}
