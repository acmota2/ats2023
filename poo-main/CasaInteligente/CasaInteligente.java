package poo.CasaInteligente;

/**
 * A CasaInteligente faz a gestão dos SmartDevices que existem e dos 
 * espaços (as salas) que existem na casa.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import poo.SmartDevice.SmartDevice;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.lang.String;
import java.lang.Object;
import java.util.HashSet;
import poo.SmartDevice.*;


public class CasaInteligente implements Serializable {
    private String Nome_proprietario;
    private int NIF;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, Set<String>> locations; // Espaço -> Lista codigo dos devices
    private String nomeFornecedor;

    /**
     * Constructor for objects of class CasaInteligente
     */
    public CasaInteligente() {
        // initialise instance variables
        this.Nome_proprietario = "";
        this.NIF=0;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.nomeFornecedor="";
    }

    public CasaInteligente(String Nome_proprietario, int nif,String nome) {
        // initialise instance variables
        this.Nome_proprietario = Nome_proprietario;
        this.NIF=nif;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.nomeFornecedor=nome;
    }

    public CasaInteligente (CasaInteligente casa) {
        this.Nome_proprietario = casa.Nome_proprietario;
        this.NIF=casa.NIF;
        this.devices = casa.getDevices();
        this.locations = casa.getLocations();
        this.nomeFornecedor= casa.getNomeFornecedor();
    }

    public String getNome_proprietario() {
        return Nome_proprietario;
    }

    public void setNome_proprietario(String nome_proprietario) {
        Nome_proprietario = nome_proprietario;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public HashMap<String, SmartDevice> getDevices() {
        HashMap<String, SmartDevice> res= new HashMap<String, SmartDevice>();
        for (SmartDevice s : this.devices.values()) {
            res.put(s.getID(),s.clone());
        }
        return res;
    }

    public void setDevices(Map<String, SmartDevice> dev) {
        for (SmartDevice s : dev.values()) {
            this.devices.put(s.getID(),s.clone());
        }
    }

    public HashMap<String, Set<String>> getLocations() {
        HashMap<String, Set<String>> res = new HashMap<>();
        for (String k : this.locations.keySet()) {
            HashSet<String> divDevices = new HashSet<>(this.locations.get(k));
            res.put(k,divDevices);
        }
        return res;
    }

    public void setLocations(Map<String, Set<String>> loc) {
        this.locations = new HashMap<>();
        for (String k: loc.keySet()) {
            HashSet<String> divDevices = new HashSet<>(loc.get(k));
            this.locations.put(k, divDevices);
        }
    }

    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public boolean existsDevice(String id) {
        return this.devices.containsKey(id);
    }
    
    public void addDevice(SmartDevice s) {
        this.devices.put(s.getID(), s.clone());
    }
    
    public SmartDevice getDevice(String s) {
            if (this.devices.containsKey(s))
                return this.devices.get(s).clone();
            else
                return new SmartBulb();
    }
    
    public void setOn(String s, boolean b) {
        if (this.devices.containsKey(s))
            this.devices.get(s).setOn(b);
    }
    
    public void setAllOn(boolean b) {
        for (SmartDevice i : this.devices.values()) {
            i.setOn(b);
        }
    }

    public int getNrDispositivos() {
        return this.devices.values().size();
    }
    
    public void addRoom(String s) throws DivisaoJaExisteException{
        if (this.hasRoom(s))
            throw new DivisaoJaExisteException("Esta Divisão já existe!");
        HashSet <String> nr= new HashSet<>();
        this.locations.put(s,nr);
    }
    
    public boolean hasRoom(String s) {
        return this.locations.containsKey(s);
    }
    
    public void addToRoom (String s1, String s2) {
        this.locations.get(s1).add(s2);
    }
    
    public boolean roomHasDevice (String s1, String s2) {
        return this.locations.get(s1).contains(s2);
    }

    public double getNrHorasDispositivoson() {
       return this.devices.values()
               .stream()
               .filter(SmartDevice::getOn)
               .mapToDouble(SmartDevice::getConsume)
               .sum();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CasaInteligente that = (CasaInteligente) o;
        return this.Nome_proprietario.equals(that.Nome_proprietario)
                && this.NIF == that.NIF
                && this.nomeFornecedor.equals(that.nomeFornecedor)
                && this.devices.equals(that.devices)
                && this.locations.equals(that.locations);
    }

    public String toString() {
        return "CasaInteligente{" +
                "Nome Proprietário='" + this.Nome_proprietario  + '\'' +
                ", NIF = '"+ this.NIF + '\'' +
                ", Fornecedor = '" +this.nomeFornecedor+ '\''+
                ", devices='" + this.devices +  '\''+
                ", locations='" + this.locations + '\'' +
                '}'+ '\n';
    }

    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

}
