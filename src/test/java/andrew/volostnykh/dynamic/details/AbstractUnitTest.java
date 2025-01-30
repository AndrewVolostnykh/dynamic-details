package andrew.volostnykh.dynamic.details;

import andrew.volostnykh.dynamic.details.services.DataCastService;
import andrew.volostnykh.dynamic.details.services.DateOperationService;
import andrew.volostnykh.dynamic.details.services.JsonOperationService;
import andrew.volostnykh.dynamic.details.services.MathOperationService;
import andrew.volostnykh.dynamic.details.services.context.BusinessContext;
import andrew.volostnykh.dynamic.details.services.context.DetailsBeanResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
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
    protected ApplicationContext applicationContext;
    @Autowired
    protected DetailsBeanResolver detailsBeanResolver;
    protected BusinessContext businessContext;

    @BeforeEach
    public void init() {
        businessContext = new BusinessContext(detailsBeanResolver, applicationContext);
    }
}
