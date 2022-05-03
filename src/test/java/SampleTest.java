import com.mobest1an.labs.models.checkers.AreaCheckerBean;
import com.mobest1an.labs.services.Result;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SampleTest {

    @Test
    public void testSample() {
        AreaCheckerBean areaCheckerBean = new AreaCheckerBean();
        Result result = new Result(-0.33, 0.66, 1, null);
        boolean checkResult = areaCheckerBean.check(result);
        assertTrue(checkResult);
    }
}
