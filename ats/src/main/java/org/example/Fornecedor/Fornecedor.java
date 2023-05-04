package org.example.Fornecedor;

import org.example.Fatura.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Fornecedor implements Serializable {
    // @Serial
    private static final long serialVersionUID = 9999L;
    private final String nome;
    private double precoBase;
    private double imposto;
    private double multiplicador;
    private Map<Integer, List<Fatura>> faturas;

    public Fornecedor() {
        this.nome = "";
        this.precoBase = 0.;
        this.imposto = 0.;
        this.multiplicador = 0.;
        this.faturas = new HashMap<>();
    }

    public Fornecedor(
            String nome,
            double precoBase,
            double imposto,
            double multiplicador
    ) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.imposto = imposto;
        this.multiplicador = multiplicador;
        this.faturas = new HashMap<Integer, List<Fatura>>();
    }

    public Fornecedor(
            String nome,
            double precoBase,
            double imposto,
            double multiplicador,
            Map<Integer, List<Fatura>> faturas) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.imposto = imposto;
        this.multiplicador = multiplicador;
        this.setFaturas(faturas);
    }

    public Fornecedor(Fornecedor that) {
        this.nome = that.getNome();
        this.precoBase = that.getPrecoBase();
        this.imposto = that.getImposto();
        this.multiplicador = that.getMultiplicador();
        this.faturas = that.getFaturas();
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) { this.precoBase = precoBase; }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(float imposto) {
        this.imposto = imposto;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador = multiplicador;
    }

    public void setFaturas(Map<Integer, List<Fatura>> faturas) {
        Map<Integer, List<Fatura>> f = new HashMap<>();
        for(int i: this.faturas.keySet()) {
            List<Fatura> l = this.faturas.get(i)
                    .stream()
                    .map(Fatura::clone)
                    .collect(Collectors.toList());
            f.put(i, l);
        }
        this.faturas = f;
    }

    public Map<Integer, List<Fatura>> getFaturas() {
        Map<Integer, List<Fatura>> r = new HashMap<>();

        for(int i: this.faturas.keySet()) {
            List<Fatura> l = this.faturas.get(i)
                    .stream()
                    .map(Fatura::clone)
                    .collect(Collectors.toList());
            r.put(i, l);
        }
        return r;
    }

    public List<Fatura> getFaturasDoNIF(int nif) {
        return this.faturas.get(nif)
                .stream()
                .map(Fatura::clone)
                .collect(Collectors.toList());
    }

    public void addFatura(int nif, Fatura f) {
        this.faturas.computeIfAbsent(nif, k -> new ArrayList<>());
        this.faturas.get(nif).add(f.clone());
    }

    public boolean TemFaturas() {
        return this.faturas.values().size()!=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor fornecedor = (Fornecedor) o;
        return Double.compare(fornecedor.precoBase, precoBase) == 0
                && Double.compare(fornecedor.imposto, imposto) == 0
                && Double.compare(fornecedor.multiplicador, multiplicador) == 0
                && Objects.equals(nome, fornecedor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, precoBase, imposto, multiplicador);
    }

    @Override
    abstract public Fornecedor clone();

    public abstract double precoDiaPorDispositivo(int numeroDispositivos, double consumoDispositivo);
}
