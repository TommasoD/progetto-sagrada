package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOverTest {

    @Test
    void determineWinner() {
        Game model = new Game();
        int[] turnOrder = new int[4];
        turnOrder[0] = 1;
        turnOrder[1] = 0;
        turnOrder[2] = 2;
        WindowPatternFactory factory = new WindowPatternFactory();
        model.addPlayer(new Player("a", factory.createWindow("Virtus"), 0));
        model.addPlayer(new Player("b", factory.createWindow("Aurorae Magnificus"), 1));
        model.initialize();
        Die d1 = new Die("GREEN");
        d1.setValue(2);
        Die d2 = new Die("RED");
        d2.setValue(3);

        model.getPlayers(0).setPlayerObjective(new PrivateObjective("GREEN"));
        model.getPlayers(0).getPlayerWindow().getWindowMatrix(4, 0).setDie(d1);

        GameOver go = new GameOver();

        assertEquals("a", go.determineWinner(model.getPlayers(), model.getPublicObjectiveActive(), turnOrder));

        model.getPlayers(1).setPlayerObjective(new PrivateObjective("RED"));
        model.getPlayers(1).getPlayerWindow().getWindowMatrix(0, 2).setDie(d2);
        model.getPlayers(1).getPlayerWindow().decreaseDifficultyToken(1);

        assertEquals("b", go.determineWinner(model.getPlayers(), model.getPublicObjectiveActive(), turnOrder));

        model.getPlayers(0).getPlayerWindow().getWindowMatrix(4, 0).removeDie();
        d1.setValue(3);
        model.getPlayers(0).getPlayerWindow().getWindowMatrix(4, 0).setDie(d1);
        model.getPlayers(0).getPlayerWindow().decreaseDifficultyToken(1);

        assertEquals("b", go.determineWinner(model.getPlayers(), model.getPublicObjectiveActive(), turnOrder));

        Die d3 = new Die("PURPLE");
        d3.setValue(3);
        model.getPlayers(1).getPlayerWindow().getWindowMatrix(4, 3).setDie(d3);
        model.getPlayers(1).getPlayerWindow().decreaseDifficultyToken(1);

        assertEquals("a", go.determineWinner(model.getPlayers(), model.getPublicObjectiveActive(), turnOrder));

    }
}