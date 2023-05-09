

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.SmartDevice.*;
import org.example.CasaInteligente.*;
/**
 * The test class SmartBulbTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SmartBulbTest {
    /**
     * Default constructor for test class SmartBulbTest
     */
    public SmartBulbTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testContructor() {
        SmartBulb smartBul1 = new SmartBulb();
        assertNotNull(smartBul1);
        smartBul1 = new SmartBulb("b1");
        assertNotNull(smartBul1);
        smartBul1 = new SmartBulb("b1", SmartBulb.NEUTRAL,10.0);
        assertNotNull(smartBul1);
        SmartBulb sb2 = new SmartBulb(smartBul1);
        assertNotNull(sb2);
    }

    @Test
    public void testGetTone() {
        SmartBulb smartBul1 = new SmartBulb("b1", SmartBulb.COLD,10.0);
        assertEquals(0, smartBul1.getTone());
        smartBul1 = new SmartBulb("b1", SmartBulb.NEUTRAL,10.0);
        assertEquals(1, smartBul1.getTone());
        smartBul1 = new SmartBulb("b1", SmartBulb.WARM,10.0);
        assertEquals(2, smartBul1.getTone());
        smartBul1 = new SmartBulb("b1");
        assertEquals(SmartBulb.NEUTRAL, smartBul1.getTone());
    }

    @Test
    public void testSetTone() {
        SmartBulb smartBul1 = new SmartBulb("b1");
        smartBul1.setTone(2);
        assertEquals(SmartBulb.WARM, smartBul1.getTone());
        smartBul1.setTone(10);
        assertEquals(SmartBulb.WARM, smartBul1.getTone());
        smartBul1.setTone(-10);
        assertEquals(SmartBulb.COLD, smartBul1.getTone());
    }

    @Test
    public void testCloneeEquals (){
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartBulb smartBulb2 = smartBul1.clone();
        assertEquals(smartBul1, smartBulb2);

    }

    @Test
    public void testGetTone2() {
        int tone = SmartBulb.NEUTRAL;
        SmartBulb smartBulb = new SmartBulb("bulb1", tone, 2.5);

        assertEquals(tone, smartBulb.getTone());
    }

    @Test
    public void testSetTone2() {
        SmartBulb smartBulb = new SmartBulb("bulb1", SmartBulb.NEUTRAL, 2.5);
        int tone = SmartBulb.WARM;
        smartBulb.setTone(tone);

        assertEquals(tone, smartBulb.getTone());
    }

    @Test
    public void testSetToneInvalid() {
        SmartBulb smartBulb = new SmartBulb("bulb1", SmartBulb.NEUTRAL, 2.5);
        int invalidTone = -1;
        smartBulb.setTone(invalidTone);

        assertEquals(SmartBulb.COLD, smartBulb.getTone());

        int validTone = 3;
        smartBulb.setTone(validTone);

        assertEquals(SmartBulb.WARM, smartBulb.getTone());
    }

    @Test
    public void testGetHeight() {
        double height = 2.5;
        SmartBulb smartBulb = new SmartBulb("bulb1", SmartBulb.NEUTRAL, height);

        assertEquals(height, smartBulb.getHeight());
    }

    @Test
    public void testSetHeight() {
        SmartBulb smartBulb = new SmartBulb("bulb1", SmartBulb.NEUTRAL, 2.5);
        double height = 3.0;
        smartBulb.setHeight(height);

        assertEquals(height, smartBulb.getHeight());
    }

    @Test
    public void testEquals() {
        SmartBulb bulb1 = new SmartBulb("bulb1", SmartBulb.NEUTRAL, 2.5);
        SmartBulb bulb2 = new SmartBulb("bulb1", SmartBulb.NEUTRAL, 2.5);

        assertEquals(bulb1, bulb2);
    }

    @Test
    public void testClone() {
        SmartBulb bulb1 = new SmartBulb("bulb1", SmartBulb.NEUTRAL, 2.5);
        SmartBulb clone = bulb1.clone();

        assertEquals(bulb1, clone);
        assertNotSame(bulb1, clone);
    }

    @Test
    public void testGetConsume() {
        int tone = SmartBulb.NEUTRAL;
        double height = 2.5;
        SmartBulb smartBulb = new SmartBulb("bulb1", tone, height);

        double expectedConsume = 0.32 + tone * 0.02 + height * 0.005;
        assertEquals(expectedConsume, smartBulb.getConsume());
    }

}



