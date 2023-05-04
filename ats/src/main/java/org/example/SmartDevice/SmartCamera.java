package org.example.SmartDevice;

import java.io.Serializable;

public class SmartCamera extends SmartDevice implements Serializable {
    //@Serial

    private static final long serialVersionUID = 9999L;

    private String resolution;
    private int size;

    public SmartCamera() {
        super();
        this.resolution="";
        this.size=0;
    }

    public SmartCamera(String id, String resolution, int size) {
        super(id);
        this.resolution=resolution;
        this.size=size;
    }

    public SmartCamera (SmartCamera camera) {
        super(camera.getID());
        this.resolution=camera.resolution;
        this.size=camera.size;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getConsumption() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartCamera that = (SmartCamera) o;
        return super.equals(that)
                && this.getResolution() == that.getResolution()
                && this.getSize() == that.getSize();
    }

    @Override
    public String toString() {
        return "SmartCamera{" +
                super.toString() +
                ", resolution='" + resolution + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public SmartCamera clone() {
        return new SmartCamera(this);
    }

    public double getConsume(){
        String[] res = this.resolution.split("()x");
        double resolution = Double.parseDouble(res[0]) * Double.parseDouble(res[1]) / 1000000;
        return 0.24 * (this.size * 0.005 * resolution);
    }

}
