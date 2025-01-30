package andrew.volostnykh.dynamic.details.services.context;

import org.springframework.expression.BeanResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ComputableDetailContext extends StandardEvaluationContext {

    public ComputableDetailContext(Object sourceEntity, BeanResolver beanResolver) {
        setBeanResolver(beanResolver);
        setVariable("source", sourceEntity);
    }

    // set variables
}
