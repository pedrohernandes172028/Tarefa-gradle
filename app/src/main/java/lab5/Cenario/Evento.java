package lab5.Cenario;

import java.util.Scanner;

import lab5.Entidades.*;

public abstract class Evento {
    private String descricao;
    
    public Evento(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
    public abstract boolean iniciar(Heroi heroi, Scanner scanner);
}
