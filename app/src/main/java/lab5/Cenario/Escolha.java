package lab5.Cenario;

import java.util.Scanner;

import lab5.Entidades.Heroi;

/**
 * o Heroi acha uma caixa com itens diferentes, podem representar beneficios ou maleficios, mas o heroi não sabe.
 */
public class Escolha extends Evento{
    private String[] chaves;
    private int[] consequencia;
    public Escolha(String descricao, String[] chaves, int[] consequencia){
        super(descricao);
        this.chaves = chaves;
        this.consequencia = consequencia;
    }
    public boolean iniciar(Heroi heroi, Scanner scanner){
        System.out.println(heroi.getNome() + " encontrou um bau e 3 chaves! Deseja tentar abri-lo usando uma das chaves?");
        
        int opcao;
        while (true){
            System.out.println("Escolha:\n0. Seguir caminho");
            printChaves();
            opcao = scanner.nextInt();
            if (opcao == 0){
                System.out.println(heroi.getNome() + " seguiu o seu caminho!");
                break;
            }else if (opcao > 0 && opcao <= chaves.length){
                if (consequencia[opcao - 1] == 0){
                    System.out.println("O baú abriu! " + heroi.getNome() + " encontrou 10 rupias.");
                    heroi.mudarDinheiro(10);
                }else if (consequencia[opcao - 1] == 1){
                    System.out.println("O baú explodiu! " + heroi.getNome() + " se feriu com a explosão.");
                    heroi.alterarVida(-10);
                    System.out.println(heroi.getNome() + " está com " + heroi.getVida() + " de vida!");
                }else if (consequencia[opcao - 1] == 2){
                    System.out.println("O baú abriu! " + heroi.getNome() + " encontrou uma poção de cura dentro dele.");
                    heroi.alterarVida(10);
                    System.out.println(heroi.getNome() + " está com " + heroi.getVida() + " de vida!");
                }
                break;
            }
            System.out.println("Opção inválida! Tente novamente.");
        }
        return true;
    }

    private void printChaves(){
        for (int i = 0; i < chaves.length; i++){
            System.out.println(i + 1 + ". " + chaves[i]);
        }
    }
}
