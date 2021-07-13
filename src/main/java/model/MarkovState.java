package model;

import java.util.List;

public class MarkovState {

    // own player state
    private int firePiecePos;
    private int airPiecePos;
    private int waterPiecePos;
    private int earthPiecePos;
    private List<Card> handCards; // DUDO DE SI ESTO DEBERIA ESTAR AQUI O NO
    private int handCardsCount;
    private boolean canDrawNextTurn;



    // enemy state
    private int enemyFirePiecePos;
    private int enemyAirPiecePos;
    private int enemyWaterPiecePos;
    private int enemyEarthPiecePos;
    private int enemyHandCardsCount;
    private boolean enemyCanDrawnNextTurn;


    public int calcOwnState(){
        int points = 0;
        points+= firePiecePos+airPiecePos+waterPiecePos+earthPiecePos;
        points+= handCardsCount;
        if (firePiecePos==12){
            points +=1;
        }
        if (airPiecePos==12){
            points +=1;
        }
        if (waterPiecePos==12){
            points +=1;
        }
        if (earthPiecePos==12){
            points +=1;
        }
        /*if (firePiecePos==12&&airPiecePos==12&&waterPiecePos==12&&earthPiecePos==12){
            points +=2;
        }*/
        return points;
    }
    public int calcEnemyState(){
        int points = 0;
        points+= enemyFirePiecePos+enemyWaterPiecePos+enemyEarthPiecePos+enemyAirPiecePos;
        points+= enemyHandCardsCount;
        if (enemyFirePiecePos==12){
            points +=1;
        }
        if (enemyWaterPiecePos==12){
            points +=1;
        }
        if (enemyEarthPiecePos==12){
            points +=1;
        }
        if (enemyAirPiecePos==12){
            points +=1;
        }
        /*if (enemyFirePiecePos==12&&enemyWaterPiecePos==12&&enemyEarthPiecePos==12&&enemyAirPiecePos==12){
            points +=2;
        }*/
        return points;
    }
    public boolean isFinalStage(){
        return (firePiecePos==12&&waterPiecePos==12&&earthPiecePos==12&&airPiecePos==12);
    }


    public int calcState(){
        return calcOwnState()-calcEnemyState();
    }

    public String toString(){
        String retVal = "";
        retVal+="F:"+firePiecePos+" W:"+waterPiecePos+" E:"+earthPiecePos+" A:"+airPiecePos+" CARDS:"+handCards.size();
        return retVal;
    }




    public boolean isEnemyCanDrawnNextTurn() {
        return enemyCanDrawnNextTurn;
    }

    public void setEnemyCanDrawnNextTurn(boolean enemyCanDrawnNextTurn) {
        this.enemyCanDrawnNextTurn = enemyCanDrawnNextTurn;
    }

    public int getHandCardsCount() {
        return handCardsCount;
    }

    public void setHandCardsCount(int handCardsCount) {
        this.handCardsCount = handCardsCount;
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

    public boolean isCanDrawNextTurn() {
        return canDrawNextTurn;
    }

    public void setCanDrawNextTurn(boolean canDrawNextTurn) {
        this.canDrawNextTurn = canDrawNextTurn;
    }

    public int getEnemyFirePiecePos() {
        return enemyFirePiecePos;
    }

    public void setEnemyFirePiecePos(int enemyFirePiecePos) {
        this.enemyFirePiecePos = enemyFirePiecePos;
    }

    public int getEnemyAirPiecePos() {
        return enemyAirPiecePos;
    }

    public void setEnemyAirPiecePos(int enemyAirPiecePos) {
        this.enemyAirPiecePos = enemyAirPiecePos;
    }

    public int getEnemyWaterPiecePos() {
        return enemyWaterPiecePos;
    }

    public void setEnemyWaterPiecePos(int enemyWaterPiecePos) {
        this.enemyWaterPiecePos = enemyWaterPiecePos;
    }

    public int getEnemyEarthPiecePos() {
        return enemyEarthPiecePos;
    }

    public void setEnemyEarthPiecePos(int enemyEarthPiecePos) {
        this.enemyEarthPiecePos = enemyEarthPiecePos;
    }

    public int getEnemyHandCardsCount() {
        return enemyHandCardsCount;
    }

    public void setEnemyHandCardsCount(int enemyHandCardsCount) {
        this.enemyHandCardsCount = enemyHandCardsCount;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

}
