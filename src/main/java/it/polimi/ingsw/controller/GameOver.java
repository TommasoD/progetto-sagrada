package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.objectives.publicobjectives.PublicObjective;

import java.util.List;

/**
 * Class used to determine the winner of the match.
 */
public class GameOver {


    /**
     *
     * @param players list of the players.
     * @param publicObjectiveActive list of the active public Objective.
     * @param lastTurnPosition vector that contains the order of the players during the last round.
     * @return the winner of the match.
     */

    public String determineWinner(List<Player> players, List<PublicObjective> publicObjectiveActive, int[] lastTurnPosition) {
        int[] totalPoints = new int[4];
        int[] privateObjectivePoints = new int[4];
        int[] difficultyToken = new int[4];

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 3; j++) {
                totalPoints[i] += publicObjectiveActive.get(j).checkPoints(players.get(i).getPlayerWindow());
            }
        }

        for (int i = 0; i < players.size(); i++) {
            privateObjectivePoints[i] = players.get(i).getPrivatePoints();
            difficultyToken[i] = players.get(i).getPlayerWindow().getDifficultyToken();
            totalPoints[i] += players.get(i).getTotalPoints();
        }

        int max = totalPoints[0];
        int[] maxIndex = new int[4];
        int count = 0;
        for (int i = 0; i < players.size(); i++) {
            if (totalPoints[i] > max) max = totalPoints[i];
        }

        for (int i = 0; i < players.size(); i++) {
            if (totalPoints[i] == max) {
                maxIndex[i] = 1;
                count++;
            }
        }

        if (count == 1) {
            //Just a winner.
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 1) return players.get(i).getUsername();
            }
        }

        //Private Objectives calculation (in case of Total Point draw).
        for(int i = 0; i < players.size(); i++) {
            if(maxIndex[i] == 1) {
                max = privateObjectivePoints[i];
                break;
            }
        }

        count = 0;

        for(int i = 0; i < players.size(); i++) {
            if(privateObjectivePoints[i] > max && maxIndex[i] == 1) max = privateObjectivePoints[i];
        }

        for(int i = 0; i < players.size(); i++) {
            if(privateObjectivePoints[i] == max && maxIndex[i] == 1) {
                maxIndex[i] = 2;
                count++;
            }
        }

        if (count == 1) {
            //just a winner.
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 2) return players.get(i).getUsername();
            }
        }

        //Tokens calculation (in case of Private Objective point draw).
        for(int i = 0; i < players.size(); i++) {
            if(maxIndex[i] == 2) {
                max = difficultyToken[i];
                break;
            }
        }

        count = 0;

        for(int i = 0; i < players.size(); i++) {
            if(difficultyToken[i] > max && maxIndex[i] == 2) max = difficultyToken[i];
        }

        for(int i = 0; i < players.size(); i++) {
            if(difficultyToken[i] == max && maxIndex[i] == 2) {
                maxIndex[i] = 3;
                count++;
            }
        }

        if (count == 1) {
            //Just a winner.
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 3) return players.get(i).getUsername();
            }
        }

        for(int i = 0; i < players.size(); i++) {
            //If there's also a Token point draw.
            if(maxIndex[i] == 3) return players.get(lastTurnPosition[i]).getUsername();
        }

        return players.get(0).getUsername();

    }

}
