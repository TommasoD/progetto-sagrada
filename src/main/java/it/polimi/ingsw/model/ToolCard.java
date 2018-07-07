package it.polimi.ingsw.model;

/**
 * Represents a tool card. Tool cards allow players to break the rules and do some
 * special actions like moving a die inside their window patter.
 * Each tool card has a cost of one token. Once a tool card has been used,
 * its cost increases by one to a total of two.
 */
public class ToolCard {

    private int id;
    private String name;
    private String description;
    private boolean alreadyUsed;
    private static final String TOOL_CARD = "Tool Card ";
    private static final String ALREADY_USED = "Already Used: ";
    private static final String EFFECT = "Effect: ";

    /**
     * Class constructor.
     */

    public ToolCard(){
        alreadyUsed = false;
    }

    /**
     * Class constructor specifying the identification number of the tool card,
     * its full name and the description of its effect.
     * @param id the identification number.
     * @param name the name of the card.
     * @param description the description of the card effect.
     */

    public ToolCard(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        alreadyUsed = false;
    }

    /**
     *
     * @return the identification number of the card.
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @param id the identification number of the card.
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the name of the card.
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name of the card.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return whether the tool card has already been used.
     */

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }

    /**
     * Sets the tool card as already used by someone.
     */

    public void setAsAlreadyUsed(){
        alreadyUsed = true;
    }

    /**
     *
     * @return the description of the card effect.
     */

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description of the card effect.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the String representation of the class.
     * @return the String representation of the class.
     */

    public String toString(){
        return TOOL_CARD + (id + 1) + "\n" + name + "\n" + ALREADY_USED + alreadyUsed + "\n" + EFFECT + description + "\n";
    }

    /**
     * Prints the class.
     */

    public void dump(){
        System.out.println(this);
    }

}
