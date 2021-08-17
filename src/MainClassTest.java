import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetClassNumber()
    {
        if(new MainClass().getClassNumber() > 45)
            System.out.println("Test passed.");
        else
            Assert.fail("Test failed! getClassNumber() returned value <= 45");
    }
}
