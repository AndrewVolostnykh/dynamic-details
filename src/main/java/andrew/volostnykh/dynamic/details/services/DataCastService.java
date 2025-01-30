package andrew.volostnykh.dynamic.details.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("cast")
public class DataCastService {

    public BigDecimal toNumber(String number) {
        // TODO: should it be parsed to a Double?
        return new BigDecimal(number);
    }

    public BigDecimal toNumber(Integer number) {
        return BigDecimal.valueOf(number);
    }
}
