

import static org.junit.jupiter.api.Assertions.*;

import org.example.SmartDevice.SmartDevice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SmartDeviceTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SmartDeviceTest {
    /**
     * Default constructor for test class SmartDeviceTest
     */
    public SmartDeviceTest()
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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    /*
    @Test
    public void testContructor() {
        SmartDevice smartDev1 = new SmartDevice();
        assertTrue(smartDev1!=null);
        smartDev1 = new SmartDevice("b1");
        assertTrue(smartDev1!=null);
        smartDev1 = new SmartDevice("b1", true);
        assertTrue(smartDev1!=null);
    }
    
    @Test
    public void testGetID() {
        SmartDevice smartDev1 = new SmartDevice();
        assertEquals("", smartDev1.getID());
        smartDev1 = new SmartDevice("b1");
        assertEquals("b1", smartDev1.getID());
    }
    
    @Test
    public void testGetOn() {
        SmartDevice smartDev1 = new SmartDevice();
        assertFalse(smartDev1.getOn());
        smartDev1 = new SmartDevice("b1", false);
        assertFalse(smartDev1.getOn());
        smartDev1 = new SmartDevice("b1", true);
        assertTrue(smartDev1.getOn());
    }

    @Test
    public void testSetOn() {
        SmartDevice smartDev1 = new SmartDevice("b1", false);
        smartDev1.setOn(true);
        assertTrue(smartDev1.getOn());
        smartDev1.setOn(false);
        assertFalse(smartDev1.getOn());
    }
    */

    @Test
    public void testGetOn() {
        SmartDevice smartDevice = new SmartDeviceStub("device1");
        boolean on = smartDevice.getOn();

        assertTrue(on);
    }

    @Test
    public void testSetOn() {
        SmartDevice smartDevice = new SmartDeviceStub("device1");
        boolean on = false;
        smartDevice.setOn(on);

        assertEquals(on, smartDevice.getOn());
    }

    @Test
    public void testTurnOn() {
        SmartDevice smartDevice = new SmartDeviceStub("device1");
        smartDevice.turnOff();
        smartDevice.turnOn();

        assertTrue(smartDevice.getOn());
    }

    @Test
    public void testTurnOff() {
        SmartDevice smartDevice = new SmartDeviceStub("device1");
        smartDevice.turnOn();
        smartDevice.turnOff();

        assertFalse(smartDevice.getOn());
    }

    @Test
    public void testGetID() {
        String id = "device1";
        SmartDevice smartDevice = new SmartDeviceStub(id);

        assertEquals(id, smartDevice.getID());
    }

    @Test
    public void testEquals() {
        SmartDevice device1 = new SmartDeviceStub("device1");
        SmartDevice device2 = new SmartDeviceStub("device1");

        assertEquals(device1, device2);
    }

    @Test
    public void testClone() {
        SmartDevice device1 = new SmartDeviceStub("device1");
        SmartDevice clone = device1.clone();

        assertEquals(device1, clone);
        assertNotSame(device1, clone);
    }

    @Test
    public void testGetConsume2() {
        SmartDevice smartDevice = new SmartDeviceStub("device1");
        double expectedConsume = 0.0;
        assertEquals(expectedConsume, smartDevice.getConsume());
    }

    @Test
    public void testGetOn2() {
        SmartDevice smartDevice = new SmartDeviceStub();
        boolean on = smartDevice.getOn();
        assertTrue(on);
    }

    @Test
    public void testSetOn2() {
        SmartDevice smartDevice = new SmartDeviceStub();
        boolean on = false;
        smartDevice.setOn(on);
        assertEquals(on, smartDevice.getOn());
    }

    @Test
    public void testTurnOn2() {
        SmartDevice smartDevice = new SmartDeviceStub();
        smartDevice.turnOff();
        smartDevice.turnOn();
        assertTrue(smartDevice.getOn());
    }

    @Test
    public void testTurnOff2() {
        SmartDevice smartDevice = new SmartDeviceStub();
        smartDevice.turnOn();
        smartDevice.turnOff();
        assertFalse(smartDevice.getOn());
    }

    @Test
    public void testGetID2() {
        String id = "device1";
        SmartDevice smartDevice = new SmartDeviceStub(id);
        assertEquals(id, smartDevice.getID());
    }

    @Test
    public void testEquals2() {
        SmartDevice device1 = new SmartDeviceStub("device1");
        SmartDevice device2 = new SmartDeviceStub("device1");
        SmartDevice device3 = new SmartDeviceStub("device2");

        assertEquals(device1, device2);
        assertNotEquals(device1, device3);
    }



    @Test
    public void testGetConsume() {
        SmartDevice smartDevice = new SmartDeviceStub();
        double expectedConsume = 0.0;
        assertEquals(expectedConsume, smartDevice.getConsume());
    }

    // Stub class for testing
    private static class SmartDeviceStub extends SmartDevice {
        public SmartDeviceStub() {
            super();
        }

        public SmartDeviceStub(String id) {
            super(id);
        }

        @Override
        public SmartDevice clone() {
            return new SmartDeviceStub(this.getID());
        }

        @Override
        public double getConsume() {
            return 0.0;
        }
    }

}
