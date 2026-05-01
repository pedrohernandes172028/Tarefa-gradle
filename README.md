História:
Por enquanto, você é um herói lutando contra um filhote de cobra. Derrote ela ou morra tentando!

Descrição:
Crie o seu personagem e lute contra uma cobrinha. No começo de cada turno, compre cartas da pilha de compras até ficar com 5 na sua mão. Durante esse processo, se ficar sem cartas na pilha de compras, embaralhe a sua pilha de descarte e use-a como a sua nova pilha de compras.
Com as cartas em mãos, use elas gastando suas 3 energias por turno e as descarte na pilha de descarte. Depois que estiver sem energia ou quiser passar sua vez, a cobrinha irá atacar! 
Depois do ataque dela, será seu turno novamente. Assim, o jogo acaba quando os pontos de vida de um dos dois chegue a zero.

Explicação do funcionamento dos efeitos:
Efeitos: possuem o atributo momentos, que serve para armazenar todos os momentos com que um efeito é ativado, sejá pela ativação das cartas ou pelo começo e  final do turno.
Cartas: as cartas que envolvem efeitos já declaram os atributos desses efeitos, pretendemos adicionar mais cartas com os mesmos efeitos mas com mais pontos de dano, etc. Além disso, as cartas possuem o atributo momentos, que serve para ser comparado com os momentos dos efeitos, por exemplo, ao usar a cartaDano, o efeito força é ativado pelo seu dono. Além disso, o momento da carta somado a 1 representa o momento quando alguem recebe essa carta, por exemplo, ao usar a cartaDano, o efeito Destreza (que ainda não foi implementado) é ativado  pelo alvo dessa carta.
Lista da representação de cada inteiro para os momentos:
0 = inicio de turno
1 = final de turno
2 = ao usar cartaDano
3 = ao receber cartaDano
4= ao usar cartaEscudo
5 = ao receber cartaEscudo 
6 = ao usar cartaForca
7 = ao receber cartaForca
8 = ao usar cartaVeneno
9 = ao receber cartaVeneno
10 = ao usar cartaCura
11 = ao receber cartaCura
12 = ao usar cartaVelocidade
13 = ao receber cartaVelocidade
14 = ao usar cartaLentidão
15 = ao receber cartaLentidão
16 = ao usar cartaMortalForca
17 = ao receber cartaMortalForca
18 = ao usar cartaMortalVeneno
19 = ao receber cartaMortalVeneno

Cartas novas:
cartaCura, cartaVelocidade, cartaLentidão, cartaMortalForca e cartaMortalVeneno.

Compilar:
javac -d bin $(find src -name "*.java")

Executar:
java -cp bin App

Diagrama de Classes:
classDiagram
    %% Pacote Entidades
    class Entidade {
        <<abstract>>
        -String nome
        -int vida
        -int vidaMaxima
        -int escudo
        -int velocidade
        -ArrayList~Efeito~ efeitos
        -Carta acaoEscolhida
        -Entidade alvo
        +receberDano(int)
        +alterarVida(int)
        +alterarVelocidade(int)
        +ganharEscudo(int)
        +estarVivo() boolean
        +aplicarEfeito(Efeito)
        +removerEfeito(Efeito)
        +getForca() Efeito
        +getVeneno() Efeito
        +mudarEscolha(int)*
        +realizarAcao()* boolean
        +cartaUtilizada(int)* Carta
    }
    
    class Heroi {
        -Baralho baralho
        -int energia
        -int dinheiro
        +resetarEscudo()
        +resetarenergia()
        +reiniciarHeroi()
        +mudarDinheiro(int) boolean
        +realizarAcao() boolean
        +cartaUtilizada(int) Carta
        +mudarEscolha(int)
    }
    
    class Inimigo {
        <<abstract>>
        #Carta[] acoes
        +anuncio()*
        +realizarAcao() boolean
        +cartaUtilizada(int) Carta
        +mudarEscolha(int)
    }
    
    class Cobrinha {
        +anuncio()
        -determinarAcoes()
    }
    
    class Baralho {
        -List~Carta~ cartasNaMao
        -Stack~Carta~ deckCompra
        -Stack~Carta~ deckDescarte
        +comprarCartas()
        +cartaUsada(int)
        +adicionarCarta(Carta)
        +removerCarta(int)
        +reiniciarBaralho()
        +printCartas()
    }
    
    %% Pacote Cartas
    class Carta {
        <<abstract>>
        -String nome
        -String descricao
        -int custo
        -int momentos
        +usar(Entidade)*
        +getCusto() int
        +getMomentos() int
    }
    
    class CartaDano {
        +usar(Entidade)
    }
    
    class CartaCura {
        +usar(Entidade)
    }
    
    class CartaEscudo {
        +usar(Entidade)
    }
    
    class CartaForca {
        +usar(Entidade)
    }
    
    class CartaVeneno {
        +usar(Entidade)
    }
    
    class CartaVelocidade {
        +usar(Entidade)
    }
    
    class CartaLentidao {
        +usar(Entidade)
    }
    
    class CartaMortalForca {
        +usar(Entidade)
    }
    
    class CartaMortalVeneno {
        +usar(Entidade)
    }
    
    %% Pacote Efeitos
    class Efeito {
        <<abstract>>
        -String nome
        -int acumulo
        -int[] momentos
        -Entidade dono
        +alterarAcumulo(int)
        +acionado()*
        +mudarDono(Entidade)
        +getAcumulo() int
    }
    
    class Forca {
        +acionado()
    }
    
    class Veneno {
        +acionado()
    }
    
    %% Pacote Cenario
    class Evento {
        <<abstract>>
        -String descricao
        +iniciar(Heroi, Scanner)* boolean
        +getDescricao() String
    }
    
    class Batalha {
        -List~Inimigo~ inimigos
        -int recompensa
        -boolean vitoria
        -boolean concluida
        +iniciar(Heroi, Scanner) boolean
        -copiarInimigos() List~Inimigo~
    }
    
    class Loja {
        -ArrayList~Carta~ cartas
        -ArrayList~Integer~ precos
        +iniciar(Heroi, Scanner) boolean
        -printCartasLoja()
    }
    
    class Fogueira {
        +iniciar(Heroi, Scanner) boolean
    }
    
    class Escolha {
        -String[] chaves
        -int[] consequencia
        +iniciar(Heroi, Scanner) boolean
        -printChaves()
    }
    
    class Combate {
        -List~Entidade~ ordenado
        -List~Inimigo~ inimigos
        -Heroi heroi
        +realizarBatalhaCompleta(Scanner) boolean
        +realizarTurno(Scanner) boolean
        -ordenar() ArrayList~Entidade~
        -verificarEfeitosGeral(int)
        -verificarEfeitosAlvo(int, Entidade)
        -verificarMortos() boolean
    }
    
    class Mapa {
        -NoMapa raiz
        +isNoFinal(NoMapa) boolean
        +getProximosNos(NoMapa) List~NoMapa~
    }
    
    class NoMapa {
        -Evento evento
        -List~NoMapa~ filhos
        -boolean visitado
        +adicionarFilho(NoMapa)
    }
    
    %% Pacote Salvar
    class DadosJogo {
        -Heroi heroi
        -String nomeNoAtual
        +procuraNo(NoMapa, String) NoMapa
    }
    
    class JogoSalvo {
        -Gson gson
        -String arquivo
        +existeSalvo() boolean
        +salvarJogo(Heroi, String)
        +carregarJogo() DadosJogo
    }
    
    class Jogo {
        -Heroi heroi
        -Mapa mapa
        -NoMapa noAtual
        -Scanner scanner
        -boolean emExecucao
        +iniciar() boolean
        +progredir() boolean
    }
    
    class App {
        -JogoSalvo gerenciadorSalvamento
        +main(String[])
        +criarMapa() Mapa
    }
    
    %% Relacionamentos
    Entidade <|-- Heroi
    Entidade <|-- Inimigo
    Inimigo <|-- Cobrinha
    Carta <|-- CartaDano
    Carta <|-- CartaCura
    Carta <|-- CartaEscudo
    Carta <|-- CartaForca
    Carta <|-- CartaVeneno
    Carta <|-- CartaVelocidade
    Carta <|-- CartaLentidao
    Carta <|-- CartaMortalForca
    Carta <|-- CartaMortalVeneno
    Efeito <|-- Forca
    Efeito <|-- Veneno
    Evento <|-- Batalha
    Evento <|-- Loja
    Evento <|-- Fogueira
    Evento <|-- Escolha
    
    Heroi --> Baralho : possui
    Baralho --> Carta : contém
    Heroi --> Carta : usa
    Cobrinha --> Carta : contém (acoes)
    Batalha --> Combate : cria
    Combate --> Heroi : gerencia
    Combate --> Inimigo : gerencia
    Entidade --> Efeito : possui
    NoMapa --> Evento : contém
    Mapa --> NoMapa : contém
    Jogo --> Mapa : possui
    Jogo --> NoMapa : navega
    Jogo --> Heroi : gerencia
    App --> JogoSalvo : utiliza
    App --> Jogo : cria
    JogoSalvo --> DadosJogo : serializa/deserializa
    DadosJogo --> Heroi : armazena
    DadosJogo --> NoMapa : referencia