package poo.Fatura;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Fatura implements Comparable<Fatura> , Serializable {
    //@Serial
    private static final long serialVersionUID = 9999L;

    private int NIF;
    private double valorFaturacao;
    private LocalDate data_inicio;
    private LocalDate data_fim;

    public Fatura() {
        this.NIF=0;
        this.valorFaturacao=0;
        this.data_inicio= LocalDate.now();
        this.data_fim= LocalDate.now() ;
    }

    public Fatura(int NIF, double valorFaturacao, LocalDate data_inicio, LocalDate data_fim) {
        this.NIF = NIF;
        this.valorFaturacao = valorFaturacao;
        this.setData_inicio(data_inicio);
        this.setData_fim(data_fim);
    }

    public Fatura (Fatura a ) {
        this.NIF=a.NIF;
        this.valorFaturacao=a.valorFaturacao;
        this.data_inicio= a.getData_inicio();
        this.data_fim= a.getData_fim();
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public double getValorFaturacao() {
        return valorFaturacao;
    }

    public void setValorFaturacao(double valorFaturacao) {
        this.valorFaturacao = valorFaturacao;
    }

    public LocalDate getData_inicio() {
        return LocalDate.from(this.data_inicio);
    }

    public void setData_inicio(LocalDate data) {
        this.data_inicio= LocalDate.from(data);
    }

    public LocalDate getData_fim() {
        return LocalDate.from (this.data_fim);
    }

    public void setData_fim(LocalDate data) {
        this.data_fim = LocalDate.from(data);
    }

    public int compareTo(Fatura that) {
        double compare = this.valorFaturacao - that.valorFaturacao;
        if(compare < 0) {
            return -1;
        } else if(compare == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return this.getNIF() == fatura.getNIF() && fatura.getValorFaturacao() == this.getValorFaturacao() && Objects.equals(getData_inicio(), fatura.getData_inicio()) && Objects.equals(getData_fim(), fatura.getData_fim());
    }

    public String toString() {
        return "Fatura{" +
                "NIF=" + NIF +
                ", valorFaturação=" + valorFaturacao +
                ", data_inicio=" + data_inicio +
                ", data_fim=" + data_fim +
                '}' +'\n';
    }

    public Fatura clone() {
        return new Fatura(this);
    }
}
