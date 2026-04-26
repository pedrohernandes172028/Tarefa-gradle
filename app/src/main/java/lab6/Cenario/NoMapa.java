package lab6.Cenario;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó no mapa, contendo uma batalha e conexões para outros nós
 */
public class NoMapa {
    private Evento evento;
    private List<NoMapa> filhos;
    private boolean visitado;
    
    public NoMapa(Evento evento) {
        this.evento = evento;
        this.filhos = new ArrayList<>();
        this.visitado = false;
    }
    
    public Evento getEvento() {
        return evento;
    }
    
    public List<NoMapa> getFilhos() {
        return filhos;
    }
    
    public boolean isVisitado() {
        return visitado;
    }
    
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    public void adicionarFilho(NoMapa filho) {
        filhos.add(filho);
    }
    
}