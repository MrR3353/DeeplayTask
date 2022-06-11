import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void getResult() {
        Assert.assertEquals(10, Solution.getResult("STWSWTPPTPTTPWPP", "Human"));
        Assert.assertEquals(12, Solution.getResult("STWSWTPPTPTTPWPP", "Woodman"));
        Assert.assertEquals(15, Solution.getResult("STWSWTPPTPTTPWPP", "Swamper"));
        Assert.assertEquals(6, Solution.getResult("PPPPPPPPPPPPPPPP", "Human"));
        Assert.assertEquals(14, Solution.getResult("SSSSPSSSPSSSPPSS", "Human"));
    }

}