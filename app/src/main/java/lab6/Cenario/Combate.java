package lab5.Cenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lab5.Entidades.Entidade;
import lab5.Entidades.Heroi;
import lab5.Entidades.Inimigo;

/**
 * Controla ordem de turnos, realização de ações de inimigos e heroi
 */
public class Combate {  
    private List<Entidade> ordenado; 
    private List<Inimigo> inimigos;
    private Heroi heroi; 

    public Combate(Heroi heroi, List<Inimigo> inimigos){
        this.heroi = heroi;
        this.inimigos = inimigos;
    }
    
    /**
     * Realiza uma batalha completa até a vitória ou derrota
     */
    public boolean realizarBatalhaCompleta(Scanner scanner) {
        heroi.resetarEscudo();
        heroi.resetarenergia();
        
        while (true) {
            boolean continuar = realizarTurno(scanner);
            if (!continuar) {
                return heroi.estarVivo() && inimigos.isEmpty();
            }
        }
    }

    /**
     * Realiza um turno do jogo
     */
    public boolean realizarTurno(Scanner comandoScanner){
        ordenado = ordenar();
        printordenado(); 
        heroi.resetarenergia();
        heroi.getBaralho().comprarCartas();
        boolean continua;
        Entidade jogando;
        verificarEfeitosGeral(0);
        if (verificarMortos()){
            if (heroi.estarVivo() == false || inimigos.isEmpty()){
                return false;
            }
        }
        statusPersonagens();
        decidirAcoes();
        for (int j = 0; j < ordenado.size(); j ++){
            jogando = ordenado.get(j);
            if (jogando.estarVivo() == false){
                continue;
            }
            continua = true;
            while (continua){
                if (jogando instanceof Heroi && j != 0){
                    statusPersonagens();
                }
                continua = jogando.realizarAcao(heroi, inimigos, comandoScanner);
                if (jogando.getAcaoEscolhida() != null && ordenado.get(j).getAlvo() != null) {
                    System.out.println();   
                    verificarEfeitosAlvo(jogando.getAcaoEscolhida().getMomentos(), jogando);
                    verificarEfeitosAlvo(jogando.getAcaoEscolhida().getMomentos() + 1, jogando.getAlvo());
                }
                if (verificarMortos()){
                    if (heroi.estarVivo() == false || inimigos.isEmpty()){
                        return false;
                    }
                    j = ordenado.indexOf(jogando);
                }
            }
        }
        heroi.resetarEscudo();
        verificarEfeitosGeral(1);
        if (verificarMortos()){
            if (heroi.estarVivo() == false || inimigos.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private void statusPersonagens(){
        System.out.println("Status dos personagens:");
        heroi.getStatus();
        for (int l = 0; l < inimigos.size(); l++){
            inimigos.get(l).getStatus();
        }
        System.out.println();
    }    

    private void decidirAcoes(){
        System.out.println("Anúncio dos inimigos:");
        for (int l = 0; l < inimigos.size(); l++){
            inimigos.get(l).anuncio(heroi, inimigos); 
        }
        System.out.println();
    }

    private ArrayList<Entidade> ordenar(){
        Entidade guarda;
        ArrayList<Entidade> desordenado = new ArrayList<>(inimigos);
        desordenado.add(heroi);
        for (int i = desordenado.size(); i > 0; i--){
            for (int j = 0; j < i - 1; j++){
                if (desordenado.get(j).getVelocidade() < desordenado.get(j + 1).getVelocidade()){
                    guarda = desordenado.get(j);
                    desordenado.set(j, desordenado.get(j + 1));
                    desordenado.set(j + 1, guarda);
                }
            }
        }
        return desordenado;
    }

    private void printordenado(){
        System.out.println("Ordem dos personagens:");
        for (int i = 0; i < ordenado.size(); i++){
            System.out.println((i + 1) + " -> " + ordenado.get(i).getNome());
        }
        System.out.println();
    }

    private void verificarEfeitosGeral(int momento){
        for (int i = 0; i < ordenado.size(); i ++){
            verificarEfeitosAlvo(momento, ordenado.get(i));
        }
    }

    private void verificarEfeitosAlvo(int momento, Entidade alvo){
        boolean acionou = false;
        for (int j = alvo.getEfeitos().size() - 1; j >= 0; j--){
            for (int k = 0; k < alvo.getEfeitos().get(j).getMomentos().length; k++){
                if (alvo.getEfeitos().get(j).getMomentos()[k] == momento){
                    alvo.getEfeitos().get(j).acionado();
                    acionou = true;
                    break;
                }
            }
        }
        if (acionou){
            System.out.println();
        }
    }

    private boolean verificarMortos(){  
        boolean houveMorte = false;
        for (int i = inimigos.size() - 1; i >= 0; i--) {
            if (!inimigos.get(i).estarVivo()) {
                System.out.println(inimigos.get(i).getNome() + " foi derrotado!");
                ordenado.remove(inimigos.get(i));
                inimigos.remove(i);
                houveMorte = true;
            }
        }
        if (inimigos.isEmpty()){
            System.out.println("Todos os inimigos foram derrotados! Você venceu.");
            return true;
        } else if (heroi.estarVivo() == false){
            System.out.println(heroi.getNome() + " foi derrotado! Você perdeu.");
            return true;
        }
        return houveMorte;
    }
}