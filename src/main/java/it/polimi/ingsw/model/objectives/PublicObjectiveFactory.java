package it.polimi.ingsw.model.objectives;

import it.polimi.ingsw.model.objectives.publicobjectives.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PublicObjectiveFactory {
    private ArrayList <String> list;
    private static final String[] windows = {
            "BrightS",
            "MediumS",
            "DarkS",
            "DiffC",
            "DiffCC",
            "DiffCR",
            "DiffS",
            "DiffSC",
            "DiffSR",
            "ColDiag"
    };

    public PublicObjectiveFactory(){
        list = new ArrayList<String>();
        list.addAll(Arrays.asList(windows));
    }

    public PublicObjective createObjective(String name){
        if(name.equals("BrightS")) return new BrightShade();
        if(name.equals("MediumS")) return new MediumShade();
        if(name.equals("DarkS")) return new DarkShade();
        if(name.equals("DiffC")) return new DiffColor();
        if(name.equals("DiffCC")) return new DiffColorColumn();
        if(name.equals("DiffCR")) return new DiffColorRow();
        if(name.equals("DiffS")) return new DiffShade();
        if(name.equals("DiffSC")) return new DiffShadeColumn();
        if(name.equals("DiffSR")) return new DiffShadeRow();
        if(name.equals("ColDiag")) return new ColoredDiagonal();
        return null;
    }

    public PublicObjective getRandomObjective(){
        int bound = list.size();
        if(bound == 0) return null;

        Random rand = new Random();
        int i = rand.nextInt(bound);
        String s = list.get(i);
        list.remove(s);
        return createObjective(s);
    }
    
}
