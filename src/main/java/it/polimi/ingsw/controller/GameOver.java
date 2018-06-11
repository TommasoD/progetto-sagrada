package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.objectives.publicobjectives.PublicObjective;

import java.util.List;

public class GameOver {


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
            //un solo vincitore
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 1) return players.get(i).getUsername();
            }
        }

        //calcolo obiettivi privati
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
            //un solo vincitore
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 2) return players.get(i).getUsername();
            }
        }

        //calcolo token
        for(int i = 0; i < players.size(); i++) {
            if(maxIndex[i] == 2) {
                max = privateObjectivePoints[i];
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
            //un solo vincitore
            for (int i = 0; i < players.size(); i++) {
                if (maxIndex[i] == 3) return players.get(i).getUsername();
            }
        }

        for(int i = 0; i < players.size(); i++) {
            if(maxIndex[i] == 3) return players.get(lastTurnPosition[i]).getUsername();
        }

        return players.get(0).getUsername();

    }

}
