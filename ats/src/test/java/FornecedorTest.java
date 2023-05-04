

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.Fornecedor.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        assertTrue(fornc != null);
        fornc = new FornecedorTipo1("FC Porto",10,23,5);
        assertTrue(fornc != null);
        Fornecedor fornc2 = new FornecedorTipo1(fornc);
        assertTrue(fornc2 != null);
    }

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
}
