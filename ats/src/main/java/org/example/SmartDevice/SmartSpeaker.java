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

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker extends SmartDevice implements Serializable {
    //@Serial

    private static final long serialVersionUID = 9999L;

    public static final int MAX = 20; //volume máximo

    private int volume;
    private String channel;
    private final String brand;

    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        // initialise instance variables
        super();
        this.volume = 0;
        this.channel="";
        this.brand="";
    }

    public SmartSpeaker(String s) {
        // initialise instance variables
        super(s);
        this.volume = 0;
        this.channel="";
        this.brand="";
    }

    public SmartSpeaker(String cod, int volume, String channel, String brand) {
        // initialise instance variables
        super(cod);
        if (volume>=0 && volume<=MAX) {
            this.volume = volume;
        }
        this.channel = channel;
        this.brand=brand;

    }

    public SmartSpeaker (SmartSpeaker s) {
        super (s.getID(),s.getOn());
        this.volume=s.getVolume();
        this.channel=s.getChannel();
        this.brand= s.brand;
    }

    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    public int getVolume() {return this.volume;}
    
    public String getChannel() {return this.channel;}
    
    public void setChannel(String c) {this.channel=c;}

    @Override
    public boolean equals (Object o) {
        if (this == o ) return true;
        if (o== null || o.getClass() != this.getClass()) return false;
        SmartSpeaker s= (SmartSpeaker) o;
        return super.equals(s)
                && this.volume == s.getVolume()
                && this.channel == s.getChannel()
                && this.brand == s.brand;
    }

    @Override
    public String toString() {
        return "SmartSpeaker {" +
                super.toString() +
                ", volume='" + volume + '\'' +
                ", channel='" + channel + '\'' +
                ", brand='" + brand+ '\''+
                '}';
    }

    public SmartSpeaker clone() {
        return new SmartSpeaker( this);
    }

    public double getConsume() {
        return 0.72 * (this.volume != 0 ? (1/(this.volume * 0.05)) : 0.01);
    }
}
