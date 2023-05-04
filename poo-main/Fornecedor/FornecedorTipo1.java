package poo.Fornecedor;

import poo.Fatura.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FornecedorTipo1 extends Fornecedor implements Serializable {
    //@Serial
    private static final long serialVersionUID = 9999L;

    public FornecedorTipo1() {
        super();
    }

    public FornecedorTipo1(
            String nome,
            double precoBase,
            double imposto,
            double multiplicador
    ) {
        super(nome, precoBase, imposto, multiplicador);
    }

    public FornecedorTipo1(
            String nome,
            double precoBase,
            double imposto,
            double multiplicador,
            Map<Integer, List<Fatura>> faturas) {
        super(nome, precoBase, imposto, multiplicador, faturas);
    }

    public FornecedorTipo1(Fornecedor that) {
        super(that);
    }

    @Override
    public double precoDiaPorDispositivo(int numeroDispositivos, double consumoDispositivo) {
        return numeroDispositivos > 10
                ? (this.getPrecoBase() * consumoDispositivo / this.getImposto()) * this.getMultiplicador()
                : (this.getPrecoBase() * consumoDispositivo / this.getImposto()) * (this.getMultiplicador() - 0.1);
    }

    public Fornecedor clone() {
        return new FornecedorTipo1(this);
    }
}
