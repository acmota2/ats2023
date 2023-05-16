package org.example;

import org.example.SmartDevice.SmartCamera;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SmartCameraTest {

    @Test
    public void testDefaultConstructor() {
        SmartCamera camera = new SmartCamera();
        assertEquals("", camera.getResolution());
        assertEquals(0, camera.getSize());
    }

    @Test
    public void testParameterizedConstructor() {
        String id = "CAM001";
        String resolution = "1920x1080";
        int size = 5;
        SmartCamera camera = new SmartCamera(id, resolution, size);
        assertEquals(id, camera.getID());
        assertEquals(resolution, camera.getResolution());
        assertEquals(size, camera.getSize());
    }

    @Test
    public void testParameterizedConstructor2() {
        String id = "";
        String resolution = "";
        int size = 0;
        SmartCamera camera = new SmartCamera(id, resolution, size);
        assertEquals(id, camera.getID());
        assertEquals(resolution, camera.getResolution());
        assertEquals(size, camera.getSize());
    }

    @Test
    public void testCopyConstructor() {
        SmartCamera original = new SmartCamera("CAM001", "1280x720", 3);
        SmartCamera clone = new SmartCamera(original);
        assertEquals(original.getID(), clone.getID());
        assertEquals(original.getResolution(), clone.getResolution());
        assertEquals(original.getSize(), clone.getSize());
    }

    @Test
    public void testGetSetResolution() {
        SmartCamera camera = new SmartCamera();
        camera.setResolution("1280x720");
        assertEquals("1280x720", camera.getResolution());
    }

    @Test
    public void testGetSetSize() {
        SmartCamera camera = new SmartCamera();
        camera.setSize(3);
        assertEquals(3, camera.getSize());
    }

    @Test
    public void testEquals() {
        SmartCamera camera1 = new SmartCamera("CAM001", "1280x720", 3);
        SmartCamera camera2 = new SmartCamera("CAM001", "1280x720", 3);
        SmartCamera camera3 = new SmartCamera("CAM002", "1920x1080", 5);

        assertNotEquals(camera1, camera3);
        assertNotEquals(camera2, camera3);
    }



    @Test
    public void testClone() {
        SmartCamera original = new SmartCamera("CAM001", "1280x720", 3);
        SmartCamera clone = original.clone();
        assertNotSame(original, clone);
        assertEquals(original.getID(), clone.getID());
        assertEquals(original.getResolution(), clone.getResolution());
        assertEquals(original.getSize(), clone.getSize());
    }

    @Test
    public void testClone2() {
        SmartCamera original = new SmartCamera("", "", 3);
        SmartCamera clone = original.clone();
        assertNotSame(original, clone);
        assertEquals(original.getID(), clone.getID());
        assertEquals(original.getResolution(), clone.getResolution());
        assertEquals(original.getSize(), clone.getSize());
    }

    @Test
    public void testGetConsume() {
        SmartCamera camera = new SmartCamera("CAM001", "1920x1080", 5);
        double expectedConsume = 0.24 * (5 * 0.005 * (1920 * 1080) / 1000000);
        assertEquals(expectedConsume, camera.getConsume(), 0.001);
    }
}
