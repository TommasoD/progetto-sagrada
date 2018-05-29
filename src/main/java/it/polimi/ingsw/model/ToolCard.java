package it.polimi.ingsw.model;

public class ToolCard {

    private String name;
    private String description;
    private boolean alreadyUsed;

    /*
        constructors
     */

    public ToolCard(){
        alreadyUsed = false;
    }

    public ToolCard(String name, String description, boolean alreadyUsed){
        this.name = name;
        this.description = description;
        this.alreadyUsed = alreadyUsed;
    }

    /*
        getters and setters
     */

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
}
