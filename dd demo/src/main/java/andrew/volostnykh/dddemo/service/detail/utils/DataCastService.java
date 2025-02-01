package andrew.volostnykh.dddemo.service.detail.utils;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service("cast")
public class DataCastService {

    public BigDecimal toNumber(String number) {
        return new BigDecimal(number);
    }

    public BigDecimal toNumber(Integer number) {
        return BigDecimal.valueOf(number);
    }
}
