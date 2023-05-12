

import org.example.Fatura.Fatura;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.example.Fornecedor.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class FornecedorTest {

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    // Rever se queremos por aqui alguma cena !!!!!!!!!!!!!!!!!!!!!!!!!!!11
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testConstructor() {
        Fornecedor fornc = new FornecedorTipo1();
        assertNotNull(fornc);
        fornc = new FornecedorTipo1("FC Porto",10,23,5);
        assertNotNull(fornc);
        Fornecedor fornc2 = new FornecedorTipo1(fornc);
        assertNotNull(fornc2);
    }

    /*
    @Test
    public void testequals(){
        Fornecedor f1 = new FornecedorTipo1("FC Porto",10,23,5);
        assertTrue(f1.equals(f1) == true);
        Fornecedor f2 = new FornecedorTipo1("Vitória SC",10,23,5);
        assertFalse(f1.equals(f2) == true);
        Fornecedor f3 = new FornecedorTipo1("Vitória SC",10,23,5);
        assertFalse(f1.equals(f3) == true || f2.equals(f3) == true);
        Fornecedor f4 = new FornecedorTipo1();
        assertTrue(f4.equals(f4) == false);

    }


    // REVER ESTE TESTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1!!!!!!!!!!!!!!!!!

    @Test
    public void testprecoDiaPorDispositivo() {
        Fornecedor f1 = new FornecedorTipo1("FC Porto",1,0,1);
        assertTrue(f1.precoDiaPorDispositivo(1, 0.149) == 1); // NOTA: REVER ESTE TESTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    */


    @Test
    public void testClone1() {
        String nome = "Fornecedor1";
        double precoBase = 100.0;
        double imposto = 0.23;
        double multiplicador = 1.2;

        Fornecedor fornecedor = new FornecedorTipo1(nome, precoBase, imposto, multiplicador);
        FornecedorTipo1 clone = (FornecedorTipo1) fornecedor.clone();

        assertEquals(fornecedor.getNome(), clone.getNome());
        assertEquals(fornecedor.getPrecoBase(), clone.getPrecoBase());
        assertEquals(fornecedor.getImposto(), clone.getImposto());
        assertEquals(fornecedor.getMultiplicador(), clone.getMultiplicador());
        assertNotSame(fornecedor, clone);
    }

    @Test
    public void testClone2() {
        String nome = "Fornecedor1";
        double precoBase = 100.0;
        double imposto = 0.23;
        double multiplicador = 1.2;

        Fornecedor fornecedor = new FornecedorTipo2(nome, precoBase, imposto, multiplicador);
        FornecedorTipo2 clone = (FornecedorTipo2) fornecedor.clone();

        assertEquals(fornecedor.getNome(), clone.getNome());
        assertEquals(fornecedor.getPrecoBase(), clone.getPrecoBase());
        assertEquals(fornecedor.getImposto(), clone.getImposto());
        assertEquals(fornecedor.getMultiplicador(), clone.getMultiplicador());
        assertNotSame(fornecedor, clone);
    }

    @Test
    public void testSetPrecoBase1() {
        float precoBase = 200.0F;
        Fornecedor fornecedor = new FornecedorTipo1();
        fornecedor.setPrecoBase(precoBase);

        assertEquals(precoBase, fornecedor.getPrecoBase());
    }
    @Test
    public void testSetPrecoBase2() {
        float precoBase = 200.0F;
        Fornecedor fornecedor = new FornecedorTipo2();
        fornecedor.setPrecoBase(precoBase);

        assertEquals(precoBase, fornecedor.getPrecoBase());
    }

    @Test
    public void testSetImposto1() {
        float imposto = 0.17F;
        Fornecedor fornecedor = new FornecedorTipo1();
        fornecedor.setImposto(imposto);

        assertEquals(imposto, fornecedor.getImposto());
    }
    @Test
    public void testSetImposto2() {
        float imposto = 0.18F;
        Fornecedor fornecedor = new FornecedorTipo2();
        fornecedor.setImposto(imposto);

        assertEquals(imposto, fornecedor.getImposto());
    }

    @Test
    public void testSetMultiplicador1() {
        float multiplicador = 1.5F;
        Fornecedor fornecedor = new FornecedorTipo1();
        fornecedor.setMultiplicador(multiplicador);

        assertEquals(multiplicador, fornecedor.getMultiplicador());
    }

    @Test
    public void testSetMultiplicador2() {
        float multiplicador = 1.2F;
        Fornecedor fornecedor = new FornecedorTipo2();
        fornecedor.setMultiplicador(multiplicador);

        assertEquals(multiplicador, fornecedor.getMultiplicador());
    }


    @Test
    public void testSetFaturas1() {
        Fornecedor fornecedor = new FornecedorTipo1();
        Map<Integer, List<Fatura>> mapFaturaList = new HashMap<>();
        Fatura fatura = new Fatura(31342,23.4,LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 13));
        List<Fatura> faturaList = new ArrayList<>();
        faturaList.add(fatura);
        mapFaturaList.put(2023,faturaList);
        fornecedor.setFaturas(mapFaturaList);
        fornecedor.addFatura(31342, fatura);
        List<Fatura> expected = fornecedor.getFaturas().get(2023);
        assertThat(expected, is(fornecedor.getFaturas().get(2023)));
    }


    @Test
    public void testSetFaturas2() {
        Fornecedor fornecedor = new FornecedorTipo2();
        Map<Integer, List<Fatura>> mapFaturaList = new HashMap<>();
        Fatura fatura = new Fatura(31342,23.4,LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 13));
        List<Fatura> faturaList = new ArrayList<>();
        faturaList.add(fatura);
        mapFaturaList.put(2023,faturaList);
        fornecedor.setFaturas(mapFaturaList);
        fornecedor.addFatura(31342, fatura);
        List<Fatura> expected = fornecedor.getFaturas().get(2023);
        assertThat(expected, is(fornecedor.getFaturas().get(2023)));
    }


    @Test
    public void testGetFaturasDoNIF1() {
        List<Fatura> faturaList = new ArrayList<>();
        Fatura fatura = new Fatura(31342,23.4,LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 13));
        faturaList.add(fatura);

        Map<Integer, List<Fatura>> mapFaturas = new HashMap<>();
        mapFaturas.put(2023, faturaList);

        Fornecedor fornecedor = new FornecedorTipo1();
        fornecedor.setFaturas(mapFaturas);
        fornecedor.addFatura(31342, fatura);

        List<Fatura> actualFaturas = fornecedor.getFaturasDoNIF(31342);

        assertThat(faturaList, is(actualFaturas));
    }

    @Test
    public void testGetFaturasDoNIF2() {
        List<Fatura> faturaList = new ArrayList<>();
        Fatura fatura = new Fatura(31342,23.4,LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 13));
        faturaList.add(fatura);

        Map<Integer, List<Fatura>> mapFaturas = new HashMap<>();
        mapFaturas.put(2023, faturaList);

        Fornecedor fornecedor = new FornecedorTipo2();
        fornecedor.setFaturas(mapFaturas);
        fornecedor.addFatura(31342, fatura);

        List<Fatura> actualFaturas = fornecedor.getFaturasDoNIF(31342);

        assertThat(faturaList, is(actualFaturas));
    }

    @Test
    public void testAddFatura1() {
        int nif = 123456789;
        Fatura fatura = new Fatura();

        Fornecedor fornecedor = new FornecedorTipo1();
        fornecedor.addFatura(nif, fatura);

        assertTrue(fornecedor.getFaturas().containsKey(nif));
        assertTrue(fornecedor.getFaturas().get(nif).contains(fatura));
    }

    @Test
    public void testAddFatura2() {
        int nif = 123456789;
        Fatura fatura = new Fatura();

        Fornecedor fornecedor = new FornecedorTipo2();
        fornecedor.addFatura(nif, fatura);

        assertTrue(fornecedor.getFaturas().containsKey(nif));
        assertTrue(fornecedor.getFaturas().get(nif).contains(fatura));
    }


    @Test
    public void testTemFaturas1() {
        Fornecedor fornecedor = new FornecedorTipo1();
        assertFalse(fornecedor.TemFaturas());

        List<Fatura> faturaList = new ArrayList<>();
        Fatura fatura = new Fatura(31342,232.4,LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 13));
        faturaList.add(fatura);

        Map<Integer, List<Fatura>> mapFaturas = new HashMap<>();
        mapFaturas.put(2023, faturaList);

        fornecedor.setFaturas(mapFaturas);
        fornecedor.addFatura(31342, fatura);

        assertTrue(fornecedor.TemFaturas());
    }


    @Test
    public void testTemFaturas2() {
        Fornecedor fornecedor = new FornecedorTipo2();
        assertFalse(fornecedor.TemFaturas());

        List<Fatura> faturaList = new ArrayList<>();
        Fatura fatura = new Fatura(31342,232.4,LocalDate.of(2023, 5, 12), LocalDate.of(2023, 5, 13));
        faturaList.add(fatura);

        Map<Integer, List<Fatura>> mapFaturas = new HashMap<>();
        mapFaturas.put(2023, faturaList);

        fornecedor.setFaturas(mapFaturas);
        fornecedor.addFatura(31342, fatura);

        assertTrue(fornecedor.TemFaturas());
    }

}
