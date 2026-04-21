package lab5;


import lab5.Entidades.Baralho;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BaralhoTest {
    
        @Test
        void testBaralhoECompra() {
            Baralho baralho = new Baralho();
            assertEquals(0, baralho.getnCartasNaMao());
            
            baralho.comprarCartas();
            assertEquals(5, baralho.getnCartasNaMao());
            
            baralho.cartaUsada(1);
            assertEquals(4, baralho.getnCartasNaMao());
        }
}
