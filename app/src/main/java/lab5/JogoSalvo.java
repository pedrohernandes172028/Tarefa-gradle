package lab5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;


import lab5.Cartas.Carta;
import lab5.Cartas.CartaCura;
import lab5.Cartas.CartaDano;
import lab5.Cartas.CartaEscudo;
import lab5.Cartas.CartaForca;
import lab5.Cartas.CartaLentidao;
import lab5.Cartas.CartaMortalForca;
import lab5.Cartas.CartaMortalVeneno;
import lab5.Cartas.CartaVelocidade;
import lab5.Cartas.CartaVeneno;
import lab5.Efeitos.Efeito;
import lab5.Efeitos.Forca;
import lab5.Efeitos.Veneno;
import lab5.Entidades.*;
import java.io.*;

/**
 * classe que cuida do arquivo a ser salvo no computador
 */
public class JogoSalvo {
    private final Gson gson;
    private static final String arquivo = "save.json";


    public JogoSalvo(){
        GsonBuilder builder = new GsonBuilder();

        // Criamos o adaptador para a classe abstrata Carta
        JsonDeserializer<Carta> adaptadorDeCartas = (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();
            
            // Pegamos o valor de 'momentos' que está no JSON
            int momentos = jsonObject.get("momentos").getAsInt();

            // Lógica de decisão baseada nos valores que você definiu:
            if (momentos == 2) {
                return context.deserialize(json, CartaDano.class);
            }else if (momentos == 4){
                return context.deserialize(json, CartaEscudo.class);
            }else if(momentos == 6){
                return context.deserialize(json, CartaForca.class);
            }else if(momentos == 8){
                return context.deserialize(json, CartaVeneno.class);
            }else if(momentos == 10){
                return context.deserialize(json, CartaCura.class);
            }else if(momentos == 12){
                return context.deserialize(json, CartaVelocidade.class);
            }else if(momentos == 14){
                return context.deserialize(json, CartaLentidao.class);
            }else if(momentos == 16){
                return context.deserialize(json, CartaMortalForca.class);
            }else{
                return context.deserialize(json, CartaMortalVeneno.class);
            }
        };

        builder.registerTypeAdapter(Efeito.class, (JsonDeserializer<Efeito>) (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray momentosArray = jsonObject.getAsJsonArray("momentos");
            int primeiroMomento = momentosArray.get(0).getAsInt();

            if (primeiroMomento == 2) {
                return context.deserialize(json, Forca.class);
            } 
            else{
                return context.deserialize(json, Veneno.class);
            }
        });

        // Registramos o adaptador e criamos o Gson
        this.gson = builder
                .registerTypeAdapter(Carta.class, adaptadorDeCartas)
                .setPrettyPrinting()
                .create();
    }


    /**
     * verifica se já existe um jogo salvo na máquina
     * @return retorna true caso exista jogo salvo, caso contrário, retorna false
     */
    public boolean existeSalvo(){
        File file = new File(arquivo);  /*procurando arquivo na memória */
        return file.exists();
    }

    /**
     * salva os dados do jogo em um arquivo
     * @param heroi heroi no momento que o jogo foi salvo
     * @param nomeNoAtual   nome do noAtual no momento que o jogo foi salvo
     */
    public void salvarJogo(Heroi heroi, String nomeNoAtual){
        DadosJogo dados = new DadosJogo(heroi, nomeNoAtual);

        try (FileWriter writer = new FileWriter(arquivo)) { /*criando um arquivo para salvar o jogo */
            gson.toJson(dados, writer); /*passando os dados para o arquivo criado */
            System.out.println("Jogo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo!");
        }
    }
    
    /**
     * carrega um arquivo salvo do jogo e passa para um atributo de classe DadosJogo
     * @return  retorna o atributo de classe DadosJogo (retorna null caso o metodo não ache um arquivo salvo)
     */
    public DadosJogo carregarJogo(){
        try (FileReader reader = new FileReader(arquivo)) { /*tentando ler um arquivo salvo */
            DadosJogo dados = gson.fromJson(reader, DadosJogo.class); /*passando o arquivo salvo para o formato da classe DadosJogo*/
                Heroi heroi = dados.getHeroi();
                for (Efeito efeito : heroi.getEfeitos()) {  /*preenchedo o atributo dono novamente */
                    efeito.mudarDono(heroi);
                }
            return dados;  
            
        } catch (IOException e) {
            System.out.println("Erro ao carregar o jogo!");
            return null;
        }
    }


}
