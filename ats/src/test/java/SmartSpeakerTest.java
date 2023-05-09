

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.SmartDevice.SmartSpeaker;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class SmartSpeakerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SmartSpeakerTest {
    /**
     * Default constructor for test class SmartSpeakerTest
     */

    public SmartSpeakerTest()
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
    
    

    @Test
    public void testConstructor() {
        SmartSpeaker smartSpe1 = new SmartSpeaker();
        assertNotNull(smartSpe1);
        smartSpe1 = new SmartSpeaker("b1");
        assertNotNull(smartSpe1);
        smartSpe1 = new SmartSpeaker("b1",10, "RUM", "Sony");
        assertNotNull(smartSpe1);
    }

    @Test
    public void testGetVolume() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("b1",5, "RUM", "Sony");
        assertEquals(5, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker("b1",  SmartSpeaker.MAX,"RUM", "Sony");
        assertEquals(20, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker("b1",-10, "RUM","Sony" );
        assertEquals(0, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker();
        assertEquals(0, smartSpe1.getVolume());
    }

    @Test
    public void testSetVolume() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("b1",5, "RUM", "Sony");
        smartSpe1.volumeUp();
        smartSpe1.volumeUp();
        assertEquals(7, smartSpe1.getVolume());
        for (int i=0; i<25; i++) smartSpe1.volumeUp();
        assertEquals(20, smartSpe1.getVolume());
        for (int i=0; i<30; i++) smartSpe1.volumeDown();
        assertEquals(0, smartSpe1.getVolume());
    }

    @Test
    public void testGetChannel() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1",5, "RUM", "Sony");
        assertEquals("RUM", smartSpe1.getChannel());
        smartSpe1 = new SmartSpeaker("s2",5, "XPTO", "OLE");
        assertEquals("XPTO", smartSpe1.getChannel());
        smartSpe1 = new SmartSpeaker();
        assertEquals("", smartSpe1.getChannel());
    }

    @Test
    public void testSetChannel() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        smartSpe1.setChannel("RUM");
        assertEquals("RUM", smartSpe1.getChannel());
        smartSpe1.setChannel("XPTO");
        assertEquals("XPTO", smartSpe1.getChannel());
    }

    @Test
    public void testGetVolume2() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1", 0, "", "");
        int volume = smartSpeaker.getVolume();
        assertEquals(0, volume);
    }

    @Test
    public void testSetChannel2() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1", 0, "", "");
        String channel = "Channel 1";
        smartSpeaker.setChannel(channel);
        assertEquals(channel, smartSpeaker.getChannel());
    }

    @Test
    public void testVolumeUp() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1", 0, "", "");
        smartSpeaker.volumeUp();
        assertEquals(1, smartSpeaker.getVolume());
    }

    @Test
    public void testVolumeUp_MaxVolume() {
        // Set volume to maximum
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1", 20, "", "");
        smartSpeaker.volumeUp();
        assertEquals(20, smartSpeaker.getVolume());
    }

    @Test
    public void testVolumeDown() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1", 5, "", "");
        smartSpeaker.volumeDown();
        assertEquals(4, smartSpeaker.getVolume());
    }

    @Test
    public void testVolumeDown_MinVolume() {
        // Set volume to minimum
        SmartSpeaker smartSpeaker = new SmartSpeaker("speaker1", 0, "", "");
        smartSpeaker.volumeDown();
        assertEquals(0, smartSpeaker.getVolume());
    }

    @Test
    public void testEquals() {
        SmartSpeaker speaker1 = new SmartSpeaker("speaker1", 10, "Channel 1", "Brand 1");
        SmartSpeaker speaker2 = new SmartSpeaker("speaker1", 10, "Channel 1", "Brand 1");
        SmartSpeaker speaker3 = new SmartSpeaker("speaker2", 10, "Channel 1", "Brand 1");

        assertEquals(speaker1, speaker2);
        assertNotEquals(speaker1, speaker3);
    }

    @Test
    public void testClone() {
        SmartSpeaker speaker1 = new SmartSpeaker("speaker1", 10, "Channel 1", "Brand 1");
        SmartSpeaker clone = speaker1.clone();

        assertEquals(speaker1, clone);
        assertNotSame(speaker1, clone);
    }

    @Test
    public void testGetConsume() {
        SmartSpeaker smartSpeaker = new SmartSpeaker();
        double expectedConsume = 0.01;
        assertEquals(expectedConsume, smartSpeaker.getConsume());
    }


}

