import calcualtor.service.CalcService;
import junit.framework.TestCase;

public class CalcServiceTest extends TestCase {
    String test1;
    String test2;
    String test3;
    String test4;
    String test5;
    String test6;
    String test7;
    CalcService service;

    @Override
    protected void setUp() {
        service = new CalcService();
        test1 = "2- 3 * 4 / 8 + 2";
        test2 = "2- --3 * 4 / 8 + 2";
        test3 = "2 - 3 * 4 /0 + 2";
        test4 = "--2";
        test5 = "7+ 2+  3*75-1/3 -2";
        test6 = "7+ 2+  3*75−1/3 -2";
        test7 = "7+ 2+  3×75-1/3 -2";
    }

    public void test() {
        String actual1 = service.calculate(test1);
        String actual2 = service.calculate(test2);
        String actual3 = service.calculate(test3);
        String actual4 = service.calculate(test4);
        String actual5 = service.calculate(test5);
        String actual6 = service.calculate(test6);
        String actual7 = service.calculate(test7);
        assertEquals("2.5", actual1);
        assertEquals("Check your input", actual2);
        assertEquals("Can't divide by zero", actual3);
        assertEquals("Check your input", actual4);
        assertEquals("231.66667", actual5);
        assertEquals("Check your input", actual6);
        assertEquals("Check your input", actual7);
    }
}
