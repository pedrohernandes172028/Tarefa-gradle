package lab6.Cenario;

import java.util.Scanner;

import lab6.Entidades.*;

/**
 * será a representação de uma fase do jogo, podendo ser: batalha, escolha, fogueira ou loja
 */
public abstract class Evento {
    /**nome do evento */
    private String descricao;
    
    public Evento(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
    public abstract boolean iniciar(Heroi heroi, Scanner scanner);
}
