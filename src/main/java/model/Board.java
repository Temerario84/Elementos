package model;

import java.util.List;

/**
 * Created by pabloestradaleon on 12/12/2020.
 */


public class Board {



    private List<Card> boardCards;

    public List<Card> getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(List<Card> boardCards) {
        this.boardCards = boardCards;
    }
}
