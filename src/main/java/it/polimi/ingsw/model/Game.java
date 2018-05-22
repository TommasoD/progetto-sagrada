package it.polimi.ingsw.model;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.objectives.PublicObjectiveFactory;
import it.polimi.ingsw.model.objectives.publicobjectives.*;
import it.polimi.ingsw.model.toolcard.ToolCard;
import  java.util.Random;
import java.util.ArrayList;

public class Game {

    //variables
    private int round;
    private DiceBag diceBag;
    private DraftPool draftPool;
    private ArrayList<Player> players;
    private ArrayList<Die> roundTrack;
    private ArrayList<PrivateObjective> privateObjectives;
    private PublicObjective[] publicObjectiveActive;
    private ToolCard[] toolCardList;

    public Game(){
        this.round = 0;
        this.diceBag = new DiceBag();
        this.draftPool = new DraftPool();
        this.players = new ArrayList<Player>();
        this.roundTrack = new ArrayList<Die>();
        this.publicObjectiveActive = new PublicObjective[3];
        this.privateObjectives = new ArrayList<PrivateObjective>();
        this.toolCardList = new ToolCard[12];
    }

    public int getRound() {
        return round;
    }

    public void increaseRound() {
        round++;
    }

    /*
        toolCardList methods
     */

    public ToolCard getToolCard(int index) {
        return toolCardList[index];
    }

    /*
        players methods
     */

    public void setPlayers(Player player) {
        players.add(player);
    }

    public Player getPlayers(int index) {
        return players.get(index);
    }

    /*
        roundTrack methods
     */

    public void setDieRoundTrack(Die dieLeft) {
        roundTrack.add(dieLeft);
    }

    public Die getDieFromRoundTrack(int index) {
        Die die;
        die = roundTrack.get(index);
        roundTrack.remove(index);
        return die;
    }

    /*
        draftPool methods
     */

    public Die getDieFromDraft(int index){
        return draftPool.getDieFromDraft(index);
    }

    public void setDraft() {
        for (int i = 0; i < 2 * players.size() + 1; i++) {
            draftPool.setDieInDraftPool(diceBag.getDie());
        }
    }

    /*
        inizializza carte obiettivo privato e le distribuisce, carte obiettivo pubblico e carte strumento
     */

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

    }

    //missing a method that assigns WindowPattern to player

}