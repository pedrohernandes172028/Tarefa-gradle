package lab5.Cartas;
import lab5.Entidades.Entidade;


public class CartaDano extends Carta {

    public CartaDano(String nome, String descricao, int custo){
        super(nome, descricao, custo, 2);
    }
    /** causa dano ao alvo proporcional ao custo da carta */
    public void usar(Entidade alvo){  
        alvo.receberDano(getCusto() * 5);
        System.out.println(alvo.getNome() + " recebeu " + getCusto() * 5 + " de dano.\n");
    }
}