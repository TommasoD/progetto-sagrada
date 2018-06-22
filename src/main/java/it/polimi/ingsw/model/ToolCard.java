package it.polimi.ingsw.model;

public class ToolCard {

    private int id;
    private String name;
    private String description;
    private boolean alreadyUsed;
    private static final String TOOL_CARD = "Tool Card ";
    private static final String ALREADY_USED = "Already Used: ";
    private static final String EFFECT = "Effect: ";


    /*
        constructors
     */

    public ToolCard(){
        alreadyUsed = false;
    }

    public ToolCard(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        alreadyUsed = false;
    }

    /*
        getters and setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAsAlreadyUsed(){
        alreadyUsed = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return TOOL_CARD + (id + 1) + "\n" + name + "\n" + ALREADY_USED + alreadyUsed + "\n" + EFFECT + description + "\n";
    }

    public void dump(){
        System.out.println(this);
    }

}
