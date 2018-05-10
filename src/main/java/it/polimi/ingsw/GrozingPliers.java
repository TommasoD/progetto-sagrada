package it.polimi.ingsw;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class GrozingPliers extends ToolCard {



    public void effect(ToolCardParameters p) {
        try {
            if(p.getMove().equals("increase")) {
                Die die = p.getDieFromDraft();
                if(die.getValue().equals("6")) throw new InvalidValue();
                p.getDieFromDraft().setValue(die.getValueAsInt() + 1);
            }
            if(p.getMove().equals("decrease")) {
                Die die = p.getDieFromDraft();
                if(die.getValue().equals("1")) throw new InvalidValue();
                p.getDieFromDraft().setValue(die.getValueAsInt() - 1);
            }
            if(!p.getMove().equals("increase") && !p.getMove().equals("decrease")) throw new InvalidValue();
        }
        catch(InvalidValue e) {
            System.out.println(e + ": mossa non valida");
        }
    }

}
