package lab6.Cenario;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o mapa do jogo como uma estrutura de árvore
 */
public class Mapa {
    private NoMapa raiz;
    
    public Mapa(NoMapa raiz) {
        this.raiz = raiz;
    }
    
    public NoMapa getRaiz() {
        return raiz;
    }
    
    /**
     * Verifica se um nó é folha (final do jogo)
     */
    public boolean isNoFinal(NoMapa no) {
        return no.getFilhos().isEmpty();
    }
    
    /**
     * Obtém os próximos nós disponíveis a partir de um nó
     */
    public List<NoMapa> getProximosNos(NoMapa atual) {
        return new ArrayList<>(atual.getFilhos());
    }
}