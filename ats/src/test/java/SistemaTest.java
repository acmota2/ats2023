import org.example.CasaInteligente.CasaInteligente;
import org.example.CasaInteligente.DivisaoJaExisteException;
import org.example.Fornecedor.Fornecedor;
import org.example.Sistema.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    private Sistema sistema;
    private HashMap<Integer, CasaInteligente> listaCasas;
    private HashMap<String, Fornecedor> listaFornecedores;
    @BeforeEach
    public void setup() {
        listaCasas = new HashMap<>();
        listaFornecedores = new HashMap<>();
        sistema = new Sistema(listaCasas, listaFornecedores);
    }

    @Test
    public void testGetListaCasas() {
        HashMap<Integer, CasaInteligente> casas = sistema.getListaCasas();
        assertNotNull(casas);
        assertEquals(0, casas.size());
    }

    @Test
    public void testSetListaCasas() {
        CasaInteligente casa1 = new CasaInteligente();
        casa1.setNIF(12345);
        listaCasas.put(12345, casa1);

        sistema.setListaCasas(listaCasas);
        HashMap<Integer, CasaInteligente> casas = sistema.getListaCasas();
        assertNotNull(casas);
        assertEquals(1, casas.size());
        assertEquals(casa1, casas.get(12345));
    }

    @Test
    public void testGetListaFornecedores() {
        HashMap<String, Fornecedor> fornecedores = sistema.getListaFornecedores();
        assertNotNull(fornecedores);
        assertEquals(0, fornecedores.size());
    }


    @Test
    public void testGetTime() {
        LocalDate time = sistema.getTime();
        assertNotNull(time);
        assertEquals(LocalDate.now(), time);
    }

    @Test
    public void testSetTime() {
        LocalDate time = LocalDate.of(2023, 1, 1);
        sistema.setTime(time);
        LocalDate updatedTime = sistema.getTime();
        assertNotNull(updatedTime);
        assertEquals(time, updatedTime);
    }

    @Test
    public void testGetNrCasasInteligentes() {
        assertEquals(0, sistema.getNrCasasInteligentes());
    }

    @Test
    public void testGetNrFornecedores() {
        assertEquals(0, sistema.getNrFornecedores());
    }

    @Test
    public void testAdicionaDivisao() {
        CasaInteligente casa = new CasaInteligente();
        casa.setNIF(12345);
        listaCasas.put(12345, casa);
        sistema.setListaCasas(listaCasas);

        assertThrows(DivisaoJaExisteException.class, () -> sistema.adicionaDivisao(12345, "Sala"));
        assertDoesNotThrow(() -> sistema.adicionaDivisao(12345, "Quarto"));

    }

}
