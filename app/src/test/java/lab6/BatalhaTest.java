package lab6;

import org.junit.jupiter.api.Test;

import lab6.Cenario.Batalha;
import lab6.Entidades.Cobrinha;
import lab6.Entidades.Inimigo;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class BatalhaTest {
    private Batalha batalha;
    private List<Inimigo> inimigos;
    
    @BeforeEach
    void setUp() {
        inimigos = new ArrayList<>();
        inimigos.add(new Cobrinha("Inimigo1", 10, 5, 10));
        batalha = new Batalha("Teste", inimigos, 10);

    }
    
    @Test
    void testGetInimigos() {
        assertEquals(1, batalha.getInimigos().size());
        assertEquals("Inimigo1", batalha.getInimigos().get(0).getNome());
    }
    
    @Test
    void testIsConcluidaInicial() {
        assertFalse(batalha.isConcluida());
    }
    
    @Test
    void testIsVitoriaInicial() {
        assertFalse(batalha.isVitoria());
    }
    
    @Test
    void testBatalhaNaoExecutada() {
        assertFalse(batalha.isConcluida());
        assertFalse(batalha.isVitoria());
    }
}