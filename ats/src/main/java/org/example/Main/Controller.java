package org.example.Main;

import org.example.CasaInteligente.CasaInteligente;
import org.example.CasaInteligente.DivisaoJaExisteException;
import org.example.Fatura.Fatura;
import org.example.Fornecedor.Fornecedor;
import org.example.Fornecedor.FornecedorComNomeJaExistenteException;
import org.example.Fornecedor.FornecedorTipo1;
import org.example.Fornecedor.FornecedorTipo2;
import org.example.Sistema.*;
import org.example.SmartDevice.SmartBulb;
import org.example.SmartDevice.SmartCamera;
import org.example.SmartDevice.SmartDevice;
import org.example.SmartDevice.SmartSpeaker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Controller {
    private Sistema sistema;

    public Controller() {
        this.sistema=new Sistema();
    }

    public boolean Precondition2() {
        return this.sistema.getNrFornecedores()>0;
    }

    public boolean Precondition4() {
        return this.Precondition2() && this.sistema.getNrCasasInteligentes()>0;
    }

    public boolean Precondition6() {
        return this.Precondition2() && this.sistema.TemFaturas() ;
    }


    public void addRoom(int nif,String nome) throws DivisaoJaExisteException{
        this.sistema.adicionaDivisao(nif,nome);

    }
    public void InsereCasa(String nome,int nif,String nomeFornecedor) {
        try {
            CasaInteligente c= new CasaInteligente(nome,nif,nomeFornecedor);
            this.sistema.adicionaCasa(c);
        } catch (NumberFormatException e) {
            System.out.println("Formato do NIF errado! Não foi possível adicionar a Casa Inteligente!");
        } catch (CasaExceptionNifExistente | CasaExceptionFornecedorNaoExiste e) {
            System.out.println(e.getMessage() + " Não foi possível adicionar a Casa Inteligente!");
        }
    }

    public void InsereFornecedor(String nome, double precobase,double  imposto,double multiplicador) {

            try {
                Random r= new Random();
                 Fornecedor f= r.nextBoolean()
                                ? new FornecedorTipo1(
                                nome,
                                precobase,
                                imposto,
                                multiplicador
                        )
                                : new FornecedorTipo2(
                                nome,
                                precobase,
                                imposto,
                                multiplicador
                        );
                this.sistema.adicionaFornecedor(f);
        } catch (NumberFormatException e) {
            System.out.println("O último dado introduzido não está na forma correta. Não foi possível adicionar o dispositivo!");
        } catch (FornecedorComNomeJaExistenteException e) {
            System.out.println(e.getMessage() + " Não foi possível adicionar a Empresa!");
        }
    }

    public String getSistema () {
        return this.sistema.toString();
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema.clone();
    }

    public void guardaEstado(String nome) throws FileNotFoundException, IOException {
        sistema.guardaEstado(nome);
    }

    public void carregaEstado(String nome) throws FileNotFoundException, IOException, ClassNotFoundException {
        //sistema = new Sistema();
        this.sistema = this.sistema.carregaEstado(nome);
    }


    public String executaQuery1(LocalDate datainic,LocalDate dataFim) {
       return  this.sistema.casaQueMaisGastou(datainic,dataFim).toString();
    }

    public String executaQuery2() {
        return this.sistema.fornecedorQueMaisFaturou();
    }

    public String executaQuery3(String nome) throws CasaExceptionFornecedorNaoExiste {
        return this.sistema.faturasDoFornecedor(nome).toString();
    }

    public String  executaQuery4(LocalDate datainic, LocalDate dataFim) {
       return  this.sistema.maioresConsumidoresEnergia(datainic,dataFim).toString();
    }

    public void simulacao(LocalDate date) throws DateTimeParseException {
        sistema.simulacao(date);
    }

    public LocalDate getTime () {
        return this.sistema.getTime();
    }

    public void addDevice(int nif, String nomeDivisao, SmartDevice smartDevice) throws DispositivoExisteNaDivisaoException {
        this.sistema.addDevice(nif,nomeDivisao,smartDevice);
    }

    public void setFornecedorEm(int nif, String fornecedor) throws CasaExceptionFornecedorNaoExiste, RuntimeException {
        if(!this.sistema.getListaFornecedores().containsKey(fornecedor)) {
            throw new CasaExceptionFornecedorNaoExiste("Não é um fornecedor válido");
        }
        if(!this.sistema.getListaCasas().containsKey(nif)) {
            throw new RuntimeException("Não é um NIF válido");
        }
        this.sistema.getListaCasas().get(nif).setNomeFornecedor(fornecedor);
    }
}
