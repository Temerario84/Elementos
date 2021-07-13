package business;

import model.*;
import model.enums.CardEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ElementosEngine {

    public static final int MODE_MARKOV_VS_MARKOV = 1;
    public static final int MODE_GENERATE_DATA = 2;
    public static final int MODE_LEARNING_VS_LEARNING = 3;
    public static final int MODE_MARKOV_VS_LEARNING = 4;
    public static final int MODE_HUMAN_VS_MARKOV = 4;


    // Inicializa una partida
    public static Game startGame(int mode) {
        // Cards
        List<Card> boardCards = generateBoardCards();
        shuffle(boardCards);
        // Board
        Board board = new Board();
        board.setBoardCards(boardCards);
        // Game
        Game game = new Game();
        game.setActivePlayer(null);
        game.setAge(0);
        game.setTurn(0);
        game.setBoard(board);
        game.setPlayers(new ArrayList());

        // create players
        if (mode==MODE_MARKOV_VS_MARKOV){
            Player player1 = generatePlayer(1, Player.MODE_MARKOV);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player1);
            }
            game.getPlayers().add(player1);

            Player player2 = generatePlayer(2, Player.MODE_MARKOV);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player2);
            }
            game.getPlayers().add(player2);
        } else if (mode==MODE_GENERATE_DATA){  // Generar datos será uno con markov, sin tergiversar y otro con politicas especiales
            Player player1 = generatePlayer(1, Player.MODE_GENERATE_DATA);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player1);
            }
            game.getPlayers().add(player1);

            Player player2 = generatePlayer(2, Player.MODE_MARKOV);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player2);
            }
            game.getPlayers().add(player2);
        } else if (mode==MODE_LEARNING_VS_LEARNING){
            Player player1 = generatePlayer(1, Player.MODE_LEARNING_POLICY);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player1);
            }
            game.getPlayers().add(player1);

            Player player2 = generatePlayer(2, Player.MODE_LEARNING_POLICY);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player2);
            }
            game.getPlayers().add(player2);
        } else if (mode==MODE_MARKOV_VS_LEARNING){
            Player player1 = generatePlayer(1, Player.MODE_MARKOV);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player1);
            }
            game.getPlayers().add(player1);

            Player player2 = generatePlayer(2, Player.MODE_LEARNING_POLICY);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player2);
            }
            game.getPlayers().add(player2);
        } else if (mode==MODE_HUMAN_VS_MARKOV){
            Player player1 = generatePlayer(1, Player.MODE_HUMAN);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player1);
            }
            game.getPlayers().add(player1);

            Player player2 = generatePlayer(2, Player.MODE_MARKOV);
            // repartirle cartas
            for (int j = 0; j < 5; j++) {
                playerDrawCard(board, player2);
            }
            game.getPlayers().add(player2);
        }

        // initGame
        game.setAge(1);
        game.setTurn(1);
        game.setActivePlayer(getPlayerByTurn(game,1));

        return game;

    }


    private static Player generatePlayer(int turn, int mode){
        Player player = new Player();
        player.setName("Player_" + turn+"_"+mode);
        player.setFirePiecePos(0);
        player.setWaterPiecePos(0);
        player.setEarthPiecePos(0);
        player.setAirPiecePos(0);
        player.setOrder(turn);
        player.setMode(mode);
        player.setHandCards(new ArrayList());
        player.setCanDrawNextTurn(true);
        player.setElementProgressBanned(-1);
        player.setProgressAllowed(true);
        player.setTurnsProgressBanned(0);
        return player;

    }



    // Generar baraja
    private static List<Card> generateBoardCards() {
        List<Card> boardCards = new ArrayList();

        // añadimos primero una carta de cada una de las definidas
        for (CardEnum cardEnum : CardEnum.values()) {
            boardCards.add(new Card(cardEnum));
        }

        // ahora añadimos las cartas que tienen mas de una instancia a mano
        // elementales x2
        boardCards.add(new Card(CardEnum.ELEM_FIRE));
        boardCards.add(new Card(CardEnum.ELEM_WATER));
        boardCards.add(new Card(CardEnum.ELEM_EARTH));
        boardCards.add(new Card(CardEnum.ELEM_AIR));
        // dioses accion x2 (y elpis x3)
        boardCards.add(new Card(CardEnum.AG_ADICIA));
        boardCards.add(new Card(CardEnum.AG_APATE));
        boardCards.add(new Card(CardEnum.AG_ARES));
        boardCards.add(new Card(CardEnum.AG_ARTEMISA));
        boardCards.add(new Card(CardEnum.AG_AUTOLICO));
        boardCards.add(new Card(CardEnum.AG_BOREAS));
        boardCards.add(new Card(CardEnum.AG_HADES));
        //boardCards.add(new Card(CardEnum.AG_HARMONIA));
        //boardCards.add(new Card(CardEnum.AG_HECATE));
        boardCards.add(new Card(CardEnum.AG_HERMES));
        boardCards.add(new Card(CardEnum.AG_TIQUE));
        boardCards.add(new Card(CardEnum.AG_ELPIS)); //x2
        boardCards.add(new Card(CardEnum.AG_ELPIS)); //x3

        // avance normal (x2)
        boardCards.add(new Card(CardEnum.AV_FIRE1));
        boardCards.add(new Card(CardEnum.AV_FIRE2));
        boardCards.add(new Card(CardEnum.AV_FIRE3));
        boardCards.add(new Card(CardEnum.AV_FIRE4));
        boardCards.add(new Card(CardEnum.AV_FIRE5));
        boardCards.add(new Card(CardEnum.AV_WATER1));
        boardCards.add(new Card(CardEnum.AV_WATER2));
        boardCards.add(new Card(CardEnum.AV_WATER3));
        boardCards.add(new Card(CardEnum.AV_WATER4));
        boardCards.add(new Card(CardEnum.AV_WATER5));
        boardCards.add(new Card(CardEnum.AV_EARTH1));
        boardCards.add(new Card(CardEnum.AV_EARTH2));
        boardCards.add(new Card(CardEnum.AV_EARTH3));
        boardCards.add(new Card(CardEnum.AV_EARTH4));
        boardCards.add(new Card(CardEnum.AV_EARTH5));
        boardCards.add(new Card(CardEnum.AV_AIR1));
        boardCards.add(new Card(CardEnum.AV_AIR2));
        boardCards.add(new Card(CardEnum.AV_AIR3));
        boardCards.add(new Card(CardEnum.AV_AIR4));
        boardCards.add(new Card(CardEnum.AV_AIR5));
        return boardCards;
    }




    // verificacion condiciones victoria
    // 1- tener todas las fichas en el ether
    // 2- tener la carta del eter o un representante de cada elemento
    public static boolean canWin(Player player) {
        if (hasAllInEther(player)) {

            // si tiene la carta de ether, ya gana
            if (playerHasCard(player, CardEnum.ETHER)) {
                return true;
            }

            // hacemos copia de las cartas de mano
            List<Card> combinationList = new ArrayList<>();
            combinationList.addAll(player.getHandCards());

            Card fireRepresentant = getElementRepresentation(combinationList, Card.SUB_FIRE);
            if (fireRepresentant!=null){
                combinationList.remove(fireRepresentant);
            } else {
                return false;
            }
            Card waterRepresentant = getElementRepresentation(combinationList, Card.SUB_WATER);
            if (waterRepresentant!=null){
                combinationList.remove(waterRepresentant);
            } else {
                return false;
            }
            Card earthRepresentant = getElementRepresentation(combinationList, Card.SUB_EARTH);
            if (earthRepresentant!=null){
                combinationList.remove(earthRepresentant);
            } else {
                return false;
            }
            Card airRepresentant = getElementRepresentation(combinationList, Card.SUB_AIR);
            if (airRepresentant!=null){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }



    // tras decidir, un jugador lleva a cabo una accion
    public static void playCard(Game game, Player player, MarkovAction action) {
        if (action.getActionType()==MarkovAction.ACTION_DO_NOTHING){
            return;
        }
        Card card = action.getCard1();


        // avance, avance eter y avance especial
        if (card.getType() == Card.TYPE_FORWARD || card.getType() == Card.TYPE_SPECIAL_FORWARD) {
            if (card.getSubtype() == Card.SUB_FIRE) {
                progressElement(game, player, Card.SUB_FIRE, card.getValue());
            } else if (card.getSubtype() == Card.SUB_WATER) {
                progressElement(game, player, Card.SUB_WATER, card.getValue());
            } else if (card.getSubtype() == Card.SUB_EARTH) {
                progressElement(game, player, Card.SUB_EARTH, card.getValue());
            } else if (card.getSubtype() == Card.SUB_AIR) {
                progressElement(game, player, Card.SUB_AIR, card.getValue());
            } else if (card.getSubtype() == Card.SUB_ETHER) {
                if (action.getElement() == Card.SUB_FIRE) {
                    progressElement(game, player, Card.SUB_FIRE, card.getValue());
                } else if (action.getElement() == Card.SUB_WATER) {
                    progressElement(game, player, Card.SUB_WATER, card.getValue());
                } else if (action.getElement() == Card.SUB_EARTH) {
                    progressElement(game, player, Card.SUB_EARTH, card.getValue());
                } else if (action.getElement() == Card.SUB_AIR) {
                    progressElement(game, player, Card.SUB_AIR, card.getValue());
                }
            }



        // avance por elemental, avance 6
        // si además tiene el dios, avanza 12 en lugar de 6
        } else if (card.getType() == Card.TYPE_ELEMENTAL) {
            if (card.getSubtype() == Card.SUB_FIRE ||
                    (card.getSubtype() == Card.SUB_ETHER
                            && action.getElement() == Card.SUB_FIRE)) {
                if (playerHasGod(player, Card.SUB_FIRE)) {
                    progressElement(game, player, Card.SUB_FIRE, 12);
                } else {
                    progressElement(game, player, Card.SUB_FIRE, 6);
                }
            } else if (card.getSubtype() == Card.SUB_WATER ||
                    (card.getSubtype() == Card.SUB_ETHER
                            && action.getElement() == Card.SUB_WATER)) {
                if (playerHasGod(player, Card.SUB_WATER)) {
                    progressElement(game, player, Card.SUB_WATER, 12);
                } else {
                    progressElement(game, player, Card.SUB_WATER, 6);
                }
            } else if (card.getSubtype() == Card.SUB_EARTH ||
                    (card.getSubtype() == Card.SUB_ETHER
                            && action.getElement() == Card.SUB_EARTH)) {
                if (playerHasGod(player, Card.SUB_EARTH)) {
                    progressElement(game, player, Card.SUB_EARTH, 12);
                } else {
                    progressElement(game, player, Card.SUB_EARTH, 6);
                }
            } else if (card.getSubtype() == Card.SUB_AIR ||
                    (card.getSubtype() == Card.SUB_ETHER
                            && action.getElement() == Card.SUB_AIR)) {
                if (playerHasGod(player, Card.SUB_AIR)) {
                    progressElement(game, player, Card.SUB_AIR, 12);
                } else {
                    progressElement(game, player, Card.SUB_AIR, 6);
                }
            }



        // action gods
        } else if (card.getType()==Card.TYPE_ACTION_GOD){

        // Adicia: intercambio posiciones fichas en un elemento dado
        if (card.getValue()==Card.ACTION_GOD_ADICIA){
            if (action.getElement()==Card.SUB_FIRE){
                int enemyPos = game.getEnemy().getFirePiecePos();
                game.getEnemy().setFirePiecePos(player.getFirePiecePos());
                player.setFirePiecePos(enemyPos);
            } else if (action.getElement()==Card.SUB_WATER){
                int enemyPos = game.getEnemy().getWaterPiecePos();
                game.getEnemy().setWaterPiecePos(player.getWaterPiecePos());
                player.setWaterPiecePos(enemyPos);
            } else if (action.getElement()==Card.SUB_EARTH){
                int enemyPos = game.getEnemy().getEarthPiecePos();
                game.getEnemy().setEarthPiecePos(player.getEarthPiecePos());
                player.setEarthPiecePos(enemyPos);
            } else  if (action.getElement()==Card.SUB_AIR){
                int enemyPos = game.getEnemy().getAirPiecePos();
                game.getEnemy().setAirPiecePos(player.getAirPiecePos());
                player.setAirPiecePos(enemyPos);
            }

            // apate: intercambiar cartas de mano
        } if (card.getValue()==Card.ACTION_GOD_APATE){
            List<Card> enemyCards = new ArrayList<>();
            enemyCards.addAll(game.getEnemy().getHandCards());
             List<Card> playerCards = new ArrayList<>();
             playerCards.addAll(player.getHandCards());
             game.getEnemy().setHandCards(playerCards);
             player.setHandCards(enemyCards);

            // ares: el jugador enemigo no robara carta
        } if (card.getValue()==Card.ACTION_GOD_ARES){
            game.getEnemy().setCanDrawNextTurn(false);

            // artemisa: te entregan cartas de mano del elemento que elijas
        } if (card.getValue()==Card.ACTION_GOD_ARTEMISA){
            List<Card> cardsToSteal = getCardsByElement(game.getEnemy(), action.getElement());
            for(Card myCard: cardsToSteal){
                game.getEnemy().getHandCards().remove(myCard);
                player.getHandCards().add(myCard);
            }

            // autolico: robar carta al azar del rival
        } if (card.getValue()==Card.ACTION_GOD_AUTOLICO){
            if (!game.getEnemy().getHandCards().isEmpty()){
                Collections.shuffle(game.getEnemy().getHandCards());
                Card toStealCard = game.getEnemy().getHandCards().get(0);
                game.getEnemy().getHandCards().remove(toStealCard);
                player.getHandCards().add(toStealCard);
            }


            // boreas: las fichas del enemigo no avanzan durante 2 turnos en el elemento que elijas
        } if (card.getValue()==Card.ACTION_GOD_BOREAS){
                game.getEnemy().setProgressAllowed(false);
                game.getEnemy().setTurnsProgressBanned(3); // le ponemos 3 porque al principio de turno va a bajar a 2 en su mantenimineot
                game.getEnemy().setElementProgressBanned(action.getElement());

            // hades: la ficha elegida retrocede 5 casillas. Y no hay lo de caer en posicion 6
        } if (card.getValue()==Card.ACTION_GOD_HADES){
            if (action.getElement()==Card.SUB_FIRE){
                game.getEnemy().setFirePiecePos(Math.max(game.getEnemy().getFirePiecePos()-5,0));
            } else if (action.getElement()==Card.SUB_WATER){
                game.getEnemy().setWaterPiecePos(Math.max(game.getEnemy().getWaterPiecePos()-5,0));
            } else if (action.getElement()==Card.SUB_EARTH){
                game.getEnemy().setEarthPiecePos(Math.max(game.getEnemy().getEarthPiecePos()-5,0));
            } else  if (action.getElement()==Card.SUB_AIR){
                game.getEnemy().setAirPiecePos(Math.max(game.getEnemy().getAirPiecePos()-5,0));
            }
            // harmonia: puedes usar 2 avances por turno
        } if (card.getValue()==Card.ACTION_GOD_HARMONIA){
            // no hace nada TODO: implementar como se usa esto

            // hecate: multiplicas por dos la puntuacion de avance
        } if (card.getValue()==Card.ACTION_GOD_HECATE){
            // no hace nada : TODO: implementar como se usa esto

            // hermes: avanzas 6 casillas en un elemento
        } if (card.getValue()==Card.ACTION_GOD_HERMES){
            if (action.getElement()==Card.SUB_FIRE){
                progressElement(game, player, Card.SUB_FIRE, 6);
            } else if (action.getElement()==Card.SUB_WATER){
                progressElement(game, player, Card.SUB_WATER, 6);
            } else if (action.getElement()==Card.SUB_EARTH){
                progressElement(game, player, Card.SUB_EARTH, 6);
            } else  if (action.getElement()==Card.SUB_AIR){
                progressElement(game, player, Card.SUB_AIR, 6);
            }
            // tique: avanzas 3 casillas en un elemento
        } if (card.getValue()==Card.ACTION_GOD_TIQUE){
            if (action.getElement()==Card.SUB_FIRE){
                progressElement(game, player, Card.SUB_FIRE, 3);
            } else if (action.getElement()==Card.SUB_WATER){
                progressElement(game, player, Card.SUB_WATER, 3);
            } else if (action.getElement()==Card.SUB_EARTH){
                progressElement(game, player, Card.SUB_EARTH, 3);
            } else  if (action.getElement()==Card.SUB_AIR){
                progressElement(game, player, Card.SUB_AIR, 3);
            }
            // elpis: roba 3 cartas
        } if (card.getValue()==Card.ACTION_GOD_ELPIS){
            for (int i=0; i<3;i++) {
                Card drewCard = game.getBoard().getBoardCards().get(0);
                game.getBoard().getBoardCards().remove(drewCard);
                player.getHandCards().add(drewCard);
            }
        }



    }
        // descartamos carta porque la hemos jugado y la devolvemos al mazo
        discard(game, player, card);



    }





    // realiza el avance en un elemento
    // además si cae en casilla 6 y enemigo entre 0 y 6, le hace retroceder a cero
    // y si esta entre la 6 y la 11, retrocede a la 6
    // Implementadas tambien las defensas,
    // TODO: no hay forma de alertar de que ha habido una defensa pero tampoco es el objetivo del proyecto
    private static void progressElement(Game game, Player player, int element, int progress) {
        //System.out.println("Progresamos elemento="+element+" progress="+progress);
        Player enemy = game.getEnemy();
        if (element == Card.SUB_FIRE) {
            player.setFirePiecePos(Math.min(12, player.getFirePiecePos() + progress));
            if (!playerHasDefenseAgainstElementAttack(enemy, Card.SUB_FIRE)) {
                if (player.getFirePiecePos() == 6 && enemy.getFirePiecePos() < 6) {
                    enemy.setFirePiecePos(0);
                }
                if (player.getFirePiecePos() == 6 && enemy.getFirePiecePos() > 6 && enemy.getFirePiecePos() < 12) {
                    enemy.setFirePiecePos(6);
                }
            }
        } else if (element == Card.SUB_WATER) {
            player.setWaterPiecePos(Math.min(12, player.getWaterPiecePos() + progress));
            if (!playerHasDefenseAgainstElementAttack(enemy, Card.SUB_WATER)) {
                if (player.getWaterPiecePos() == 6 && enemy.getWaterPiecePos() < 6) {
                    enemy.setWaterPiecePos(0);
                }
                if (player.getWaterPiecePos() == 6 && enemy.getWaterPiecePos() > 6 && enemy.getWaterPiecePos() < 12) {
                    enemy.setWaterPiecePos(6);
                }
            }
        } else if (element == Card.SUB_EARTH) {
            player.setEarthPiecePos(Math.min(12, player.getEarthPiecePos() + progress));
            if (!playerHasDefenseAgainstElementAttack(enemy, Card.SUB_EARTH)) {
                if (player.getEarthPiecePos() == 6 && enemy.getEarthPiecePos() < 6) {
                    enemy.setEarthPiecePos(0);
                }
                if (player.getEarthPiecePos() == 6 && enemy.getEarthPiecePos() > 6 && enemy.getEarthPiecePos() < 12) {
                    enemy.setEarthPiecePos(6);
                }
            }
        } else if (element == Card.SUB_AIR) {
            player.setAirPiecePos(Math.min(12, player.getAirPiecePos() + progress));
            if (!playerHasDefenseAgainstElementAttack(enemy, Card.SUB_AIR)) {
                if (player.getAirPiecePos() == 6 && enemy.getAirPiecePos() < 6) {
                    enemy.setAirPiecePos(0);
                }
                if (player.getAirPiecePos() == 6 && enemy.getAirPiecePos() > 6 && enemy.getAirPiecePos() < 12) {
                    enemy.setAirPiecePos(6);
                }
            }
        }
    }


    // ATENCION: Vamos a hacer mas sencilla una norma
    // se puede defender con dios (teniendolo) o con elemental del elemento o del eter (gastandolo)
    // la parte del elemental requeriria tomar una decision
    // solucion sencilla empleada: defensa con dios (teniendolo) o con elemental (TENIENDOLO)
    // y el elemental del eter no defiende
    private static boolean playerHasDefenseAgainstElementAttack(Player player, int element){
        if (element==Card.SUB_FIRE && (hasCard(player.getHandCards(), CardEnum.GOD_FIRE) ||
                hasCard(player.getHandCards(), CardEnum.ELEM_FIRE))){
            return true;
        }
        if (element==Card.SUB_WATER && (hasCard(player.getHandCards(), CardEnum.GOD_WATER) ||
                hasCard(player.getHandCards(), CardEnum.ELEM_WATER))){
            return true;
        }
        if (element==Card.SUB_EARTH && (hasCard(player.getHandCards(), CardEnum.GOD_EARTH) ||
                hasCard(player.getHandCards(), CardEnum.ELEM_EARTH))){
            return true;
        }
        if (element==Card.SUB_AIR && (hasCard(player.getHandCards(), CardEnum.GOD_AIR) ||
                hasCard(player.getHandCards(), CardEnum.ELEM_AIR))){
            return true;
        }
        return false;
    }


    // debe invocarse despues de la accion de robar
    public static void maintenanceAtTurnStarting(Game game, Player player){
        // poner el puedeRobar a true
        player.setCanDrawNextTurn(true);
        // boreas, turnos sin poder avanzar un elemento
        if (!player.isProgressAllowed()){
            player.setTurnsProgressBanned(player.getTurnsProgressBanned()-1);
            if (player.getTurnsProgressBanned()==0){
                player.setProgressAllowed(true);
            }
        }

    }



    public static void playMachineVsMachine(Game game) {

        boolean ended = false;

        int turn = 0;
        int age = 0;

        while (!ended) {

            System.out.println("");
            System.out.println("Turno de "+game.getActivePlayer().getName() + " TURN="+turn + " AGE="+age);
            //System.out.println("PLAYER STATUS: ");
            System.out.println("Estado del jugador: "+game.getActivePlayer().toString());

            // imprimir cartas de mano
            for(Card card: game.getActivePlayer().getHandCards()){
                System.out.println("      Carta:"+card.getName()+ " ");
            }

            // robar carta, si esta habilitado
            if (age > 0 && game.getActivePlayer().isCanDrawNextTurn()) {
                Card drewCard = game.getBoard().getBoardCards().get(0);
                game.getBoard().getBoardCards().remove(drewCard);
                game.getActivePlayer().getHandCards().add(drewCard);
                System.out.println("     Nueva carta robada: "+drewCard.getName()+ " "+drewCard.getDescription());
            }

            //mantenimiento despues de accion de robar
            maintenanceAtTurnStarting(game, game.getActivePlayer());

            // verify victory
            if (canWin(game.getActivePlayer())){
                ended=true;
                System.out.println(game.getActivePlayer().getName() + " GANA LA PARTIDA !!!!");
                System.out.println("PLAYER STATUS: ");
                System.out.println(game.getActivePlayer().toString());
                System.out.println("ENEMY STATUS: ");
                System.out.println(game.getEnemy().toString());
                game.getActivePlayer().setVictory(true);

                // escribir experiencias en fichero
                System.out.println("Vamos a escribir experiencias");
                if (game.getActivePlayer().getMode() == Player.MODE_GENERATE_DATA){
                    for (Experience experience: game.getActivePlayer().getExperiences()){
                        experience.setVictory(1);
                        System.out.println(experience.toString());
                        FileIO.addExperience(experience);
                    }
                }
                if (game.getEnemy().getMode() == Player.MODE_GENERATE_DATA){
                    for (Experience experience: game.getEnemy().getExperiences()){
                        experience.setVictory(0);
                        System.out.println(experience.toString());
                        FileIO.addExperience(experience);
                    }
                }


            }


            // decidir accion y emprenderla
                MarkovAction action = MarkovLogic.findBestAction(game, game.getActivePlayer());
                System.out.println("   Accion optima: "+action.toString());
                // perform action
                if (action.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                    System.out.println("   Decide jugar carta: "+action.getCard1().getName());
                    playCard(game, game.getActivePlayer(), action);
                } else if (action.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {
                    System.out.println("Se decide desechar 3 para robar 1 "+action.getCard1().getName()+" "+
                    action.getCard2().getName()+" "+action.getCard3().getName());
                    perform3x1(game, game.getActivePlayer(), action);
                }


            // change turn, increase turn, increase age
            Player enemy = game.getEnemy();
            game.setActivePlayer(enemy);
            turn++;
            age=turn/2;
            game.setTurn(turn);
            game.setAge(age);

        }






    }




    public static Player getPlayerByTurn(Game game, int turn) {
        for (Player player : game.getPlayers()) {
            if (turn == player.getOrder()) {
                return player;
            }
        }
        return null;
    }

    public static void shuffle(List<Card> boardCards) {
        Collections.shuffle(boardCards);
    }


    public static boolean hasAllInEther(Player player) {
        return (player.getFirePiecePos() == 12 &&
                player.getWaterPiecePos() == 12 &&
                player.getEarthPiecePos() == 12 &&
                player.getAirPiecePos() == 12);
    }


    public static Card playerDrawCard(Board board, Player player) {
        Card card = board.getBoardCards().get(0);
        board.getBoardCards().remove(card);
        player.getHandCards().add(card);
        return card;
    }

    private static void perform3x1(Game game, Player player, MarkovAction action){
        discard(game, player, action.getCard1());
        discard(game, player, action.getCard2());
        discard(game, player, action.getCard3());
        playerDrawCard(game.getBoard(), player);
    }

    private static void discard(Game game, Player player, Card card){
        Card toDiscard = null;
        for (Card iterCard: player.getHandCards()){
            if (iterCard.getType()==card.getType() && iterCard.getSubtype()==card.getSubtype() && iterCard.getValue()==card.getValue()){
                toDiscard=iterCard;
            }
        }
        if (toDiscard!=null){
            player.getHandCards().remove(toDiscard);
            game.getBoard().getBoardCards().add(toDiscard);
        }
    }



    public static boolean playerHasGod(Player player, int element) {
        for (Card card : player.getHandCards()) {
            if (card.getType() == Card.TYPE_ELEMENTAL_GOD && card.getSubtype() == element) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCard(List<Card> cards, CardEnum cardEnum) {
        for (Card card : cards) {
            if (card.getType() == cardEnum.getCardType() &&
                    card.getSubtype() == cardEnum.getCardSubtype() &&
                    card.getValue() == cardEnum.getValue()) {
                return true;
            }
        }
        return false;
    }

    public static boolean playerHasCard(Player player, CardEnum cardEnum) {
        return hasCard(player.getHandCards(), cardEnum);
    }

    public static List<Card> getCardsByElement(Player player, int element) {
        List<Card> retVal = new ArrayList<>();
        for (Card card : player.getHandCards()) {
            if ((card.getType()==Card.TYPE_FORWARD || card.getType()==Card.TYPE_SPECIAL_FORWARD)
                    && card.getSubtype()==element){
                retVal.add(card);
            }
        }
        return retVal;
    }
    public static CardEnum getCardEnumByTypes(int type, int subtype, int value){
        for (CardEnum ce: CardEnum.values()){
            if (ce.getCardType()==type && ce.getCardSubtype()==subtype && ce.getValue()==value){
                return ce;
            }
        }
        return null;
    }

    public static Card getElementRepresentation(List<Card> playerCards, int element) {
        // 1 buscar dios
        // 2 buscar elemento
        // 3 buscar avance especial
        // 4 elemental eter
        for (Card card : playerCards) {
            if (card.getType() == Card.TYPE_ELEMENTAL_GOD && card.getSubtype() == element) {
                return card;
            }
        }
        for (Card card : playerCards) {
            if (card.getType() == Card.TYPE_ELEMENTAL && card.getSubtype() == element) {
                return card;
            }
        }
        for (Card card : playerCards) {
            if (card.getType() == Card.TYPE_SPECIAL_FORWARD && card.getSubtype() == element) {
                return card;
            }
        }
        for (Card card : playerCards) {
            if (card.getType() == Card.TYPE_ELEMENTAL && card.getSubtype() == Card.SUB_ETHER) {
                return card;
            }
        }
        return null;
    }

    public static int getLessProgressedElement(Player player){
        int value = player.getFirePiecePos();
        int element = Card.SUB_FIRE;
        if (player.getWaterPiecePos()<element){
            element = Card.SUB_WATER;
            value = player.getWaterPiecePos();
        }
        if (player.getEarthPiecePos()<element){
            element = Card.SUB_EARTH;
            value = player.getEarthPiecePos();
        }
        if (player.getAirPiecePos()<element){
            element = Card.SUB_AIR;
            value = player.getAirPiecePos();
        }
        return element;
    }

    public static boolean has3x1Combination(List<MarkovAction> actions, Card card1, Card card2, Card card3){
        for (MarkovAction action: actions){
            if (action.getActionType()==MarkovAction.ACTION_DISCARD_3_DRAW_1){
                if (action.getCard1().equals(card1)&& action.getCard2().equals(card2)&& action.getCard3().equals(card3)){
                    return true;
                }
                if (action.getCard1().equals(card1)&& action.getCard3().equals(card2)&& action.getCard2().equals(card3)){
                    return true;
                }
                if (action.getCard2().equals(card1)&& action.getCard1().equals(card2)&& action.getCard3().equals(card3)){
                    return true;
                }
                if (action.getCard2().equals(card1)&& action.getCard3().equals(card2)&& action.getCard1().equals(card3)){
                    return true;
                }
                if (action.getCard3().equals(card1)&& action.getCard1().equals(card2)&& action.getCard2().equals(card3)){
                    return true;
                }
                if (action.getCard3().equals(card1)&& action.getCard2().equals(card2)&& action.getCard1().equals(card3)){
                    return true;
                }
            }
        }
        return false;
    }


    public static int getPlayerPhase(Player player){
        double avg = (player.getFirePiecePos()+player.getWaterPiecePos()+player.getEarthPiecePos()+player.getAirPiecePos())/4.0;
        if (avg < 6) return 1;
        if (avg >= 6 && avg < 12) return 2;
        return 3;
    }



}
