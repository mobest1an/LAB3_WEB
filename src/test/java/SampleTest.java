import com.mobest1an.labs.models.checkers.AreaCheckerBean;
import com.mobest1an.labs.services.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SampleTest {

    private double x;
    private double y;
    private double r;
    private boolean expected;
    private Date creationDate;

    public SampleTest(double x, double y, double r, boolean expected) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.expected = expected;
        this.creationDate = null;
    }

    @Parameterized.Parameters(name = "{index}: logic({0}, {1}, {2})={3}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][] {
                {-0.11, -0.11, 1, true},
                {-0.33, 0.66, 2, true},
                {2, 2, 1, false}
        });
    }

    @Test
    public void paramTest() {
        assertEquals(expected, new AreaCheckerBean().check(new Result(x,y, r, creationDate)));
    }
}
