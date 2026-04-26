package lab6.Cenario;

import java.util.Scanner;

import lab6.Cartas.*;
import lab6.Entidades.Heroi;

/**
 * evento para recuperar vida ou para clonar uma carta do baralho
 */
public class Fogueira extends Evento{
    
    public Fogueira(String descricao){
        super(descricao);
    }
    public boolean iniciar(Heroi heroi, Scanner scanner){        
        int opcao;
        while (true){
            System.out.println("Escolha:\n1. Descansar (cura e aumenta a vida máxima)\n2. Clonar (ganhe uma carta igual a uma no seu baralho)");
            opcao = scanner.nextInt();
            if (opcao == 1){
                int cura = (int) (heroi.getVidaMaxima() * 0.3);
                System.out.println(heroi.getNome() + " dormiu diante da fogueira. Você aumentou " + cura + " da sua vida máxima e se curou");
                heroi.aumentarVidaMaxima(cura);
                heroi.alterarVida(cura);
                break;
            }else if (opcao == 2){
                System.out.println("Escolha uma carta do seu deck para clonar: ");
                heroi.getBaralho().printCartas();
                opcao = scanner.nextInt();
                if (opcao > 0 && opcao <= heroi.getBaralho().getDeckCompra().size()){
                    Carta clonada = heroi.getBaralho().getDeckCompra().get(opcao - 1);
                    heroi.getBaralho().adicionarCarta(clonada);
                    System.out.println(clonada.getNome() + " foi clonada!");
                    break;
                }
            }
            System.out.println("Opção inválida! Tente novamente.");
        }
        return true;
    }
}
