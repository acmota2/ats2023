package poo.Fornecedor;

import poo.Fatura.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FornecedorTipo2 extends Fornecedor implements Serializable {
    //@Serial
    private static final long serialVersionUID = 9999L;

    public FornecedorTipo2() {
        super();
    }

    public FornecedorTipo2(
            String nome,
            double precoBase,
            double imposto,
            double multiplicador
    ) {
        super(nome, precoBase, imposto, multiplicador);
    }

    public FornecedorTipo2(
            String nome,
            double precoBase,
            double imposto,
            double multiplicador,
            Map<Integer, List<Fatura>> faturas) {
        super(nome, precoBase, imposto, multiplicador, faturas);
    }

    public FornecedorTipo2(Fornecedor that) {
        super(that);
    }

    @Override
    public double precoDiaPorDispositivo( int nrdispositivos, double consumoDispositivo) {
        return (this.getPrecoBase() * consumoDispositivo / this.getImposto()) * this.getMultiplicador();
    }

    public Fornecedor clone() {
        return new FornecedorTipo2(this);
    }
}