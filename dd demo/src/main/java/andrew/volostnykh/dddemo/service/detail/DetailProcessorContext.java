package andrew.volostnykh.dddemo.service.detail;

import andrew.volostnykh.dddemo.core.exceptions.DetailProcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DetailProcessorContext implements BeanResolver {
    private final ApplicationContext applicationContext;

    // Register allowed beans to be used in Computable Detail
    private static final Set<String> ALLOWED_BEANS = Set.of(
            "math", "json", "date", "relate", "cast"
    );

    @Override
    public @NonNull Object resolve(@NonNull EvaluationContext context, @NonNull String beanName) {
        if (!ALLOWED_BEANS.contains(beanName)) {
            throw new DetailProcessException("No such service detected: " + beanName + ". Computation is failed!");
        }
        return applicationContext.getBean(beanName);
    }
}
