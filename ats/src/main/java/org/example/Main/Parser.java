package org.example.Main;

import org.example.Fornecedor.*;
import org.example.Sistema.Sistema;
import org.example.SmartDevice.*;
import org.example.CasaInteligente.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {
    public Sistema parse(double valorBase, double imposto, String nomeFicheiro) throws FileNotFoundException {
        Sistema s = new Sistema();

        List<String> linhas = lerFicheiro(nomeFicheiro);
        if(linhas == null) {
            throw new FileNotFoundException();
        }
        String[] linhaPartida;
        String divisao = null;
        HashMap<Integer, CasaInteligente> casas = new HashMap<>();
        HashMap<String, Fornecedor> fornecedores = new HashMap<>();
        int id = 0;
        int nif = 0;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            Random r = new Random();
            switch(linhaPartida[0]){
                case "Fornecedor": {
                    // verificar bem esta parte
                    double multiplicador = (0.65 + 0.3) * r.nextDouble();
                    fornecedores.put(linhaPartida[1], r.nextBoolean()
                            ? new FornecedorTipo1(
                                    linhaPartida[1],
                                    valorBase,
                                    imposto,
                                    multiplicador
                            )
                            : new FornecedorTipo2(
                                    linhaPartida[1],
                                    valorBase,
                                    imposto,
                                    multiplicador
                            )
                    );
                    break;
                }
                case "Casa": {
                    CasaInteligente c = parseCasa(linhaPartida[1]);
                    casas.put(nif = c.getNIF(), c);
                    break;
                }
                case "Divisao": {
                    if (nif == 0) {
                        System.out.println("Linha inválida.");
                    } else {
                        divisao = linhaPartida[1];
                        try {
                            casas.get(nif).addRoom(divisao);
                        } catch(DivisaoJaExisteException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
                case "SmartBulb": {
                    if (divisao == null) {
                        System.out.println("Linha inválida.");
                    }
                    SmartBulb sd = parseSmartBulb(linhaPartida[1], id);
                    casas.get(nif).addDevice(sd);
                    casas.get(nif).addToRoom(divisao, sd.getID());
                    ++id;
                    break;
                }
                case "SmartCamera": {
                    if (divisao == null) {
                        System.out.println("Linha inválida.");
                    }
                    SmartCamera sd = parseSmartCamera(linhaPartida[1], id);
                    casas.get(nif).addDevice(sd);
                    casas.get(nif).addToRoom(divisao, sd.getID());
                    ++id;
                    break;
                }
                case "SmartSpeaker": {
                    if (divisao == null) {
                        System.out.println("Linha inválida.");
                    }
                    SmartSpeaker sd = parseSmartSpeaker(linhaPartida[1], id);
                    casas.get(nif).addDevice(sd);
                    casas.get(nif).addToRoom(divisao, sd.getID());
                    ++id;
                    break;
                }
                default: {
                    System.out.println("Linha inválida.");
                }
            }
        }
        s.setListaFornecedores(fornecedores);
        s.setListaCasas(casas);
        return s;
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch(IOException exc) {
            System.out.println(exc.getMessage());
            lines = null;
        }
        return lines;
    }

    public CasaInteligente parseCasa(String input){
        String[] campos = input.split(",");

        return new CasaInteligente(
                campos[0],
                Integer.parseInt(campos[1]),
                campos[2]
        );
    }

    public SmartBulb parseSmartBulb(String input, int id) {
        String[] fields = input.split(",");
        int tone;
        switch (fields[0]) {
            case "Neutral": tone = SmartBulb.NEUTRAL; break;
            case "Warm": tone = SmartBulb.WARM; break;
            case "Cold": tone = SmartBulb.COLD; break;
            default: {
                tone = 0;
                System.out.println("Not a valid light tone,");
            }
        }
        return new SmartBulb(
                Integer.toString(id),
                tone,
                Double.parseDouble(fields[1])
        );
    }

    public SmartCamera parseSmartCamera(String input, int id) {
        String[] fields = input.split(",");
        return new SmartCamera(
                Integer.toString(id),
                fields[0],
                Integer.parseInt(fields[1])
        );
    }

    public SmartSpeaker parseSmartSpeaker(String input, int id) {
        String[] fields = input.split(",");
        return new SmartSpeaker(
                Integer.toString(id),
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2]
        );
    }
}