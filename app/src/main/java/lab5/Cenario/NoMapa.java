package lab5.Cenario;

import java.util.ArrayList;
import java.util.List;

import lab5.Batalha;

/**
 * Representa um nó no mapa, contendo uma batalha e conexões para outros nós
 */
public class NoMapa {
    private Batalha batalha;
    private String descricao;
    private List<NoMapa> filhos;
    private boolean visitado;
    
    public NoMapa(Batalha batalha, String descricao) {
        this.batalha = batalha;
        this.descricao = descricao;
        this.filhos = new ArrayList<>();
        this.visitado = false;
    }
    
    public Batalha getBatalha() {
        return batalha;
    }
    
    public String getDescricao() {
        return descricao;
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
    
    @Override
    public String toString() {
        return descricao;
    }
}