import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber()
    {
        MainClass obj = new MainClass();
        if(obj.getLocalNumber() != 14)
            Assert.fail("Local Number is Not equal 14! Local Number=" + obj.getLocalNumber());
        else
            System.out.println("getLocalNumber = 14");
    }
}
