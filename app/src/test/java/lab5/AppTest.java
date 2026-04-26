package lab5;

import org.junit.jupiter.api.Test;

import lab5.Cenario.Mapa;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    @Test
    void testCriarMapa() {
        Mapa mapa = App.criarMapa();
        assertNotNull(mapa);
        assertEquals("Entrada da Floresta", mapa.getRaiz().getEvento().getDescricao());
        assertEquals(2, mapa.getRaiz().getFilhos().size());
        
    }
}
