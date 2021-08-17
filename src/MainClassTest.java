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

    @Test
    public void testGetClassString()
    {
        MainClass obj = new MainClass();
        if(obj.getClassString().contains("Hello") || obj.getClassString().contains("hello"))
            System.out.println("Test passed.");
        else
            Assert.fail("Test failed! The private variable class_string in MainClass does not contain substrings 'Hello' or 'hello' ");
    }
}
