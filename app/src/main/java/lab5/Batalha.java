package lab5;

import java.util.List;
import java.util.Scanner;
import lab5.Entidades.*;

/**
 * Encapsula a lógica de um combate individual
 */
public class Batalha {
    private List<Inimigo> inimigos;
    private boolean vitoria;
    private boolean concluida;
    
    public Batalha(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
        this.vitoria = false;
        this.concluida = false;
    }
    
    /**
     * Executa a batalha até o fim
     * @param heroi O herói que participará da batalha
     * @param scanner Scanner para entrada do usuário
     * @return true se o herói venceu, false caso contrário
     */
    public boolean executar(Heroi heroi, Scanner scanner) {
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
            System.out.println("\nVitória! Você derrotou todos os inimigos!");
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