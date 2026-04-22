package lab5.Salvar;

import lab5.Cenario.NoMapa;
import lab5.Entidades.*;


/**
 * encapsula o estado atual do jogo para que possa ser salvo
 */
public class DadosJogo {
    private Heroi heroi;
    private String nomeNoAtual; /*apenas o nome de uma fase Nó*/

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

    /**
     * encontra a fase Nó que possui o mesmo nome de nomeNoMapa
     * @param atual  nó atual enquanto a busca em profudade vai andando no mapa
     * @param nomeNoMapa  nome da fase que queremos encontrar no mapa
     * @return  retorna o Nó da fase procurada
     */
    public NoMapa procuraNo(NoMapa atual, String nomeNoMapa){  /*Busca em profundidade*/

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
