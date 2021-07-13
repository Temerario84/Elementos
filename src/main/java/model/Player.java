package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pabloestradaleon on 12/12/2020.
 */
public class Player {


    public static final int MODE_MARKOV = 1;
    public static final int MODE_GENERATE_DATA = 2;
    public static final int MODE_LEARNING_POLICY = 3;
    public static final int MODE_HUMAN = 4;
    public static final int LEARNING_ACTION_1 = 1;
    public static final int LEARNING_ACTION_2 = 2;
    public static final int LEARNING_ACTION_3 = 3;
    public static final int LEARNING_ACTION_4 = 4;


    private String name;
    private int order;
    private int mode;
    private int learningAction=0;
    private List<Experience> experiences;
    private boolean victory=false;


    private int firePiecePos;
    private int airPiecePos;
    private int waterPiecePos;
    private int earthPiecePos;

    private List<Card> handCards;



    private boolean canDrawNextTurn = true;

    // boreas: las fichas del enemigo no avanzan durante 2 turnos en el elemento que elijas
    private boolean progressAllowed = true;
    private int turnsProgressBanned = 0;
    private int elementProgressBanned = 0;



    public String toString(){
        String retVal = "";
        retVal+="FirePos:"+firePiecePos+" WaterPos:"+waterPiecePos+" EarthPos:"+earthPiecePos+" AirPos:"+airPiecePos+" CardsInHand:"+handCards.size()+" Mode="+mode;
        return retVal;
    }



    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
        if (this.mode==MODE_GENERATE_DATA){
            double random = Math.random();
            if (random<0.25){
                learningAction = LEARNING_ACTION_1;
            } else if (random>=0.25 && random < 0.5){
                learningAction = LEARNING_ACTION_2;
            }else if (random>=0.5 && random < 0.75){
                learningAction = LEARNING_ACTION_3;
            }else {
                learningAction = LEARNING_ACTION_4;
            }
            System.out.println("Generado player con mode =" +mode+" learning_Action="+learningAction);
            experiences = new ArrayList<>();
        }
    }



    public List<Experience> getExperiences() {
        return experiences;
    }

    public int getLearningAction() {
        return learningAction;
    }

    public boolean isProgressAllowed() {
        return progressAllowed;
    }

    public void setProgressAllowed(boolean progressAllowed) {
        this.progressAllowed = progressAllowed;
    }

    public int getTurnsProgressBanned() {
        return turnsProgressBanned;
    }

    public void setTurnsProgressBanned(int turnsProgressBanned) {
        this.turnsProgressBanned = turnsProgressBanned;
    }

    public int getElementProgressBanned() {
        return elementProgressBanned;
    }

    public void setElementProgressBanned(int elementProgressBanned) {
        this.elementProgressBanned = elementProgressBanned;
    }

    public boolean isCanDrawNextTurn() {
        return canDrawNextTurn;
    }

    public void setCanDrawNextTurn(boolean canDrawNextTurn) {
        this.canDrawNextTurn = canDrawNextTurn;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getFirePiecePos() {
        return firePiecePos;
    }

    public void setFirePiecePos(int firePiecePos) {
        this.firePiecePos = firePiecePos;
    }

    public int getAirPiecePos() {
        return airPiecePos;
    }

    public void setAirPiecePos(int airPiecePos) {
        this.airPiecePos = airPiecePos;
    }

    public int getWaterPiecePos() {
        return waterPiecePos;
    }

    public void setWaterPiecePos(int waterPiecePos) {
        this.waterPiecePos = waterPiecePos;
    }

    public int getEarthPiecePos() {
        return earthPiecePos;
    }

    public void setEarthPiecePos(int earthPiecePos) {
        this.earthPiecePos = earthPiecePos;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }
}
