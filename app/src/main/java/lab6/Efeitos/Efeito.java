package lab5.Efeitos;
import lab5.Entidades.Entidade;

public abstract class Efeito {
    private String nome;
    private int acumulo;
    /** conjunto de momentos que ocorrem durante o jogo em que o efeito é acionado */
    private int[] momentos; 

    private transient Entidade dono;

    public Efeito(String nome, Entidade dono, int acumulo, int[] momentos){
        this.nome = nome;
        this.dono = dono;
        this.acumulo = acumulo;
        this.momentos = momentos;
    }
    public String getString(){
        return dono + " possui " + acumulo + " de " + nome + "!";
    }
    public String getNome(){
        return nome;
    }
    public int getAcumulo(){
        return acumulo;
    }
    public Entidade getDono(){
        return dono;
    }
    public int[] getMomentos(){
        return momentos;
    }

    public void mudarDono(Entidade dono){
        this.dono = dono;
    }


    /**
     * soma um valor no atributo acumulo e remove esse efeito do seu dono caso acumulo menor ou igual a 0 
     * @param valor valor que vai ser somado ao acúmulo
     */
    public void alterarAcumulo(int valor){
        acumulo += valor;
        if (acumulo <= 0){
            getDono().removerEfeito(this);
        }
    }
    public abstract void acionado();
}