package it.polimi.ingsw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import it.polimi.ingsw.windows.*;

public class WindowPatternFactory {

    private ArrayList<String> list;
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

    public WindowPatternFactory(){
        list = new ArrayList<String>();
        Collections.addAll(list, windows);
    }

    public WindowPattern createWindow(String name){
        if(name.equals("Kaleidoscopic Dream")) return new Window1();
        if(name.equals("Aurorae Magnificus")) return new Window2();
        if(name.equals("Sun Catcher")) return new Window3();
        if(name.equals("Virtus")) return new Window4();
        if(name.equals("Via Lux")) return new Window5();
        if(name.equals("Bellesguard")) return new Window6();
        if(name.equals("Shadow Thief")) return new Window7();
        if(name.equals("Aurora Sagradis")) return new Window8();
        if(name.equals("Firmitas")) return new Window9();
        if(name.equals("Batllo")) return new Window10();
        if(name.equals("Industria")) return new Window11();
        if(name.equals("Symphony of Light")) return new Window12();
        if(name.equals("Firelight")) return new Window13();
        if(name.equals("Lux Astram")) return new Window14();
        if(name.equals("Gravitas")) return new Window15();
        if(name.equals("Luz Celestial")) return new Window16();
        if(name.equals("Chromatic Splendor")) return new Window17();
        if(name.equals("Fractal Drops")) return new Window18();
        if(name.equals("Ripples of Light")) return new Window19();
        if(name.equals("Comitas")) return new Window20();
        if(name.equals("Fulgor del Ciel")) return new Window21();
        if(name.equals("Water of Light")) return new Window22();
        if(name.equals("Lux Mundi")) return new Window23();
        if(name.equals("Sun's Glory")) return new Window24();
        return null;
    }

    public String getWindow (){
        int bound = list.size();
        if(bound == 0) return null;

        Random rand = new Random();
        int i = rand.nextInt(bound);
        String s = list.get(i);
        list.remove(s);
        return s;
    }

    public void dump(){
        System.out.println("Window Factory:");
        for(String s : list){
            System.out.println(s);
        }
    }

}
