package andrew.volostnykh.dynamic.details.services.context;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BusinessContext implements BeanResolver {

    private final DetailsBeanResolver beanResolver;
    // TODO: service for querying global vars and other business data

    public BeanResolver getBeanResolver() {
        return beanResolver;
    }

    public Map<String, Object> getGlobalVars() {
        // imitation of queried global variables
        return Map.of(
                "rate", new BigDecimal("0.5"),
                "basicSum", new BigDecimal("1000"),
                "refund", new BigDecimal("500"),
                "paymentSystemCommission", new BigDecimal("12.5"),
                "personPhoneNumber", "1234567890",
                "personFullName", "Test Test Test",
                "allowedStartDate", LocalDateTime.now(),
                "allowedEndDate", LocalDateTime.now().plusYears(1)
        );
    }

    private static final Set<String> ALLOWED_BEANS = Set.of(
            "math", "ijson", "date"
    );

    private final ApplicationContext applicationContext;

    @Override
    public Object resolve(EvaluationContext context, String beanName) {
//        if (!ALLOWED_BEANS.contains(beanName)) {
//            // it could be greate to store in
//            throw new DetailProcessException("No such service detected: " + beanName + ". Computation is failed!");
//        }
        return applicationContext.getBean(beanName);
    }
}
