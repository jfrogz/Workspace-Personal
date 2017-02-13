package concursantes;

import org.springframework.stereotype.Component;

@Component("poema")
public class Soneto implements Poema {
    public void recitar() {
        String sonetoPasionSorJuana = "Hombres necios que acusáis\n" +
                "a la mujer sin razón,\n" +
                "sin ver que sois la ocasión\n" +
                "de lo mismo que culpáis.\n" +
                "\n" +
                "Si con ansia sin igual\n" +
                "solicitáis su desdén,\n" +
                "¿por qué queréis que obren bien\n" +
                "si las incitáis al mal?\n";

        System.out.println("Soneto: " + sonetoPasionSorJuana);
    }
}
