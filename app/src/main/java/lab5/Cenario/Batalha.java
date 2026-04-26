package lab5.Cenario;

import java.util.List;
import java.util.Scanner;

import lab5.Entidades.*;

/**
 * Encapsula a lógica de um combate individual
 */
public class Batalha extends Evento{
    private List<Inimigo> inimigos;
    private boolean vitoria;
    private boolean concluida;
    /** quantidade de dinherio que o heroi vai ganhar se vencer esta batalha */
    private int recompensa;
    
    public Batalha(String descricao, List<Inimigo> inimigos, int recompensa) {
        super(descricao);
        this.inimigos = inimigos;
        this.vitoria = false;
        this.concluida = false;
        this.recompensa = recompensa;
    }
    
    /**
     * Executa a batalha até o fim
     * @param heroi O herói que participará da batalha
     * @param scanner Scanner para entrada do usuário
     * @return true se o herói venceu, false caso contrário
     */
    public boolean iniciar(Heroi heroi, Scanner scanner) {
        System.out.println("\n=== BATALHA ===");
        System.out.println("Inimigos:");
        for (Inimigo inimigo : inimigos) {
            System.out.println("- " + inimigo.getNome());
        }
        System.out.println();
        
        // Cria uma cópia dos inimigos para não afetar o original
        List<Inimigo> inimigosBatalha = copiarInimigos();
        Combate combate = new Combate(heroi, inimigosBatalha);
        
        vitoria = combate.realizarBatalhaCompleta(scanner);
        concluida = true;
        
        if (vitoria) {
            heroi.reiniciarHeroi();
            heroi.mudarDinheiro(recompensa);
            System.out.println(heroi.getNome() + " ganhou " + recompensa + " rupias como recompensa!");
            System.out.println(heroi.getNome() + " possui " + heroi.getDinheiro() + " rupias.");
        } else {
            System.out.println("\nDerrota! Seu herói foi derrotado...");
        }
        
        return vitoria;
    }
    
    /**
     * Cria cópias dos inimigos para a batalha
     */
    private List<Inimigo> copiarInimigos() {
        List<Inimigo> copias = new java.util.ArrayList<>();
        for (Inimigo inimigo : inimigos) {
            copias.add(new Cobrinha(
                inimigo.getNome(),
                inimigo.getVidaMaxima(),
                inimigo.getEscudo(),
                inimigo.getVelocidade()
            ));
        }
        return copias;
    }
    
    public boolean isVitoria() {
        return vitoria;
    }
    
    public boolean isConcluida() {
        return concluida;
    }
    
    public List<Inimigo> getInimigos() {
        return inimigos;
    }
}