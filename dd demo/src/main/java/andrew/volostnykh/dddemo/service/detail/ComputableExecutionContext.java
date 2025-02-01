package andrew.volostnykh.dddemo.service.detail;

import andrew.volostnykh.dddemo.model.Detail;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ComputableExecutionContext extends StandardEvaluationContext  {

    public ComputableExecutionContext(Detail detail, Object sourceEntity, BeanResolver beanResolver) {
        setBeanResolver(beanResolver);
        setVariable("source", sourceEntity);
        setVariable("this", detail);
    }
}
