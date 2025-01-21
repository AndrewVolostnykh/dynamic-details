package com.lessons.spring.springdb;

import com.lessons.spring.springdb.frameworks.services.DataCastService;
import com.lessons.spring.springdb.frameworks.services.DateOperationService;
import com.lessons.spring.springdb.frameworks.services.JsonOperationService;
import com.lessons.spring.springdb.frameworks.services.MathOperationService;
import com.lessons.spring.springdb.frameworks.services.context.BusinessContext;
import com.lessons.spring.springdb.frameworks.services.context.DetailsBeanResolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        DetailsBeanResolver.class,
        DataCastService.class,
        DateOperationService.class,
        JsonOperationService.class,
        MathOperationService.class
})
public class AbstractUnitTest {

    @Autowired
    protected DetailsBeanResolver detailsBeanResolver;
    protected BusinessContext businessContext;

    @BeforeEach
    public void init() {
        businessContext = new BusinessContext(detailsBeanResolver);
    }
}
