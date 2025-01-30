package andrew.volostnykh.dynamic.details;

import andrew.volostnykh.dynamic.details.impl.PeriodicDetail;

import java.util.List;

public interface DetailRepository {

    List<PeriodicDetail> findPeriodicDetailsByCode(String code);
}
