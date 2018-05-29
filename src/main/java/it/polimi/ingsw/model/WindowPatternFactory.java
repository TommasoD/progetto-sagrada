package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import it.polimi.ingsw.model.windows.*;

public class WindowPatternFactory {

    private XMLParserSAX reader;

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
        reader = new XMLParserSAX();
    }

    public WindowPattern createWindow(String name){
        if(name.equals("Kaleidoscopic Dream")) return reader.readWindowPattern("window1");
        if(name.equals("Aurorae Magnificus")) return reader.readWindowPattern("window2");
        if(name.equals("Sun Catcher")) return reader.readWindowPattern("window3");
        if(name.equals("Virtus")) return reader.readWindowPattern("window4");
        if(name.equals("Via Lux")) return reader.readWindowPattern("window5");
        if(name.equals("Bellesguard")) return reader.readWindowPattern("window6");
        if(name.equals("Shadow Thief")) return reader.readWindowPattern("window7");
        if(name.equals("Aurora Sagradis")) return reader.readWindowPattern("window8");
        if(name.equals("Firmitas")) return reader.readWindowPattern("window9");
        if(name.equals("Batllo")) return reader.readWindowPattern("window10");
        if(name.equals("Industria")) return reader.readWindowPattern("window11");
        if(name.equals("Symphony of Light")) return reader.readWindowPattern("window12");
        if(name.equals("Firelight")) return reader.readWindowPattern("window13");
        if(name.equals("Lux Astram")) return reader.readWindowPattern("window14");
        if(name.equals("Gravitas")) return reader.readWindowPattern("window15");
        if(name.equals("Luz Celestial")) return reader.readWindowPattern("window16");
        if(name.equals("Chromatic Splendor")) return reader.readWindowPattern("window17");
        if(name.equals("Fractal Drops")) return reader.readWindowPattern("window18");
        if(name.equals("Ripples of Lights")) return reader.readWindowPattern("window19");
        if(name.equals("Comitas")) return reader.readWindowPattern("window20");
        if(name.equals("Fulgor del Ciel")) return reader.readWindowPattern("window21");
        if(name.equals("Water of Light")) return reader.readWindowPattern("window22");
        if(name.equals("Lux Mundi")) return reader.readWindowPattern("window23");
        if(name.equals("Sun's Glory")) return reader.readWindowPattern("window24");
        return null;
    }

    public WindowPattern getWindow (){
        int bound = list.size();
        if(bound == 0) return null;

        Random rand = new Random();
        int i = rand.nextInt(bound);
        String s = list.get(i);
        list.remove(s);
        return createWindow(s);
    }

    public void dump(){
        System.out.println("Window Factory:");
        for(String s : list){
            System.out.println(s);
        }
    }

}
