package it.polimi.ingsw;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class CopperFoilBurnisher extends ToolCard {

    public void effect(ToolCardParameters parameters) {
        try {
                if (parameters.getInputSlot().isNotEmpty()) {   //////is valid è troppo restrittivo
                    if (parameters.getOutputSlot().isValid(parameters.getInputSlot().getDie())) {
                        parameters.getOutputSlot().setDie(parameters.getInputSlot().getDie());
                        parameters.getInputSlot().setDie(null);
                    }
                    else throw new InvalidValue();
                }
                else throw new InvalidValue();
            }
        catch(InvalidValue e) {
            System.out.println(e + ": mossa non valida");
        }
    }

}
