package lab5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import lab5.Cenario.Mapa;
import lab5.Cenario.NoMapa;
import lab5.Entidades.Cobrinha;
import lab5.Entidades.Inimigo;

public class MapaTest {
    private Mapa mapa;
    private NoMapa raiz;
    
    @BeforeEach
    void setUp() {
        List<Inimigo> inimigos1 = new ArrayList<>();
        inimigos1.add(new Cobrinha("Teste", 10, 5,  10));
        Batalha batalha1 = new Batalha(inimigos1);
        raiz = new NoMapa(batalha1, "Nó Raiz");
        
        List<Inimigo> inimigos2 = new ArrayList<>();
        inimigos2.add(new Cobrinha("Teste2", 10, 5,  10));
        Batalha batalha2 = new Batalha(inimigos2);
        NoMapa filho = new NoMapa(batalha2, "Nó Filho");
        
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
        assertEquals("Nó Filho", proximos.get(0).getDescricao());
    }
    
    @Test
    void testNoMapaDescricao() {
        assertEquals("Nó Raiz", raiz.getDescricao());
        assertEquals("Nó Filho", raiz.getFilhos().get(0).getDescricao());
    }
    
    @Test
    void testNoMapaVisitado() {
        assertFalse(raiz.isVisitado());
        raiz.setVisitado(true);
        assertTrue(raiz.isVisitado());
    }
    
    @Test
    void testNoMapaGetBatalha() {
        assertNotNull(raiz.getBatalha());
        assertEquals(1, raiz.getBatalha().getInimigos().size());
    }
}