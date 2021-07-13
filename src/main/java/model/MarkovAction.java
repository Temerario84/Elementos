package model;

public class MarkovAction {

    public static final int ACTION_PLAY_CARD = 1;
    public static final int ACTION_DO_NOTHING = 2;
    public static final int ACTION_DISCARD_3_DRAW_1 = 3;
    public static final int ACTION_NATURAL_DISASTER = 4;

    private int actionType;
    private Card card1;
    private Card card2;
    private Card card3;
    private int element=-1;
    private double reward = 0;
    private double value = 0;

    public MarkovAction(int actionType) {
        this.actionType = actionType;
    }
    public MarkovAction(int actionType, Card card1) {
        this.actionType = actionType;
        this.card1 = card1;
    }
    public MarkovAction(int actionType, Card card1, Card card2) {
        this.actionType = actionType;
        this.card1 = card1;
        this.card2 = card2;
    }
    public MarkovAction(int actionType, Card card1, Card card2, Card card3) {
        this.actionType = actionType;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }

    public String toString(){
        String cadena = " ACTION[typ=";
        cadena+=actionType+",";
        if (card1!=null) {
            cadena += "car1="+card1.getName()+",";
        }
        if (card2!=null) {
            cadena += "car2="+card2.getName()+",";
        }
        if (card3!=null) {
            cadena += "car3="+card3.getName()+",";
        }
        cadena+="elem="+element+",";
        cadena+=reward+",";
        cadena+=value+"]";
                return cadena;
    }








    public Card getCard3() {
        return card3;
    }

    public void setCard3(Card card3) {
        this.card3 = card3;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }
}
