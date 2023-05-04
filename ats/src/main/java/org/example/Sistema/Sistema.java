package org.example.Sistema;

import org.example.CasaInteligente.*;
import org.example.Fatura.Fatura;
import org.example.Fornecedor.*;
import org.example.Fornecedor.FornecedorComNomeJaExistenteException;
import org.example.Main.CasaExceptionFornecedorNaoExiste;
import org.example.Main.CasaExceptionNifExistente;
import org.example.Main.DispositivoExisteNaDivisaoException;
import org.example.SmartDevice.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import java.util.stream.Collectors;

public class Sistema implements Serializable {
    //@Serial
    private static final long serialVersionUID = 9999L;


    private HashMap<Integer, CasaInteligente> listaCasas;
    private HashMap<String,Fornecedor> listaFornecedores;
    private LocalDate time;

    public Sistema() {
        this.listaCasas=new HashMap<>();
        this.listaFornecedores = new HashMap<>();
        time=LocalDate.now();
    }

    public Sistema(HashMap<Integer,CasaInteligente> listaCasas,HashMap<String,Fornecedor>listaFornecedores) {
        this.setListaCasas(listaCasas);
        this.setListaFornecedores(listaFornecedores);
        time=LocalDate.now();

    }

    public Sistema(Sistema s) {
        this.listaCasas= s.getListaCasas();
        this.listaFornecedores= s.getListaFornecedores();
        time=LocalDate.now();
    }

    public HashMap<Integer, CasaInteligente> getListaCasas() {
        HashMap<Integer,CasaInteligente> c = new HashMap<>();
        for (CasaInteligente a : this.listaCasas.values()) {
            c.put(a.getNIF(),a.clone());
        }
        return c;
    }

    public void setListaCasas(HashMap<Integer, CasaInteligente> listaCasas) {
        this.listaCasas = new HashMap<>();
        for (CasaInteligente a : listaCasas.values()) {
            this.listaCasas.put(a.getNIF(), a.clone());
        }
    }

    public HashMap<String, Fornecedor> getListaFornecedores() {
        HashMap<String,Fornecedor> c = new HashMap<>();
        for (Fornecedor a : this.listaFornecedores.values()) {
            c.put(a.getNome(),a.clone());
        }
        return c;
    }

    public void setListaFornecedores(HashMap<String,Fornecedor> listaFornecedores) {
        this.listaFornecedores = new HashMap<>();
        for (Fornecedor a : listaFornecedores.values()) {
            this.listaFornecedores.put(a.getNome(), a.clone());
        }
    }

    public LocalDate getTime() {
        return LocalDate.from(time);
    }

    public void setTime(LocalDate time) {
        this.time = LocalDate.from(time);
    }

    public int getNrCasasInteligentes() {
        return this.listaCasas.size();
    }

    public int getNrFornecedores() {
        return this.listaFornecedores.size();
    }

    public void adicionaDivisao (int nif,String nome) throws DivisaoJaExisteException {
        this.listaCasas.get(nif).addRoom(nome);
    }

    public void adicionaCasa(CasaInteligente c) throws org.example.Main.CasaExceptionNifExistente, CasaExceptionFornecedorNaoExiste {
        if (!this.listaFornecedores.containsKey(c.getNomeFornecedor()))
            throw new CasaExceptionFornecedorNaoExiste("A Fornecedor indicada não existe!");
        if (this.listaCasas.containsKey(c.getNIF()))
            throw new CasaExceptionNifExistente("O NIF definido já esta atribuído a uma Casa Inteligente!");
        this.listaCasas.put(c.getNIF(),c.clone());
    }

    public boolean existeCasa (int nif) {
        return this.listaCasas.containsKey(nif);
    }

    public void adicionaFornecedor(Fornecedor e) throws FornecedorComNomeJaExistenteException {
        if (this.listaFornecedores.containsKey(e.getNome()))
            throw new FornecedorComNomeJaExistenteException("O nome definido já esta atribuído a uma Fornecedor!");
        this.listaFornecedores.put(e.getNome(),e.clone());
    }

    public boolean existeFornecedor (String nome) {
        return this.listaFornecedores.containsKey(nome);
    }

    public void addDevice(int NIF,String nomeDivisao, SmartDevice s) throws DispositivoExisteNaDivisaoException{
        CasaInteligente c = this.listaCasas.get(NIF);
        if(c.roomHasDevice(nomeDivisao,s.getID()))
            throw new DispositivoExisteNaDivisaoException("O dispositivo já existe nesta divisão");
        c.addDevice(s);
        c.addToRoom(nomeDivisao,s.getID());
    }


    public void guardaEstado (String fich) throws FileNotFoundException, IOException{
        FileOutputStream fos= new FileOutputStream(fich);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public Sistema carregaEstado(String fich) throws FileNotFoundException,IOException,ClassNotFoundException {
        FileInputStream fis= new FileInputStream(fich);
        ObjectInputStream ois= new ObjectInputStream(fis);
        Sistema f = (Sistema) ois.readObject();
        ois.close();
        return f;
    }

    public void simulacao(LocalDate time) {
        LocalDate finalTime= LocalDate.from(time);
        Period t = Period.between(this.getTime(),finalTime);
        double tempo = t.getDays();

        for (CasaInteligente a : this.listaCasas.values()) {
            double valorFaturacao=this.listaFornecedores.get(a.getNomeFornecedor()).precoDiaPorDispositivo(a.getNrDispositivos(),a.getNrHorasDispositivoson()*tempo);
            Fatura fatura= new Fatura(a.getNIF(),valorFaturacao,this.getTime(),finalTime);
            this.listaFornecedores.get(a.getNomeFornecedor()).addFatura(a.getNIF(),fatura);
        }
        this.time=finalTime;
    }

    public String fornecedorQueMaisFaturou() {
        String r = "";
        double maior = -1;
        for(Fornecedor f: this.listaFornecedores.values()) {
            double v = f.getFaturas().values()
                    .stream()
                    .map(a -> a
                            .stream()
                            .mapToDouble(Fatura::getValorFaturacao)
                            .sum())
                    .reduce(0., Double::sum);
            if(v > maior) {
                r = f.getNome();
            }
        }
        return r;
    }

    private double getConsumoDaCasaEm(CasaInteligente c, LocalDate inicio, LocalDate fim) {
        double accum = 0.;
        for(Fornecedor e: this.listaFornecedores.values()) {
            List<Fatura> f = e.getFaturasDoNIF(c.getNIF());
            if(f != null) {
                accum = f.stream()
                        .filter( f1 -> f1.getData_inicio().compareTo(inicio) >= 0 && f1.getData_fim().compareTo(fim) <= 0)
                        .mapToDouble(Fatura :: getValorFaturacao)
                        .sum();
            }
        }
        return accum;
    }

    public CasaInteligente casaQueMaisGastou(LocalDate inicio, LocalDate fim) {
        Comparator<CasaInteligente> c = (c1, c2) -> Double.compare(
                getConsumoDaCasaEm(c2, inicio, fim),
                getConsumoDaCasaEm(c1, inicio, fim)
        );
        return this.listaCasas.values().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(c)))
                .first().clone();
    }

    public List<Fatura> faturasDoFornecedor(String fornecedor) throws CasaExceptionFornecedorNaoExiste {
        Fornecedor r = this.listaFornecedores.get(fornecedor);
        if(r == null) {
            throw new CasaExceptionFornecedorNaoExiste("Fornecedor não existente");
        }
        return r.getFaturas().values()
                .stream()
                .flatMap(List::stream)
                .map(Fatura::clone)
                .collect(Collectors.toList());
    }

    public TreeSet<CasaInteligente> maioresConsumidoresEnergia(LocalDate inicio, LocalDate fim) {
        Comparator<CasaInteligente> c = (c1, c2) -> Double.compare(
                getConsumoDaCasaEm(c2, inicio, fim),
                getConsumoDaCasaEm(c1, inicio, fim)
        );
        return this.listaCasas.values().stream()
                .map(CasaInteligente::clone)
                .collect(Collectors.toCollection(() -> new TreeSet<>(c)));
    }

    public boolean TemFaturas() {
        return this.listaFornecedores.values().stream().anyMatch(Fornecedor::TemFaturas);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sistema sistema = (Sistema) o;
        return this.getListaCasas().equals(sistema.getListaCasas()) && this.getListaFornecedores().equals(sistema.getListaFornecedores());
    }


    @Override
    public String toString() {
        return "Sistema {" + '\n' +
                "listaCasas= " +'\n' + this.getListaCasas() + '\n' +
                "listaFornecedores= "+ '\n' + this.getListaFornecedores() + '\n' +
                '}';
    }

    public Sistema clone() {
        return new Sistema(this);
    }
}

