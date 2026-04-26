package lab6;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import lab6.Entidades.Baralho;

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
