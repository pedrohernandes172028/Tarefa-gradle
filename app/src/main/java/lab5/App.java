package lab5;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import lab5.Cenario.Mapa;
import lab5.Cenario.NoMapa;
import lab5.Entidades.*;
import lab5.Salvar.DadosJogo;
import lab5.Salvar.JogoSalvo;

public class App {
    private static JogoSalvo gerenciadorSalvamento = new JogoSalvo();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== JOGO DE AVENTURA ===");
        System.out.println("1. Novo Jogo");
        System.out.println("2. Carregar Jogo Salvo");
        System.out.print("Escolha: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine();
        
        Heroi heroi;
        Mapa mapa;
        NoMapa noAtual;
        Jogo jogo;
        mapa = criarMapa(); /*mapa é sempre o mesmo para qualquer partida */
        if (opcao == 2 && gerenciadorSalvamento.existeSalvo()) {
            DadosJogo dados = gerenciadorSalvamento.carregarJogo();
            heroi = dados.getHeroi();
            noAtual = dados.procuraNo(mapa.getRaiz(), dados.getnomeNoAtual());  /*o jogador já venceu esse nó */
            System.out.println("Jogo carregado com sucesso!");
        } else {
            System.out.print("Digite o nome do seu personagem: ");
            String nome = scanner.nextLine();
            heroi = new Heroi(nome, 40, 5, 10);
            noAtual = mapa.getRaiz();
            jogo = new Jogo(heroi, mapa, noAtual, scanner);
            System.out.println("Novo jogo iniciado!");
        }

        jogo = new Jogo(heroi, mapa, noAtual, scanner);

        if (opcao == 2 && gerenciadorSalvamento.existeSalvo()) {
            jogo.progredir();
        }

        if (jogo.iniciar()){
            gerenciadorSalvamento.salvarJogo(heroi, jogo.getNoAtual().getDescricao());
        }

        scanner.close();
    }
    
    /**
     * Cria o mapa do jogo como uma árvore de batalhas
     * @return Mapa configurado
     */
    protected static Mapa criarMapa() { /*deixei protected para poder testar no jacoco*/
        // Nível 1 (raiz)
        List<Inimigo> inimigos1 = new ArrayList<>();
        inimigos1.add(new Cobrinha("Cobra Guardiã", 12, 6, 10));
        Batalha batalha1 = new Batalha(inimigos1);
        NoMapa raiz = new NoMapa(batalha1, "Entrada da Floresta");
        
        // Nível 2
        List<Inimigo> inimigos2 = new ArrayList<>();
        inimigos2.add(new Cobrinha("Cobra Venenosa", 15, 7, 12));
        inimigos2.add(new Cobrinha("Cobra Pequena", 8, 4, 8));
        Batalha batalha2 = new Batalha(inimigos2);
        NoMapa no2 = new NoMapa(batalha2, "Clareira Escura");
        
        List<Inimigo> inimigos3 = new ArrayList<>();
        inimigos3.add(new Cobrinha("Cobra Gigante", 20, 8, 7));
        Batalha batalha3 = new Batalha(inimigos3);
        NoMapa no3 = new NoMapa(batalha3, "Ponte Quebrada");
        
        raiz.adicionarFilho(no2);
        raiz.adicionarFilho(no3);
        
        // Nível 3 (nós folha)
        List<Inimigo> inimigos4 = new ArrayList<>();
        inimigos4.add(new Cobrinha("Cobra Rainha", 25, 10, 15));
        inimigos4.add(new Cobrinha("Cobra Guardiã", 12, 6, 10));
        inimigos4.add(new Cobrinha("Cobra Venenosa", 15, 7, 12));
        Batalha batalha4 = new Batalha(inimigos4);
        NoMapa no4 = new NoMapa(batalha4, "Templo da Serpente");
        
        List<Inimigo> inimigos5 = new ArrayList<>();
        inimigos5.add(new Cobrinha("Cobra Anciã", 30, 12, 18));
        Batalha batalha5 = new Batalha(inimigos5);
        NoMapa no5 = new NoMapa(batalha5, "Covil Final");
        
        no2.adicionarFilho(no4);
        no3.adicionarFilho(no4);
        no3.adicionarFilho(no5);
        
        return new Mapa(raiz);
    }



}