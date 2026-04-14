package lab5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import lab5.Entidades.Heroi;

public class HeroiTest {
    private Heroi heroi;
    
    @BeforeEach
    void setUp() {
        heroi = new Heroi("Teste", 30, 5, 10);
    }
    
    @Test
    void testDanoAbsorvidoPorEscudo() {
        heroi.receberDano(3);
        assertEquals(30, heroi.getVida());
        assertEquals(2, heroi.getEscudo());
    }
    
    @Test
    void testDanoParcialComEscudo() {
        heroi.receberDano(8);
        assertEquals(27, heroi.getVida());
        assertEquals(0, heroi.getEscudo());
    }
    
    @Test
    void testDanoSemEscudo() {
        heroi = new Heroi("Teste", 30, 0, 10);
        heroi.receberDano(10);
        assertEquals(20, heroi.getVida());
        assertEquals(0, heroi.getEscudo());
    }
    
    @Test
    void testVidaNaoFicaNegativa() {
        heroi.receberDano(100);
        assertEquals(0, heroi.getVida());
        assertFalse(heroi.estarVivo());
    }
    
    @Test
    void testResetarEscudo() {
        heroi.receberDano(3);
        assertEquals(2, heroi.getEscudo());
        heroi.resetarEscudo();
        assertEquals(0, heroi.getEscudo());
    }
    
    @Test
    void testGetNome() {
        assertEquals("Teste", heroi.getNome());
    }
    
    @Test
    void testGetVidaMaxima() {
        assertEquals(30, heroi.getVidaMaxima());
    }
}