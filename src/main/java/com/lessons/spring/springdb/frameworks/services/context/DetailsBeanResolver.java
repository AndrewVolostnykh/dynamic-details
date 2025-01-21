package com.lessons.spring.springdb.frameworks.services.context;

import com.lessons.spring.springdb.frameworks.core.exceptions.DetailProcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DetailsBeanResolver implements BeanResolver {

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
