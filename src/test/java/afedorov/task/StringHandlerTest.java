package afedorov.task;


import org.junit.Assert;
import org.junit.Test;

public class StringHandlerTest {

    @Test
    public void testWithRepeat() {
        String expected = "xxxyxxxy";
        String actual = new StringHandler("2[3[x]y]").handle();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithoutRepeat() {
        String expected = "xyzxyzxyzxyxyxyxyz";
        String actual = new StringHandler("3[xyz]4[xy]z").handle();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithOneInsideRepeatManyParts() {
        String expected = "xyzxyzxyzxyxyxyxyzxyzxyzxyzxyxyxyxyzl";
        String actual = new StringHandler("2[3[xyz]4[xy]z]l").handle();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithTwoInsideRepeatManyParts() {
        String expected = "xyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyxyl";
        String actual = new StringHandler("2[3[3[xy]xy]]l").handle();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithotBrackets() {
        String expected = "xy";
        String actual = new StringHandler("xy").handle();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongWithoutCounts() {
        new StringHandler("[xy]").handle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongWithCountsAfterSimple() {
        new StringHandler("xy5[xy]").handle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongWithWrongSymbols() {
        new StringHandler("5[xy1]").handle();
    }

}