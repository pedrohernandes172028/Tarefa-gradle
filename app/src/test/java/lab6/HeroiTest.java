package lab6;

import org.junit.jupiter.api.Test;

import lab6.Entidades.Cobrinha;
import lab6.Entidades.Heroi;
import lab6.Entidades.Inimigo;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class HeroiTest {
    private Heroi heroi;
    
    @BeforeEach
    void setUp() {
        heroi = new Heroi("Teste", 30, 10);
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
        heroi = new Heroi("Teste", 30, 10);
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

    @Test
        void testRealizarAcao() {
        ArrayList<Inimigo> inimigos = new ArrayList<>();

        inimigos.add(new Cobrinha("Cobra", 20, 5, 8));
            heroi.resetarenergia(); 
            heroi.getBaralho().comprarCartas();

            Scanner scannerSimulado = new Scanner("1 1");
            boolean continuaTurno = heroi.realizarAcao(heroi, inimigos, scannerSimulado);
            
            assertTrue(continuaTurno);
            assertEquals(inimigos.get(0), heroi.getAlvo());
        }
}