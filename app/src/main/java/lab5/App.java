package lab5;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import lab5.Cenario.*;
import lab5.Entidades.*;
import lab5.Cartas.*;
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
            heroi = new Heroi(nome, 40, 10);
            noAtual = mapa.getRaiz();
            jogo = new Jogo(heroi, mapa, noAtual, scanner);
            System.out.println("Novo jogo iniciado!");
        }

        jogo = new Jogo(heroi, mapa, noAtual, scanner);

        if (opcao == 2 && gerenciadorSalvamento.existeSalvo()) {
            jogo.progredir();
        }

        if (jogo.iniciar()){
            gerenciadorSalvamento.salvarJogo(heroi, jogo.getNoAtual().getEvento().getDescricao());
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
        Batalha batalha1 = new Batalha("Entrada da Floresta", inimigos1, 5);
        NoMapa raiz = new NoMapa(batalha1);
        
        // Baú 1
        Escolha escolha1 = new Escolha("Baú de madeira", new String[]{"chave da morte", "chave de cura", "chave da cobiça"}, new int[]{1, 2, 0});
        NoMapa noBau1 = new NoMapa(escolha1);
        raiz.adicionarFilho(noBau1);

        // Fogueira 1
        Fogueira fogueira1 = new Fogueira("Fogueira pequena");
        NoMapa noFogueira1 = new NoMapa(fogueira1);
        noBau1.adicionarFilho(noFogueira1);

        // Nível 2
        List<Inimigo> inimigos2 = new ArrayList<>();
        inimigos2.add(new Cobrinha("Cobra Venenosa", 15, 7, 12));
        inimigos2.add(new Cobrinha("Cobra Pequena", 8, 4, 8));
        Batalha batalha2 = new Batalha("Clareira Escura", inimigos2, 10);
        NoMapa no2 = new NoMapa(batalha2);
        
        List<Inimigo> inimigos3 = new ArrayList<>();
        inimigos3.add(new Cobrinha("Cobra Gigante", 20, 8, 7));
        Batalha batalha3 = new Batalha("Ponte Quebrada", inimigos3, 10);
        NoMapa no3 = new NoMapa(batalha3);
        
        noFogueira1.adicionarFilho(no2);
        noFogueira1.adicionarFilho(no3);
        
        // Loja
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new CartaDano("espada de ferro", "causa 15 de dano ao alvo / custa 3 de energia", 3));
        cartas.add(new CartaEscudo("escudo de ferro", "concede 10 de escudo ao alvo / custa 2 de energia", 2));
        cartas.add(new CartaCura("Cura divina", "cura 15 pontos de vida do alvo / custa 3 de energia", 3));
        ArrayList<Integer> precos = new ArrayList<>();
        precos.add(10);
        precos.add(7);
        precos.add(5);
        Loja loja = new Loja("Mercado do Seu Zé", cartas, precos);
        NoMapa noLoja = new NoMapa(loja);
        no2.adicionarFilho(noLoja);
        no3.adicionarFilho(noLoja);

        // Fogueira 2
        Fogueira fogueira2 = new Fogueira("Fogueira grande");
        NoMapa noFogueira2 = new NoMapa(fogueira2);
        no2.adicionarFilho(noFogueira2);    

        // Baú 2
        Escolha escolha2 = new Escolha("Baú de ferro", new String[]{"chave do corvo", "chave da cobra", "chave do urso"}, new int[]{0, 1, 2});
        NoMapa noBau2 = new NoMapa(escolha2);
        no3.adicionarFilho(noBau2);

        // Nível 3 (nós folha)
        List<Inimigo> inimigos4 = new ArrayList<>();
        inimigos4.add(new Cobrinha("Cobra Rainha", 25, 10, 15));
        inimigos4.add(new Cobrinha("Cobra Guardiã", 12, 6, 10));
        inimigos4.add(new Cobrinha("Cobra Venenosa", 15, 7, 12));
        Batalha batalha4 = new Batalha("Templo da Serpente", inimigos4, 30);
        NoMapa no4 = new NoMapa(batalha4);
        
        List<Inimigo> inimigos5 = new ArrayList<>();
        inimigos5.add(new Cobrinha("Cobra Anciã", 60, 12, 18));
        Batalha batalha5 = new Batalha("Covil Final", inimigos5, 30);
        NoMapa no5 = new NoMapa(batalha5);
        
        noLoja.adicionarFilho(no4);
        noLoja.adicionarFilho(no5);
        noBau2.adicionarFilho(no4);
        noFogueira2.adicionarFilho(no5);
        
        return new Mapa(raiz);
    }



}