

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.SmartDevice.*;
import org.example.CasaInteligente.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class CasaInteligenteTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CasaInteligenteTest {
    /**
     * Default constructor for test class CasaInteligenteTest
     */
    public CasaInteligenteTest()
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
        CasaInteligente casaInte1 = new CasaInteligente();
        assertNotNull(casaInte1);
        casaInte1 = new CasaInteligente("Campus de Gualtar",253681650,"");
        assertNotNull(casaInte1);
    }

    @Test
    public void testAddFindDevice() { // testa o add e o find ao mesmo tempo
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar",253681650,"");
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        assertFalse(casaInte1.existsDevice("b1"));
        assertFalse(casaInte1.existsDevice("s1"));
        casaInte1.addDevice(smartBul1);
        assertTrue(casaInte1.existsDevice("b1"));
        casaInte1.addDevice(smartSpe1);
        assertTrue(casaInte1.existsDevice("s1"));
        assertTrue(casaInte1.existsDevice("b1"));
    }

    @Test
    public void testGetDevice() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar",253681650,"");

        SmartBulb smartBul1 = new SmartBulb("b1");
        casaInte1.addDevice(smartBul1);
        assertTrue(casaInte1.getDevice("b1").equals(smartBul1));
        assertFalse(casaInte1.getDevice("b2").equals(smartBul1));
        // casaInte1.removeDevice(smartBul1);
        // assetFalse(casaInte1.getDevice("b1").equals(smartBul1)));
        // rever
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        casaInte1.addDevice(smartSpe1);
        assertTrue(casaInte1.getDevice("s1").equals(smartSpe1));


    }

    /*
    @Test
    public void testSetOn() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar",253681650,"");

        SmartBulb smartBul1 = new SmartBulb("b1");

        assertFalse(smartBul1.getOn());
        casaInte1.addDevice(smartBul1);

       SmartDevice sb = casaInte1.getDevice("b1");
       if (sb instanceof SmartBulb ) {
           SmartBulb ole= (SmartBulb) sb;
           ole.getTone();
       }
        smartBul1.setOn(true);
        assertTrue(smartBul1.getOn());
        assertFalse(casaInte1.getDevice("b1").getOn());

    }
    */

/*
    @Test
    public void testSetAllOn() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar",253681650,"");
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        casaInte1.addDevice(smartBul1);
        casaInte1.addDevice(smartSpe1);
        assertFalse(casaInte1.getDevice("b1").getOn());
        assertFalse(casaInte1.getDevice("s1").getOn());
        casaInte1.setAllOn(true);
        assertTrue(casaInte1.getDevice("b1").getOn());
        assertTrue(casaInte1.getDevice("s1").getOn());
        casaInte1.setAllOn(false);
        assertFalse(casaInte1.getDevice("b1").getOn());
        assertFalse(casaInte1.getDevice("s1").getOn());
    }
*/
    @Test
    public void testAddRoom() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar",253681650,"");
        try {
            casaInte1.addRoom("sala");
            assertTrue(casaInte1.hasRoom("sala"));
            assertFalse(casaInte1.hasRoom("quarto"));
        }
        catch (DivisaoJaExisteException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddToRoom() {
        try {
            CasaInteligente casaInte1 = new CasaInteligente("Gualtar", 253681650, "");
            SmartBulb smartBul1 = new SmartBulb("b1");
            SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
            SmartSpeaker smartSpe2 = new SmartSpeaker("s2");
            casaInte1.addDevice(smartBul1);
            casaInte1.addDevice(smartSpe1);
            casaInte1.addDevice(smartSpe2);
            casaInte1.addRoom("sala");
            casaInte1.addRoom("quarto");
            casaInte1.addToRoom("sala", "b1");
            casaInte1.addToRoom("sala", "s1");
            casaInte1.addToRoom("quarto", "s2");
            assertTrue(casaInte1.roomHasDevice("sala", "b1"));
            assertTrue(casaInte1.roomHasDevice("sala", "s1"));
            assertFalse(casaInte1.roomHasDevice("sala", "s2"));
            assertTrue(casaInte1.roomHasDevice("quarto", "s2"));
        }
        catch (DivisaoJaExisteException a ) {
            System.out.println(a.getMessage());
        }
    }

    @Test
    public void testGetNome_proprietario() {
        CasaInteligente casaInte1 = new CasaInteligente("John Doe", 123456789, "FornecedorX");
        assertEquals("John Doe", casaInte1.getNome_proprietario());
    }

    @Test
    public void testSetNome_proprietario() {
        CasaInteligente casaInte1 = new CasaInteligente();
        casaInte1.setNome_proprietario("Jane Smith");
        assertEquals("Jane Smith", casaInte1.getNome_proprietario());
    }

    @Test
    public void testGetNIF() {
        CasaInteligente casaInte1 = new CasaInteligente("John Doe", 123456789, "FornecedorX");
        assertEquals(123456789, casaInte1.getNIF());
    }

    @Test
    public void testSetNIF() {
        CasaInteligente casaInte1 = new CasaInteligente();
        casaInte1.setNIF(987654321);
        assertEquals(987654321, casaInte1.getNIF());
    }

    @Test
    public void testGetDevices() {
        CasaInteligente casaInte1 = new CasaInteligente("John Doe", 123456789, "FornecedorX");
        SmartDevice smartDevice = new SmartSpeaker("Device1");
        casaInte1.addDevice(smartDevice);
        assertEquals(1, casaInte1.getDevices().size());
        assertTrue(casaInte1.getDevices().containsKey("Device1"));
    }

    @Test
    public void testSetDevices() {
        CasaInteligente casaInte1 = new CasaInteligente("John Doe", 123456789, "FornecedorX");
        SmartDevice smartDevice1 = new SmartBulb("Device1");
        SmartDevice smartDevice2 = new SmartSpeaker("Device2");
        casaInte1.addDevice(smartDevice1);

        CasaInteligente casaInte2 = new CasaInteligente();
        casaInte2.setDevices(casaInte1.getDevices());
        casaInte2.addDevice(smartDevice2);

        assertEquals(2, casaInte2.getDevices().size());
        assertTrue(casaInte2.getDevices().containsKey("Device1"));
        assertTrue(casaInte2.getDevices().containsKey("Device2"));
    }

    @Test
    public void testGetLocations() throws DivisaoJaExisteException {
        CasaInteligente casaInte1 = new CasaInteligente("John Doe", 123456789, "FornecedorX");
        casaInte1.addRoom("Living Room");
        casaInte1.addToRoom("Living Room", "Device1");
        assertEquals(1, casaInte1.getLocations().size());
        assertTrue(casaInte1.getLocations().containsKey("Living Room"));
        assertTrue(casaInte1.getLocations().get("Living Room").contains("Device1"));
    }


}

