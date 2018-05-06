package it.polimi.ingsw;
import it.polimi.ingsw.windows.*;

public class App
{
    private static final String[] windows = {
            "Kaleidoscopic Dream",
            "Aurorae Magnificus",
            "Sun Catcher",
            "Virtus",
            "Via Lux",
            "Bellesguard",
            "Shadow Thief",
            "Aurora Sagradis",
            "Firmitas",
            "Batllo",
            "Industria",
            "Symphony of Light",
            "Firelight",
            "Lux Astram",
            "Gravitas",
            "Luz Celestial",
            "Chromatic Splendor",
            "Fractal Drops",
            "Ripples of Lights",
            "Comitas",
            "Fulgor del Ciel",
            "Water of Light",
            "Lux Mundi",
            "Sun's Glory"
    };

    public static void main( String[] args )
    {
        WindowPattern w;
        WindowPatternFactory wf = new WindowPatternFactory();
        for(String s : windows){
            w = wf.createWindow(s);
            w.dump();
            System.out.println("\n");
        }
    }
}
