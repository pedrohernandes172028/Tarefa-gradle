package lab5;

import lab5.Entidades.*;


/**
 * encapsula o estado atual do jogo para que possa ser salvo
 */
public class DadosJogo {
    private Heroi heroi;
    private String nomeNoAtual;

    public DadosJogo(Heroi heroi, String nomeNoAtual){  
        this.heroi = heroi;
        this.nomeNoAtual = nomeNoAtual;
    }

    public Heroi getHeroi(){
        return heroi;
    }
    public String getnomeNoAtual(){
        return nomeNoAtual;
    }

    public NoMapa procuraNo(NoMapa atual, String nomeNoMapa){  /*Busca em profundidade */

        if (atual == null){
            return null;
        }
        
        if (atual.getDescricao().equalsIgnoreCase(nomeNoMapa)){
            return atual;
        }

        for (NoMapa filho : atual.getFilhos()){
            NoMapa encontrado = procuraNo(filho, nomeNoMapa);

            if (encontrado != null){
                return encontrado;
            }
        }
        
        return null;
    }
}
