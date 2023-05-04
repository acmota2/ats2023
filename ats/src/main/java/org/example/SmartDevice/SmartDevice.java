package org.example.SmartDevice;

/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de org.example. Representa uma solução em construção, com base na matéria leccionada */ 
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de org.example.                                                        */
/*********************************************************************************/


import java.io.Serializable;
import java.util.Random;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos. 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class SmartDevice implements Serializable {
    //@Serial

    private static final long serialVersionUID = 9999L;

    private final String id; // sim, este é final, nunca vai ser alterado
    private boolean on;

    /**
     * Constructor for objects of class SmartDevice
     */
    public SmartDevice() {
        Random r = new Random();
        this.id = "";
        this.on = r.nextBoolean();
    }

    public SmartDevice(String s) {
        Random r = new Random();
        this.id = s;
        this.on = r.nextBoolean();
    }

    public SmartDevice(String s, boolean b) {
        this.id = s;
        this.on = b;
    }

    public SmartDevice (SmartDevice c) {
        this.id= c.getID();
        this.on=c.getOn();
    }

    public void turnOn() {
        this.on = true;
    }
    
    public void turnOff() {
        this.on = false;
    }
    
    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean b) {
        this.on=b;
    }

    public String getID() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice that = (SmartDevice) o;
        return this.getOn() == that.getOn() && this.id.equals(that.id);
    }

    @Override
    public String toString() {
        return
                "id='" + id + '\'' +
                ", on='" + on + '\''
                ;
    }

    abstract public SmartDevice clone();

    abstract public double getConsume();

}
