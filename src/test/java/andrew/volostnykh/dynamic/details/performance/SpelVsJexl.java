package andrew.volostnykh.dynamic.details.performance;

import andrew.volostnykh.dynamic.details.AbstractUnitTest;
import andrew.volostnykh.dynamic.details.impl.ComputableDetail;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SpelVsJexl extends AbstractUnitTest {

    /**
     * Test shown that SPeL is much faster than Jexl.
     * SPeL result: ~250ms
     * Jexl result: ~3500ms
     * Jexl is much better in case of very complicated scripts, rule engines, etc.
     */
    @Test
    void compute_getValueFromMap_validResult() {
        JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();
        System.err.println("SPeL result" + performanceTest(this::testSPeL, 100_000L));
        System.err.println("Jexl result" + performanceTest(() -> testJexl(jexl), 100_000L));
    }

    private Long performanceTest(Runnable runnable, Long repeatTimes) {
        long time = System.nanoTime();
        for (int i=0; i < repeatTimes; i++) {
            runnable.run();
        }
        return System.nanoTime() - time;
    }

    private void testSPeL() {
        ComputableDetail<Object> computableDetail = new ComputableDetail<>();
        computableDetail.setFormula("1000 * 2");

        computableDetail.setBusinessContext(businessContext);

        computableDetail.getValue();
    }

    void testJexl(JexlEngine jexl) {
        String calculateResult = "D1 * D2";
        JexlExpression e = jexl.createExpression(calculateResult);

        JexlContext context = new MapContext();
        context.set("D1", 1000);
        context.set("D2", 2);

        e.evaluate(context);
    }
}
