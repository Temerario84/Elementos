package model;

import java.util.List;

/**
 * Created by pabloestradaleon on 12/12/2020.
 */
public class Game {


    private int turn;
    private int age;
    private Player activePlayer;

    private List<Player> players;
    private Board board;


    public Player getEnemy(){
        for (Player player: players){
            if (!player.equals(activePlayer)){
                return player;
            }
        }
        return null;
    }


    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }
}
