package andrew.volostnykh.dynamic.details.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("date")
public class DateOperationService {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public Boolean isAfter(String date, LocalDateTime earlier) {
        return LocalDateTime.parse(date).isAfter(earlier);
    }
}
