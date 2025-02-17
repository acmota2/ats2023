
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
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice implements Serializable {
    //@Serial
    private static final long serialVersionUID = 9999L;

    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    
    private int tone;
    private double height;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        // initialise instance variables
        super();
        this.tone = NEUTRAL;
        this.height=0;
    }

    public SmartBulb(String id, int tone, double height) {
        // initialise instance variables
        super(id);
        this.tone =tone;
        this.height= height;

    }

    public SmartBulb(String id) {
        // initialise instance variables
        super(id);
        this.tone = NEUTRAL;
        this.height =0;
    }

    public SmartBulb(SmartBulb c) {
        // initialise instance variables
        super(c.getID(), c.getOn());
        this.tone = c.tone;
        this.height= c.height;
    }

    public void setTone(int t) {
        if (t>=WARM) this.tone = WARM;
        else this.tone = Math.max(t, COLD);
    }

    public int getTone() {
        return this.tone;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartBulb smartBulb = (SmartBulb) o;
        return super.equals(smartBulb)
                && this.getTone() == smartBulb.getTone()
                && this.height == smartBulb.height;
    }

    @Override
    public String toString() {
        return "SmartBulb{" +
                super.toString() +
                ", tone='" + tone +  '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    public SmartBulb clone() {
        return new SmartBulb (this);
    }

    public double getConsume(){
        return 0.32 + this.tone*0.02 + this.height*0.005;
    }
}

