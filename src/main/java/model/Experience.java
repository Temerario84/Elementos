package model;

/**
 * Created by pabloestradaleon on 12/06/2021.
 */
public class Experience {

    private int learningAction;
    private int ownPhase;
    private int enemyPhase;
    private double reward;
    private double value;
    private int victory;

    @Override
    public String toString() {
        return "Experience{" +
                "learningAction=" + learningAction +
                ", ownPhase=" + ownPhase +
                ", enemyPhase=" + enemyPhase +
                ", reward=" + reward +
                ", value=" + value +
                ", victory=" + victory +
                '}';
    }

    public int getLearningAction() {
        return learningAction;
    }

    public void setLearningAction(int learningAction) {
        this.learningAction = learningAction;
    }

    public int getOwnPhase() {
        return ownPhase;
    }

    public void setOwnPhase(int ownPhase) {
        this.ownPhase = ownPhase;
    }

    public int getEnemyPhase() {
        return enemyPhase;
    }

    public void setEnemyPhase(int enemyPhase) {
        this.enemyPhase = enemyPhase;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }
}
