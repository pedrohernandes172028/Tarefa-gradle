package lab5.Cenario;

import java.util.Scanner;
import java.util.ArrayList;
import lab5.Cartas.*;
import lab5.Entidades.Heroi;

public class Loja extends Evento{
    private ArrayList<Carta> cartas;
    private ArrayList<Integer> precos;
    public Loja(String descricao, ArrayList<Carta> cartas, ArrayList<Integer> precos){
        super(descricao);
        this.cartas = cartas;
        this.precos = precos;
    }
    public boolean iniciar(Heroi heroi, Scanner scanner){
        System.out.println("\n=== LOJA ===");
        int opcao;
        while (true){
            System.out.println(heroi.getNome() + " possui " + heroi.getDinheiro() + " rupias!");
            System.out.println("Escolha uma opção:\n0. Sair da Loja\n1. Remover uma carta do deck (5 rupias)");
            printCartasLoja();
            opcao = scanner.nextInt();
            if (opcao == 0){
                System.out.println(heroi.getNome() + " saiu da loja!");
                break;
            } else if (opcao == 1){
                if (heroi.mudarDinheiro(-5)){
                    System.out.println("Escolha uma carta do seu deck para remover: ");
                    heroi.getBaralho().printCartas();
                    opcao = scanner.nextInt();
                    if (opcao > 0 && opcao <= heroi.getBaralho().getDeckCompra().size()){
                        System.out.println(heroi.getBaralho().getDeckCompra().get(opcao - 1).getNome() + " foi removida do deck de " + heroi.getNome() + "!");
                        heroi.getBaralho().removerCarta(opcao - 1);
                    }
                }else{
                    System.out.println("Sem dinheiro suficiente para remover uma carta");
                }
                continue;
            }else if (opcao > 1 && opcao <= cartas.size() + 1){
                if (heroi.mudarDinheiro(-(precos.get(opcao - 2)))){
                    System.out.println(cartas.get(opcao - 2).getNome() + " foi adicionado no deck de " + heroi.getNome() + "!");
                    cartas.remove(opcao - 2);
                    precos.remove(opcao - 2);
                    heroi.getBaralho().adicionarCarta(cartas.get(opcao - 2));
                }else{
                    System.out.println("Sem dinheiro suficiente para comprar essa carta");
                }
                continue;
            }
            System.out.println("Opção inválida! Tente novamente.");
        }

        return true;
    }

    private void printCartasLoja(){
        for (int i = 0; i < cartas.size(); i++){
            System.out.println(i + 2 + ". " + cartas.get(i).getNome() + " (" + precos.get(i) + " rupias)");
        }
    }
}
