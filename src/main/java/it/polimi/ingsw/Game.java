package it.polimi.ingsw;
import  it.polimi.ingsw.publicobjectives.*;
import it.polimi.ingsw.toolcard.ToolCard;

import  java.util.Random;

import java.util.ArrayList;


public class Game {

    //variables
    private int round = 0;
    private ArrayList<Die> roundTrack = new ArrayList<Die>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private PublicObjective[] publicObjectiveActive = new PublicObjective[3];
    private ArrayList<PrivateObjective> privateObjectives = new ArrayList<PrivateObjective>(5);
    private ToolCard[] toolCardList = new ToolCard[12];
    private DiceBag diceBag = new DiceBag();


    public void setPlayers(Player player) {
        players.add(player);
    }

    public Player getPlayers(int i) {
        return players.get(i);
    }

    public void setDieRoundTrack(Die dieLeft) {
        roundTrack.add(dieLeft);
    }

    public Die getDieFromRoundTrack(int index) {
        Die die;
        die = roundTrack.get(index);
        roundTrack.remove(index);
        return die;
    }

    public void setDraft(DraftPool draftPool) {
        for (int i = 0; i < 2 * players.size() + 1; i++) draftPool.setDieInDraftPool(diceBag.getDie());
    }

    public void increaseRound() {
        round++;
    }

    public void inizialize() {
        //random assignment of private objectives
        privateObjectives.add(new PrivateObjective("RED"));
        privateObjectives.add(new PrivateObjective("YELLOW"));
        privateObjectives.add(new PrivateObjective("PURPLE"));
        privateObjectives.add(new PrivateObjective("GREEN"));
        privateObjectives.add(new PrivateObjective("BLUE"));
        Random random = new Random();
        int bound;
        int k;
        for (int i = 0; i < players.size(); i++) {
            bound = privateObjectives.size();
            k = random.nextInt(bound);
            players.get(i).setPlayerObjective(privateObjectives.get(k));
            privateObjectives.remove(k);
        }

        //randomly creates 3 public objectives
        PublicObjectiveFactory of = new PublicObjectiveFactory();
        for (int i = 0; i < 3; i++) {
            publicObjectiveActive[i] = of.getRandomObjective();
        }

        /*
        toolcard missing
        wait for the creation of tool cards
         */
    }

    //missing a method that assigns WindowPattern to player

    public void useDie(Player player, int x, int y, Die die) {

        if (player.getPlayerWindow().isValid(x, y, die)) {
            player.getPlayerWindow().getWindowMatrix(x, y).setDie(die);
            System.out.println("Dado posizionato correttamente");
        } else {
            System.out.println("Posizione non valida");
        }
    }

    //toolcard.effect() not implemented yet
    public boolean useToolCard(ToolCard toolcard, Player player) {

        int cost = 1;
        if (toolcard.isAlreadyUsed()) cost = 2;

        if (player.getPlayerWindow().getDifficultyToken() < cost) {
            System.out.println("Token non sufficienti");
            return false;
        }

        player.getPlayerWindow().decreaseDifficultyToken(cost);
        toolcard.setAsAlreadyUsed();
        //toolcard.effect(); //not implemented yet
        return true;
    }


    //not finished
    //we must consider the management of the rounds to determine the winner
    /*public Player determineWinner() {
        int totalPoints[] = new int[4];
        int privateObjectivePoints[] = new int[4];


        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 3; j++) {
                totalPoints[i] += publicObjectiveActive[j].checkPoints(players.get(i).getPlayerWindow());
            }
        }

        for (int i = 0; i < players.size(); i++) {
            privateObjectivePoints[i] = players.get(i).getPrivatePoints();
            totalPoints[i] = players.get(i).getTotalPoints();
        }

        int max = totalPoints[0];
        int maxIndex[] = new int[4];
        int count = 0;
        for (int i = 1; i < players.size(); i++) {
            if (totalPoints[i] > max) max = totalPoints[i];
        }

        for (int i = 0; i < players.size(); i++) {
            if (totalPoints[i] == max) maxIndex[i] = 1;
            count++;
        }

        if (count == 1) {
            //un solo vincitore
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 1) return players.get(i);
            }
        }

        return players.get(0);

    }*/


}