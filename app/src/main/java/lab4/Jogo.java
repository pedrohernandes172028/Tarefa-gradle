package lab5;

import java.util.List;
import java.util.Scanner;
import lab5.Entidades.Heroi;

/**
 * Gerencia o fluxo completo do jogo, incluindo navegação no mapa
 */
public class Jogo {
    private Heroi heroi;
    private Mapa mapa;
    private NoMapa noAtual;
    private Scanner scanner;
    private boolean emExecucao;
    
    public Jogo(Heroi heroi, Mapa mapa, NoMapa noAtual, Scanner scanner) {
        this.heroi = heroi;
        this.mapa = mapa;
        this.noAtual = noAtual;
        this.scanner = scanner;
        this.emExecucao = true;
    }
    
    public void iniciar() {
        System.out.println("\n=== INÍCIO DA JORNADA ===");
        System.out.println("Herói: " + heroi.getNome());
        System.out.println("Vida: " + heroi.getVida());
        
        while (emExecucao && heroi.estarVivo()) {
            // Marca o nó atual como visitado
            noAtual.setVisitado(true);
            
            // Mostra informações do nó
            System.out.println("\n=== " + noAtual.getDescricao() + " ===");
            
            // Executa a batalha
            Batalha batalha = noAtual.getBatalha();
            boolean venceu = batalha.executar(heroi, scanner);
            
            if (!venceu) {
                System.out.println("\n=== FIM DE JOGO ===");
                System.out.println("Você foi derrotado em " + noAtual.getDescricao());
                emExecucao = false;
                break;
            }
            
            // Verifica se chegou ao final
            if (mapa.isNoFinal(noAtual)) {
                System.out.println("\n=== VITÓRIA! ===");
                System.out.println("Parabéns! Você completou sua jornada!");
                emExecucao = false;
                break;
            }
            
            // Mostra opções de próximo nó
            if (!progredir()) {
                System.out.println("\nJogo encerrado.");
                emExecucao = false;
            }
        }
    }
    
    /**
     * Permite ao jogador escolher o próximo nó
     * @return true se o jogo deve continuar, false caso contrário
     */
    private boolean progredir() {
        List<NoMapa> proximos = mapa.getProximosNos(noAtual);
        
        if (proximos.isEmpty()) {
            System.out.println("\nNão há mais caminhos disponíveis!");
            return false;
        }
        
        System.out.println("\n=== ESCOLHA O PRÓXIMO DESTINO ===");
        System.out.println("Vida atual: " + heroi.getVida());
        
        for (int i = 0; i < proximos.size(); i++) {
            System.out.println((i + 1) + ". " + proximos.get(i).getDescricao());
        }
        System.out.println("0. Sair do jogo (salvar progresso)");
        
        int escolha;
        while (true) {
            System.out.print("Sua escolha: ");
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                scanner.nextLine();
                
                if (escolha == 0) {
                    System.out.println("Salvando jogo...");
                    return false;
                }
                
                if (escolha >= 1 && escolha <= proximos.size()) {
                    noAtual = proximos.get(escolha - 1);
                    return true;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Opção inválida! Tente novamente.");
        }
    }
    
    public boolean isEmExecucao() {
        return emExecucao;
    }
    
    public Heroi getHeroi() {
        return heroi;
    }
    
    public NoMapa getNoAtual() {
        return noAtual;
    }
}