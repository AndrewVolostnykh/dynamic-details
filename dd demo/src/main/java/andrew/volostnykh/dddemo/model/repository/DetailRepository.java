package andrew.volostnykh.dddemo.model.repository;

import andrew.volostnykh.dddemo.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {

    @Query("SELECT detail FROM Detail detail " +
            "JOIN DetailEntityRelation relation ON detail.id = relation.detailId AND relation.entityId = :entityId " +
            "WHERE relation.entity = :entityName")
    List<Detail> findByEntity(Long entityId, String entityName);
}
