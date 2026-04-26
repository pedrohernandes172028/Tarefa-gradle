package lab6;

import org.junit.jupiter.api.Test;

import lab6.Cenario.Batalha;
import lab6.Cenario.Mapa;
import lab6.Cenario.NoMapa;
import lab6.Entidades.Cobrinha;
import lab6.Entidades.Inimigo;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class MapaTest {
    private Mapa mapa;
    private NoMapa raiz;
    
    @BeforeEach
    void setUp() {
        List<Inimigo> inimigos1 = new ArrayList<>();
        inimigos1.add(new Cobrinha("Teste", 10, 5,  10));
        Batalha batalha1 = new Batalha("Nó Raiz", inimigos1, 10);
        raiz = new NoMapa(batalha1);
        
        List<Inimigo> inimigos2 = new ArrayList<>();
        inimigos2.add(new Cobrinha("Teste2", 10, 5,  10));
        Batalha batalha2 = new Batalha("Nó Filho", inimigos2, 10);
        NoMapa filho = new NoMapa(batalha2);
        
        raiz.adicionarFilho(filho);
        mapa = new Mapa(raiz);
    }
    
    @Test
    void testGetRaiz() {
        assertEquals(raiz, mapa.getRaiz());
    }
    
    @Test
    void testIsNoFinal() {
        assertFalse(mapa.isNoFinal(raiz));
        assertTrue(mapa.isNoFinal(raiz.getFilhos().get(0)));
    }
    
    @Test
    void testGetProximosNos() {
        List<NoMapa> proximos = mapa.getProximosNos(raiz);
        assertEquals(1, proximos.size());
        assertEquals("Nó Filho", proximos.get(0).getEvento().getDescricao());
    }
    
    @Test
    void testNoMapaDescricao() {
        assertEquals("Nó Raiz", raiz.getEvento().getDescricao());
        assertEquals("Nó Filho", raiz.getFilhos().get(0).getEvento().getDescricao());
    }
    
    @Test
    void testNoMapaVisitado() {
        assertFalse(raiz.isVisitado());
        raiz.setVisitado(true);
        assertTrue(raiz.isVisitado());
    }
    
    @Test
    void testNoMapaGetBatalha() {
        assertNotNull(raiz.getEvento());
        Batalha batalha = (Batalha) raiz.getEvento();
            assertEquals(1, batalha.getInimigos().size());
    }
}