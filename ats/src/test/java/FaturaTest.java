import org.example.Fatura.Fatura;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// NOTA : FAZER ESTE E O FATURA SIMULÇÃO SE NECESSAŔIO
// FALAR COM MPTA

public class FaturaTest {
    /**
     * Default constructor for test class CasaInteligenteTest
     */
    /*
    public CasaInteligenteTest()
    {
    }
     */

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    // Rever se queremos por aqui alguma cena !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
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

    }

    @Test
    public void testGetNIF() {
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        assertEquals(123456789, fatura.getNIF());
    }

    @Test
    public void testSetNIF() {
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        fatura.setNIF(987654321);
        assertEquals(987654321, fatura.getNIF());
    }

    @Test
    public void testGetValorFaturacao() {
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        assertEquals(100.0, fatura.getValorFaturacao());
    }

    @Test
    public void testSetValorFaturacao() {
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        fatura.setValorFaturacao(200.0);
        assertEquals(200.0, fatura.getValorFaturacao());
    }

    @Test
    public void testGetData_inicio() {
        LocalDate dataInicio = LocalDate.of(2023, 5, 1);
        Fatura fatura = new Fatura(123456789, 100.0, dataInicio, LocalDate.now());
        assertEquals(dataInicio, fatura.getData_inicio());
    }

    @Test
    public void testSetData_inicio() {
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        LocalDate dataInicio = LocalDate.of(2023, 5, 1);
        fatura.setData_inicio(dataInicio);
        assertEquals(dataInicio, fatura.getData_inicio());
    }

    @Test
    public void testGetData_fim() {
        LocalDate dataFim = LocalDate.of(2023, 5, 31);
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), dataFim);
        assertEquals(dataFim, fatura.getData_fim());
    }

    @Test
    public void testSetData_fim() {
        Fatura fatura = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        LocalDate dataFim = LocalDate.of(2023, 5, 31);
        fatura.setData_fim(dataFim);
        assertEquals(dataFim, fatura.getData_fim());
    }

    @Test
    public void testCompareTo() {
        Fatura fatura1 = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        Fatura fatura2 = new Fatura(987654321, 200.0, LocalDate.now(), LocalDate.now());
        assertTrue(fatura1.compareTo(fatura2) < 0);
        assertTrue(fatura2.compareTo(fatura1) > 0);
        assertEquals(0, fatura1.compareTo(fatura1));
    }

    @Test
    public void testEquals() {
        Fatura fatura1 = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        Fatura fatura2 = new Fatura(123456789, 100.0, LocalDate.now(), LocalDate.now());
        Fatura fatura3 = new Fatura(987654321, 200.0, LocalDate.now(), LocalDate.now());
        assertEquals(fatura1, fatura2);
        assertNotEquals(fatura1, fatura3);
    }

    @Test
    public void testDefaultConstructor() {
        Fatura fatura = new Fatura();
        assertEquals(0, fatura.getNIF());
        assertEquals(0.0, fatura.getValorFaturacao());
        assertEquals(LocalDate.now(), fatura.getData_inicio());
        assertEquals(LocalDate.now(), fatura.getData_fim());
    }

    @Test
    public void testParameterizedConstructor() {
        int nif = 123456789;
        double valorFaturacao = 100.0;
        LocalDate dataInicio = LocalDate.of(2023, 5, 1);
        LocalDate dataFim = LocalDate.of(2023, 5, 31);

        Fatura fatura = new Fatura(nif, valorFaturacao, dataInicio, dataFim);

        assertEquals(nif, fatura.getNIF());
        assertEquals(valorFaturacao, fatura.getValorFaturacao());
        assertEquals(dataInicio, fatura.getData_inicio());
        assertEquals(dataFim, fatura.getData_fim());
    }

    @Test
    public void testClone() {
        int nif = 123456789;
        double valorFaturacao = 100.0;
        LocalDate dataInicio = LocalDate.of(2023, 5, 1);
        LocalDate dataFim = LocalDate.of(2023, 5, 31);

        Fatura fatura = new Fatura(nif, valorFaturacao, dataInicio, dataFim);
        Fatura clone = fatura.clone();

        assertEquals(fatura.getNIF(), clone.getNIF());
        assertEquals(fatura.getValorFaturacao(), clone.getValorFaturacao());
        assertEquals(fatura.getData_inicio(), clone.getData_inicio());
        assertEquals(fatura.getData_fim(), clone.getData_fim());
        assertNotSame(fatura, clone);
    }

}
