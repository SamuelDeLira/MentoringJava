import org.testng.Assert;
import org.testng.annotations.Test;

public class myTestsTestNG {
    @Test
    public void Test1(){
        System.out.println("This is my first TEST NG test");
    }

    @Test
    public void Test2(){
        int num1 = 3;
        int num2 = 8;
        int num3 = num1 + num2;
        System.out.println("Sum " +num3);

        Assert.assertEquals(num3, 11); //Assertion Positive
    }

    @Test
    public void Test3(){
        int num1 = 3;
        int num2 = 8;
        int num3 = num1 - num2;
        System.out.println("Subs " +num3);

        Assert.assertEquals(num3, -4);//Assertion Negative
    }

}