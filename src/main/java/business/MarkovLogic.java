package business;

import model.*;
import model.enums.CardEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MarkovLogic {


    public static final double DISCOUNT_FACTOR = 0.4; //[0..1]
    public static final double LEARNING_RATE = 0.075; //[0..1]


    // Devuelve la lista de posibles acciones a tomar dado un estado inicial y cartas de mano
    private static List<MarkovAction> getActionList(Player player, MarkovState initState) {
        List<MarkovAction> retVal = new ArrayList<>();

        // acciones de jugar carta
        for (Card card : initState.getHandCards()) {

            // Casos especiales:

            // si la carta es el ether, no se permite "jugarla", porque
            // solo sirve para cumplir la condicion de victoria
            if (card.getType() == Card.TYPE_ETHER) {

                // de momento, los dioses tampoco generan acciones, mejor tenerlos en mano para defensa y ganar
                // TODO: a futuro, podran hacer lo de elemental+dios = avance al ether directo, pero esto no es muy util...
            } else if (card.getType() == Card.TYPE_ELEMENTAL_GOD) {

                // para un avance de ether (avance o elemental), deberiamos generar una posibilidad por cada elemento
            } else if ((card.getType() == Card.TYPE_FORWARD || card.getType() == Card.TYPE_ELEMENTAL) && card.getSubtype() == Card.SUB_ETHER) {
                //fuego
                if (player.isProgressAllowed() || (!player.isProgressAllowed() &&
                        player.getElementProgressBanned()!=Card.SUB_FIRE)) {
                    MarkovAction etherFire = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherFire.setElement(Card.SUB_FIRE);
                    retVal.add(etherFire);
                }

                //agua
                if (player.isProgressAllowed() || (!player.isProgressAllowed() &&
                        player.getElementProgressBanned()!=Card.SUB_WATER)) {
                    MarkovAction etherWater = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherWater.setElement(Card.SUB_WATER);
                    retVal.add(etherWater);
                }

                //tierra
                if (player.isProgressAllowed() || (!player.isProgressAllowed() &&
                        player.getElementProgressBanned()!=Card.SUB_EARTH)) {
                    MarkovAction etherEarth = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherEarth.setElement(Card.SUB_EARTH);
                    retVal.add(etherEarth);
                }

                //aire
                if (player.isProgressAllowed() || (!player.isProgressAllowed() &&
                        player.getElementProgressBanned()!=Card.SUB_AIR)) {
                    MarkovAction etherAir = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherAir.setElement(Card.SUB_AIR);
                    retVal.add(etherAir);
                }

                // para determinados dioses de accion tambien hay que elegir elemento
            } else if (card.getType() == Card.TYPE_ACTION_GOD &&
                    (card.getValue() == Card.ACTION_GOD_ADICIA || card.getValue() == Card.ACTION_GOD_ARTEMISA
                            || card.getValue() == Card.ACTION_GOD_BOREAS || card.getValue() == Card.ACTION_GOD_HADES
                            || card.getValue() == Card.ACTION_GOD_HERMES || card.getValue() == Card.ACTION_GOD_TIQUE)) {

                // verificaciones especiales para dioses que impliquen progreso de un elemento
                if (card.getValue()==Card.ACTION_GOD_HERMES || card.getValue()==Card.ACTION_GOD_TIQUE ||
                        card.getValue()==Card.ACTION_GOD_ADICIA){

                    //fuego
                    if ((player.isProgressAllowed() || (!player.isProgressAllowed() &&
                            player.getElementProgressBanned()!=Card.SUB_FIRE))) {
                        MarkovAction etherFire = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                        etherFire.setElement(Card.SUB_FIRE);
                        retVal.add(etherFire);
                    }


                    //agua
                    if ((player.isProgressAllowed() || (!player.isProgressAllowed() &&
                            player.getElementProgressBanned()!=Card.SUB_WATER))) {
                        MarkovAction etherWater = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                        etherWater.setElement(Card.SUB_WATER);
                        retVal.add(etherWater);
                    }

                    //tierra
                    if ((player.isProgressAllowed() || (!player.isProgressAllowed() &&
                            player.getElementProgressBanned()!=Card.SUB_EARTH))) {
                        MarkovAction etherEarth = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                        etherEarth.setElement(Card.SUB_EARTH);
                        retVal.add(etherEarth);
                    }

                    //aire
                    if ((player.isProgressAllowed() || (!player.isProgressAllowed() &&
                            player.getElementProgressBanned()!=Card.SUB_AIR))) {
                        MarkovAction etherAir = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                        etherAir.setElement(Card.SUB_AIR);
                        retVal.add(etherAir);
                    }
                } else {
                    //fuego
                    MarkovAction etherFire = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherFire.setElement(Card.SUB_FIRE);
                    retVal.add(etherFire);

                    //agua
                    MarkovAction etherWater = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherWater.setElement(Card.SUB_WATER);
                    retVal.add(etherWater);

                    //tierra
                    MarkovAction etherEarth = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherEarth.setElement(Card.SUB_EARTH);
                    retVal.add(etherEarth);

                    //aire
                    MarkovAction etherAir = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);
                    etherAir.setElement(Card.SUB_AIR);
                    retVal.add(etherAir);
                }


                // Casos no especiales
            } else {
                retVal.add(new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card));
            }
        }

        // accion de inaccion (es decir, no hacer nada, pero tambien representa una decision)
        retVal.add(new MarkovAction(MarkovAction.ACTION_DO_NOTHING));

        // acciones de descartar 3 para robar 1
        // solo se generan en etapa final
        // y solo con las cartas que no sirvan para completar juego
        // y si no tiene el ether
        if (initState.isFinalStage() && !ElementosEngine.hasCard(initState.getHandCards(), CardEnum.ETHER)){

            List<Card> combinationList = new ArrayList<>();
            combinationList.addAll(initState.getHandCards());

            Card fireRepresentation = ElementosEngine.getElementRepresentation(combinationList, Card.SUB_FIRE);
            if (fireRepresentation!=null){
                combinationList.remove(fireRepresentation);
            }
            Card waterRepresentation = ElementosEngine.getElementRepresentation(combinationList, Card.SUB_WATER);
            if (waterRepresentation!=null){
                combinationList.remove(waterRepresentation);
            }
            Card earthRepresentation = ElementosEngine.getElementRepresentation(combinationList, Card.SUB_EARTH);
            if (earthRepresentation!=null){
                combinationList.remove(earthRepresentation);
            }
            Card airRepresentation = ElementosEngine.getElementRepresentation(combinationList, Card.SUB_AIR);
            if (airRepresentation!=null){
                combinationList.remove(airRepresentation);
            }



            if (combinationList.size()>=3) {
                /*for (Card card: combinationList){
                    System.out.println("Van a entrar al 3x1 "+card.getName());
                }
                System.out.println(fireRepresentation!=null?fireRepresentation.getName():"fuego null");
                System.out.println(waterRepresentation!=null?waterRepresentation.getName():"agua null");
                System.out.println(earthRepresentation!=null?earthRepresentation.getName():"tierra null");
                System.out.println(airRepresentation!=null?airRepresentation.getName():"aire null");
                */
                for (Card card1 : combinationList) {
                    for (Card card2 : combinationList) {
                        for (Card card3 : combinationList) {
                            if (!card1.equals(card2) && !card1.equals(card3) && !card2.equals(card3)
                                    && !ElementosEngine.has3x1Combination(retVal, card1, card2, card3)){
                                retVal.add(new MarkovAction(MarkovAction.ACTION_DISCARD_3_DRAW_1, card1, card2, card3));
                                //System.out.println("3x1: "+card1.getName()+" "+card2.getName()+" "+card3.getName());
                            }
                        }
                    }
                }
            }
        }

        // TODO: acciones de lanzar desastre natural


        return retVal;
    }








    // obtiene un estado del juego
    public static MarkovState getInitState(Game game, Player player) {
        Player enemy = null;
        for (Player p : game.getPlayers()) {
            if (!p.equals(player)) {
                enemy = p;
            }
        }
        if (enemy == null) return null;

        MarkovState state = new MarkovState();
        state.setFirePiecePos(player.getFirePiecePos());
        state.setWaterPiecePos(player.getWaterPiecePos());
        state.setEarthPiecePos(player.getEarthPiecePos());
        state.setAirPiecePos(player.getAirPiecePos());
        state.setHandCardsCount(player.getHandCards().size());
        state.setCanDrawNextTurn(player.isCanDrawNextTurn());
        state.setEnemyFirePiecePos(enemy.getFirePiecePos());
        state.setEnemyWaterPiecePos(enemy.getWaterPiecePos());
        state.setEnemyEarthPiecePos(enemy.getEarthPiecePos());
        state.setEnemyAirPiecePos(enemy.getAirPiecePos());
        state.setEnemyHandCardsCount(enemy.getHandCards().size());
        state.setEnemyCanDrawnNextTurn(enemy.isCanDrawNextTurn());
        state.setHandCards(new ArrayList<>());
        state.getHandCards().addAll(player.getHandCards());

        return state;
    }

    // dados
    // - estado previo
    // - accion a tomar
    // calcula --> estado siguiente
    // Esto es llamado "función de transición"
    private static MarkovState getNextState(Game game, Player player, MarkovState prevState, MarkovAction action) {
        Player enemy = null;
        for (Player p : game.getPlayers()) {
            if (!p.equals(player)) {
                enemy = p;
            }
        }
        if (enemy == null) return null;

        // primero, clonamos el estado previo
        // ademas, si alguno de los jugadores no podia robar el turno previo,
        // en el siguiente ya se habilita poder robar, por lo que se pone a true seguro
        MarkovState nextState = new MarkovState();
        nextState.setFirePiecePos(prevState.getFirePiecePos());
        nextState.setWaterPiecePos(prevState.getWaterPiecePos());
        nextState.setEarthPiecePos(prevState.getEarthPiecePos());
        nextState.setAirPiecePos(prevState.getAirPiecePos());
        nextState.setHandCardsCount(prevState.getHandCardsCount());
        if (prevState.isCanDrawNextTurn()) {
            nextState.setHandCardsCount(prevState.getHandCardsCount() + 1); // suponemos que robamos carta
        }
        nextState.setCanDrawNextTurn(true);
        nextState.setEnemyFirePiecePos(prevState.getFirePiecePos());
        nextState.setEnemyWaterPiecePos(prevState.getWaterPiecePos());
        nextState.setEnemyEarthPiecePos(prevState.getEarthPiecePos());
        nextState.setEnemyAirPiecePos(prevState.getAirPiecePos());
        nextState.setEnemyHandCardsCount(prevState.getEnemyHandCardsCount() - 1); // que descarta una
        if (prevState.isEnemyCanDrawnNextTurn()) {
            nextState.setEnemyHandCardsCount(prevState.getEnemyHandCardsCount() + 1);
        }
        nextState.setEnemyCanDrawnNextTurn(true);

        // copiar lista de cartas de mano
        nextState.setHandCards(new ArrayList<>());
        nextState.getHandCards().addAll(prevState.getHandCards());
        // si la accion implica uno/dos descartes, quitar esa carta/s
        if (action.getCard1() != null) {
            nextState.getHandCards().remove(action.getCard1());
        }
        if (action.getCard2() != null) {
            nextState.getHandCards().remove(action.getCard2());
        }


        // segundo, implementamos consecuencias de la accion
        if (action.getActionType() == MarkovAction.ACTION_PLAY_CARD) {

            // avance normal y avance normal de ether
            if (action.getCard1().getType() == Card.TYPE_FORWARD) {
                if (action.getCard1().getSubtype() == Card.SUB_FIRE ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_FIRE)) {
                    progressElement(prevState, nextState, Card.SUB_FIRE, action.getCard1().getValue());
                } else if (action.getCard1().getSubtype() == Card.SUB_WATER ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_WATER)) {
                    progressElement(prevState, nextState, Card.SUB_WATER, action.getCard1().getValue());
                } else if (action.getCard1().getSubtype() == Card.SUB_EARTH ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_EARTH)) {
                    progressElement(prevState, nextState, Card.SUB_EARTH, action.getCard1().getValue());
                } else if (action.getCard1().getSubtype() == Card.SUB_AIR ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_AIR)) {
                    progressElement(prevState, nextState, Card.SUB_AIR, action.getCard1().getValue());
                }


                // avance especial
            } else if (action.getCard1().getType() == Card.TYPE_SPECIAL_FORWARD) {
                if (action.getCard1().getSubtype() == Card.SUB_FIRE) {
                    progressElement(prevState, nextState, Card.SUB_FIRE, action.getCard1().getValue());
                } else if (action.getCard1().getSubtype() == Card.SUB_WATER) {
                    progressElement(prevState, nextState, Card.SUB_WATER, action.getCard1().getValue());
                } else if (action.getCard1().getSubtype() == Card.SUB_EARTH) {
                    progressElement(prevState, nextState, Card.SUB_EARTH, action.getCard1().getValue());
                } else if (action.getCard1().getSubtype() == Card.SUB_AIR) {
                    progressElement(prevState, nextState, Card.SUB_AIR, action.getCard1().getValue());
                }


                // avance por elemental = 6 casillas
                // si además tiene el dios correspondiente, avanzaria 12...
            } else if (action.getCard1().getType() == Card.TYPE_ELEMENTAL) {
                if (action.getCard1().getSubtype() == Card.SUB_FIRE ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_FIRE)) {
                    if (ElementosEngine.playerHasGod(player, Card.SUB_FIRE)) {
                        progressElement(prevState, nextState, Card.SUB_FIRE, 12);
                    } else {
                        progressElement(prevState, nextState, Card.SUB_FIRE, 6);
                    }
                } else if (action.getCard1().getSubtype() == Card.SUB_WATER ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_WATER)) {
                    if (ElementosEngine.playerHasGod(player, Card.SUB_WATER)) {
                        progressElement(prevState, nextState, Card.SUB_WATER, 12);
                    } else {
                        progressElement(prevState, nextState, Card.SUB_WATER, 6);
                    }
                } else if (action.getCard1().getSubtype() == Card.SUB_EARTH ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_EARTH)) {
                    if (ElementosEngine.playerHasGod(player, Card.SUB_EARTH)) {
                        progressElement(prevState, nextState, Card.SUB_EARTH, 12);
                    } else {
                        progressElement(prevState, nextState, Card.SUB_EARTH, 6);
                    }
                } else if (action.getCard1().getSubtype() == Card.SUB_AIR ||
                        (action.getCard1().getSubtype() == Card.SUB_ETHER
                                && action.getElement() == Card.SUB_AIR)) {
                    if (ElementosEngine.playerHasGod(player, Card.SUB_AIR)) {
                        progressElement(prevState, nextState, Card.SUB_AIR, 12);
                    } else {
                        progressElement(prevState, nextState, Card.SUB_AIR, 6);
                    }
                }


                // action gods
            } else if (action.getCard1().getType() == Card.TYPE_ACTION_GOD) {

                // Adicia: intercambio posiciones fichas en un elemento dado
                if (action.getCard1().getValue() == Card.ACTION_GOD_ADICIA) {
                    if (action.getElement() == Card.SUB_FIRE) {
                        nextState.setEnemyFirePiecePos(prevState.getFirePiecePos());
                        nextState.setFirePiecePos(prevState.getEnemyFirePiecePos());
                    } else if (action.getElement() == Card.SUB_WATER) {
                        nextState.setEnemyWaterPiecePos(prevState.getWaterPiecePos());
                        nextState.setWaterPiecePos(prevState.getEnemyWaterPiecePos());
                    } else if (action.getElement() == Card.SUB_EARTH) {
                        nextState.setEnemyEarthPiecePos(prevState.getEarthPiecePos());
                        nextState.setEarthPiecePos(prevState.getEnemyEarthPiecePos());
                    } else if (action.getElement() == Card.SUB_AIR) {
                        nextState.setEnemyAirPiecePos(prevState.getAirPiecePos());
                        nextState.setAirPiecePos(prevState.getEnemyAirPiecePos());
                    }
                // apate: intercambiar cartas de mano
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_APATE) {
                    nextState.setHandCardsCount(prevState.getEnemyHandCardsCount());
                    nextState.setEnemyHandCardsCount(prevState.getHandCardsCount());
                    // ares: el jugador no robara carta
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_ARES) {
                    nextState.setEnemyCanDrawnNextTurn(false);
                    // artemisa: te entregan cartas de mano del elemento que elijas
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_ARTEMISA) {
                    // como no sabemos nada de las cartas que tiene el enemigo,
                    // supondremos que si tiene 4 cartas o mas, te dara 1
                    if (prevState.getEnemyHandCardsCount() >= 4) {
                        nextState.setEnemyHandCardsCount(prevState.getEnemyHandCardsCount() - 1);
                        nextState.setHandCardsCount(prevState.getHandCardsCount() + 1);
                    }

                }
                // autolico: robar carta al azar del rival
                if (action.getCard1().getValue() == Card.ACTION_GOD_AUTOLICO) {
                    if (nextState.getEnemyHandCardsCount() > 0) {
                        nextState.setEnemyHandCardsCount(prevState.getEnemyHandCardsCount() - 1);
                        nextState.setHandCardsCount(prevState.getHandCardsCount() + 1);
                    }
                }
                // boreas: las fichas del enemigo no avanzan durante 2 turnos en el elemento que elijas
                if (action.getCard1().getValue() == Card.ACTION_GOD_BOREAS) {
                    // no afecta a siguiente estado
                }
                // hades: la ficha elegida retrocede 5 casillas
                if (action.getCard1().getValue() == Card.ACTION_GOD_HADES) {
                    if (action.getElement() == Card.SUB_FIRE) {
                        nextState.setEnemyFirePiecePos(Math.max(prevState.getEnemyFirePiecePos() - 5, 0));
                    } else if (action.getElement() == Card.SUB_WATER) {
                        nextState.setEnemyWaterPiecePos(Math.max(prevState.getEnemyWaterPiecePos() - 5, 0));
                    } else if (action.getElement() == Card.SUB_EARTH) {
                        nextState.setEnemyEarthPiecePos(Math.max(prevState.getEnemyEarthPiecePos() - 5, 0));
                    } else if (action.getElement() == Card.SUB_AIR) {
                        nextState.setEnemyAirPiecePos(Math.max(prevState.getEnemyAirPiecePos() - 5, 0));
                    }
                    // harmonia: puedes usar 2 avances por turno
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_HARMONIA) {
                    // no hace nada TODO: implementar como se usa esto

                    // hecate: multiplicas por dos la puntuacion de avance
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_HECATE) {
                    // no hace nada : TODO: implementar como se usa esto

                    // hermes: avanzas 6 casillas en un elemento
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_HERMES) {
                    if (action.getElement() == Card.SUB_FIRE) {
                        //nextState.setFirePiecePos(Math.min(12, prevState.getFirePiecePos() + 6));
                        progressElement(prevState, nextState, Card.SUB_FIRE, 6);
                    } else if (action.getElement() == Card.SUB_WATER) {
                        //nextState.setWaterPiecePos(Math.min(12, prevState.getWaterPiecePos() + 6));
                        progressElement(prevState, nextState, Card.SUB_WATER, 6);
                    } else if (action.getElement() == Card.SUB_EARTH) {
                        //nextState.setEarthPiecePos(Math.min(12, prevState.getEarthPiecePos() + 6));
                        progressElement(prevState, nextState, Card.SUB_EARTH, 6);
                    } else if (action.getElement() == Card.SUB_AIR) {
                        //nextState.setAirPiecePos(Math.min(12, prevState.getAirPiecePos() + 6));
                        progressElement(prevState, nextState, Card.SUB_AIR, 6);
                    }
                    // tique: avanzas 3 casillas en un elemento
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_TIQUE) {
                    if (action.getElement() == Card.SUB_FIRE) {
                        //nextState.setFirePiecePos(Math.min(12, prevState.getFirePiecePos() + 3));
                        progressElement(prevState, nextState, Card.SUB_FIRE, 3);
                    } else if (action.getElement() == Card.SUB_WATER) {
                        //nextState.setWaterPiecePos(Math.min(12, prevState.getWaterPiecePos() + 3));
                        progressElement(prevState, nextState, Card.SUB_WATER, 3);
                    } else if (action.getElement() == Card.SUB_EARTH) {
                        //nextState.setEarthPiecePos(Math.min(12, prevState.getEarthPiecePos() + 3));
                        progressElement(prevState, nextState, Card.SUB_EARTH, 3);
                    } else if (action.getElement() == Card.SUB_AIR) {
                        //nextState.setAirPiecePos(Math.min(12, prevState.getAirPiecePos() + 3));
                        progressElement(prevState, nextState, Card.SUB_AIR, 3);
                    }
                    // elpis: roba 3 cartas
                }
                if (action.getCard1().getValue() == Card.ACTION_GOD_ELPIS) {
                    nextState.setHandCardsCount(prevState.getHandCardsCount() + 3);
                }

                // descartamos 1 carta porque la hemos jugado
                nextState.setHandCardsCount(nextState.getHandCardsCount() - 1);

            }


        } else if (action.getActionType() == MarkovAction.ACTION_DO_NOTHING) {
            // no cambia el estado
        } else if (action.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {
            nextState.setHandCardsCount(prevState.getHandCardsCount()-3+1);
        } else if (action.getActionType() == MarkovAction.ACTION_NATURAL_DISASTER) {
            // TODO: implementar desastres naturales
        }

        return nextState;
    }


    // realiza el avance en un elemento
    // además si cae en casilla 6 y enemigo entre 0 y 6, le hace retroceder a cero
    // y si cae en 6 y el enemigo entre 6 y 11, baja a la 6
    // Las defensas aqui no se contemplan (no se conocen), se conoceran al emprender la accion definitiva
    private static void progressElement(MarkovState prevState, MarkovState nextState, int element, int progress) {
        if (element == Card.SUB_FIRE) {
            nextState.setFirePiecePos(Math.min(12, nextState.getFirePiecePos() + progress));
            if (nextState.getFirePiecePos() == 6 && nextState.getEnemyFirePiecePos() < 6) {
                nextState.setEnemyFirePiecePos(0);
            }
            if (nextState.getFirePiecePos() == 6 && nextState.getEnemyFirePiecePos() > 6 && nextState.getEnemyFirePiecePos() < 12) {
                nextState.setEnemyFirePiecePos(6);
            }
        } else if (element == Card.SUB_WATER) {
            nextState.setWaterPiecePos(Math.min(12, nextState.getWaterPiecePos() + progress));
            if (nextState.getWaterPiecePos() == 6 && nextState.getEnemyWaterPiecePos() < 6) {
                nextState.setEnemyWaterPiecePos(0);
            }
            if (nextState.getWaterPiecePos() == 6 && nextState.getEnemyWaterPiecePos() > 6 && nextState.getEnemyWaterPiecePos() < 12) {
                nextState.setEnemyWaterPiecePos(6);
            }
        } else if (element == Card.SUB_EARTH) {
            nextState.setEarthPiecePos(Math.min(12, nextState.getEarthPiecePos() + progress));
            if (nextState.getEarthPiecePos() == 6 && nextState.getEnemyEarthPiecePos() < 6) {
                nextState.setEnemyEarthPiecePos(0);
            }
            if (nextState.getEarthPiecePos() == 6 && nextState.getEnemyEarthPiecePos() > 6 && nextState.getEnemyEarthPiecePos() < 12) {
                nextState.setEnemyEarthPiecePos(6);
            }
        } else if (element == Card.SUB_AIR) {
            nextState.setAirPiecePos(Math.min(12, nextState.getAirPiecePos() + progress));
            if (nextState.getAirPiecePos() == 6 && nextState.getEnemyAirPiecePos() < 6) {
                nextState.setEnemyAirPiecePos(0);
            }
            if (nextState.getAirPiecePos() == 6 && nextState.getEnemyAirPiecePos() > 6 && nextState.getEnemyAirPiecePos() < 12) {
                nextState.setEnemyAirPiecePos(6);
            }
        }
    }



    // el reward es la diferencia entre la puntuacion de estados
    private static void calcReward(MarkovState prevState, MarkovAction action, MarkovState nextState) {
        action.setReward(nextState.calcState() - prevState.calcState());
    }

    // devuelve la mejor accion que ha podido encontrar conforme a valor/recompensa
    public static MarkovAction findBestAction(Game game, Player player) {
        MarkovAction action = calcActionValue(game, player, null, 0);
        //System.out.println("OPTIMUS ACTION:" + action.toString());
        return action;
    }

    // funcion recursiva que calcula el valor
    private static MarkovAction calcActionValue(Game game, Player player, MarkovState initStatus, int depth) {


        boolean inicial = (initStatus == null);

        MarkovState prevStatus;
        if (initStatus == null) {
            prevStatus = getInitState(game, player);
        } else {
            prevStatus = initStatus;
        }



        /*if (inicial) {
            for (Card card : prevStatus.getHandCards()) {
                System.out.println("  Carta:" + card.getName());
            }
        }*/

        // Profundidad del algoritmo. Si acumula demasiadas cartas en mano, la profundidad evita exceso de computacion
        if (prevStatus.getHandCards().isEmpty() || depth >= 2) {
            return null;
        }

        // Obtener la lista de posibles acciones a emprender
        List<MarkovAction> actionList = getActionList(player, prevStatus);

        // ademas, las vamos a desordenar para que no sigan el mismo orden en que se han generado y sea mas aleatorio
        Collections.shuffle(actionList, new Random());

        // Proceso recursivo de calculo de rewards y valor de cada accion
        MarkovAction optimusAction = null;
        for (MarkovAction action : actionList) {
            MarkovState nextState = getNextState(game, player, prevStatus, action);
            calcReward(prevStatus, action, nextState);

            if (action.getActionType() == MarkovAction.ACTION_DO_NOTHING) {
                //action.setValue(0);
                action.setValue(action.getReward());
            } else {
                MarkovAction bestSubAction = calcActionValue(game, player, nextState, depth + 1);
                if (bestSubAction == null) {
                    action.setValue(action.getReward());
                } else {
                    //action.setValue(calcPolicy(game, player,prevStatus,bestSubAction, inicial)+
                    //        DISCOUNT_FACTOR*bestSubAction.getValue());
                    action.setValue(action.getReward() +
                            DISCOUNT_FACTOR * bestSubAction.getValue());
                    //action.setValue(action.getReward());
                }

            }


        }

        // una vez calculados los rewards y value, pasamos por la politica
        if (inicial) {
            for (MarkovAction markovAction : actionList) {
                markovAction.setValue(markovAction.getValue() + calcPolicy(game, player, prevStatus, markovAction));
                //System.out.println("  Posible accion:" + markovAction.toString() );
            }
        }
        // y entonces elegimos la accion optima
        for (MarkovAction action : actionList) {
            if (optimusAction == null) {
                optimusAction = action;
            } else {
                if (action.getValue() > optimusAction.getValue()) {
                    optimusAction = action;
                }
            }
        }

        //System.out.println("accion elegida "+optimusAction.toString());

        return optimusAction;
    }



    /*
    La politica impone una serie de condiciones que guien la decision.
    En ocasiones solo con el calculo del valor de tomar una accion y su recompensa, no nos conduce a
    tomar la decision mas logica que tomaria un humano.
    Con el calculo de la politica se corrigen determinadas decisiones en determinadas situaciones.
    Un ejemplo claro: Si el jugador esta ya con todas las fichas en la casilla final, no descartarse ninguna de
    las cartas que requiere para ganar. (por mucho que el valor/recompensa sean positivos, seria mejor por ejemplo
    no hacer nada)
     */
    private static double calcPolicy(Game game, Player player, MarkovState prevState, MarkovAction markovAction) {

        double dontPerformActionValue = -5000;
        double notAdviced = -3;//-1
        double adviced = 3; //4
        double notInterferedValue = 0;
        double performActionValue = 5000;


        // APRENDIZAJE !!!
        // qué acciones son interesantes?
        // 1- política de NO usar elementales como avance nunca .. y de esta forma, tenerlos en mano para defenderse
        // 2- anteponer siempre el avance (si se puede avanzar)
        // 3- anteponer siempre los dioses de acción
        // 4- usar los dioses de acción solo para descartarlos y robar cartas adicionales (3x1)
        if (player.getMode()==Player.MODE_GENERATE_DATA){

            // 1- política de NO usar elementales como avance nunca .. y de esta forma, tenerlos en mano para defenderse
            if (player.getLearningAction()==Player.LEARNING_ACTION_1 && markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL) {
                    // Add experience and return
                    player.getExperiences().add(generateExperience(game, player, prevState, markovAction));
                    return dontPerformActionValue;
                }
            }
            // 2- no usar cartas representantes de elemento, guardarlas
            if (player.getLearningAction()==Player.LEARNING_ACTION_2 && markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                if (ElementosEngine.getPlayerPhase(player)<=3) {
                    if (ElementosEngine.getElementRepresentation(player.getHandCards(), markovAction.getCard1().getSubtype())==markovAction.getCard1()) {
                        //if (!ElementosEngine.hasCard(player.getHandCards(), CardEnum.ETHER)) {
                            // Add experience and return
                            player.getExperiences().add(generateExperience(game, player, prevState, markovAction));
                            return dontPerformActionValue;
                        //}
                    }
                }
            }
            // 2- anteponer siempre el avance (si se puede avanzar), SIN INCLUIR avance por elemental, y verifica que reward > 0 y si no es carta
            // representante de elemento
            /*if (player.getLearningAction()==Player.LEARNING_ACTION_2 && markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                if (markovAction.getCard1().getType() == Card.TYPE_FORWARD || markovAction.getCard1().getType() == Card.TYPE_SPECIAL_FORWARD) {
                    if (markovAction.getReward()>0 && ElementosEngine.getElementRepresentation(player.getHandCards(), markovAction.getCard1().getSubtype())!=null) {
                        // Add experience and return
                        player.getExperiences().add(generateExperience(game, player, prevState, markovAction));
                        return performActionValue;
                    }
                }
            }*/
            // 3- no usar dioses de accion mas que para 3x1
            if (player.getLearningAction()==Player.LEARNING_ACTION_3 && markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD ) {
                if (markovAction.getCard1().getType() == Card.TYPE_ACTION_GOD) {

                        // Add experience and return
                        player.getExperiences().add(generateExperience(game, player, prevState, markovAction));
                        return dontPerformActionValue;

                }
            }

            // 4- priorizar el 3x1
            if (player.getLearningAction()==Player.LEARNING_ACTION_4 && markovAction.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {

                    // Add experience and return
                    player.getExperiences().add(generateExperience(game, player, prevState, markovAction));
                    return performActionValue;

            }


        }


        // MODO LEARNING, aplicar lo aprendido
        if (player.getMode()==Player.MODE_LEARNING_POLICY){
            // 4- priorizar el 3x1
            if (markovAction.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {
                // Add experience and return
                return performActionValue;

            }
        }


        // si tiene el eter o algun dios, no le interesa intercambiar cartas con rival (APATE)
        if (markovAction.getActionType()==MarkovAction.ACTION_PLAY_CARD && markovAction.getCard1().getType()==Card.TYPE_ACTION_GOD &&
                markovAction.getCard1().getValue()==Card.ACTION_GOD_APATE){
            if (ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.ETHER)){
                return dontPerformActionValue;
            }
            if (ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.GOD_AIR)||ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.GOD_EARTH) ||
                    ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.GOD_FIRE)||ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.GOD_WATER)||
                    ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.ELEM_ETHER)){
                return notAdviced;
            }
        }

        // si elige boreas, le aumenta el peso al elemento menos desarrollado del enemigo
        // para que no lo avance en 2 turnos
        if (markovAction.getActionType()==MarkovAction.ACTION_PLAY_CARD
                && markovAction.getCard1().getType()==Card.TYPE_ACTION_GOD &&
                markovAction.getCard1().getValue()==Card.ACTION_GOD_BOREAS){
            Player enemy = game.getEnemy();
            if (ElementosEngine.getLessProgressedElement(enemy)==markovAction.getElement()){
                return adviced;
            } else {
                return notInterferedValue;
            }
        }




        // early stage
        if (prevState.calcOwnState() < 24) {

            // no desechar 2 cartas para poder robar 1
            if (markovAction.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {
                //return dontPerformActionValue;
            }
            // el elemental del ether, no descartarlo
            if (markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                // los dioses no se juegan solo se mantienen en la mano
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL_GOD) {
                    return dontPerformActionValue;
                }
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL && markovAction.getCard1().getSubtype() == Card.SUB_ETHER) {
                    //return dontPerformActionValue;
                }
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL) {
                    //return notAdviced;
                }
            }


            // final stage
        } else if (prevState.isFinalStage()) {

            // No descartar cartas que sirvan para ganar: elementales, dioses, avance especial
            if (markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                // los dioses no se juegan solo se mantienen en la mano
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL_GOD) {
                    return dontPerformActionValue;
                }
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL && markovAction.getCard1().getSubtype() == Card.SUB_ETHER) {
                    //return dontPerformActionValue;
                }

                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL) {
                    //return dontPerformActionValue;
                }
                if (markovAction.getCard1().getType() == Card.TYPE_SPECIAL_FORWARD) {
                    //return dontPerformActionValue;
                }

                if (ElementosEngine.getElementRepresentation(player.getHandCards(), markovAction.getCard1().getSubtype())==markovAction.getCard1()){
                    return dontPerformActionValue;
                }

            }

            // desechar 3 cartas para poder robar 1
            if (markovAction.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {
                //return adviced;
                return notAdviced;
            }


            // mid-advanced stage
        } else {

            // no usar elementales para avanzar, interesa ir reservandolos para el final
            // excepto que se tenga el eter
            if (markovAction.getActionType() == MarkovAction.ACTION_PLAY_CARD) {
                // los dioses no se juegan solo se mantienen en la mano
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL_GOD) {
                    return dontPerformActionValue;
                }
                if (!ElementosEngine.hasCard(prevState.getHandCards(), CardEnum.ETHER)) {
                    if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL) {
                        //return dontPerformActionValue;
                    }
                }
                // en cualquier caso, el elemental del ether no descartarlo para hacer avance
                if (markovAction.getCard1().getType() == Card.TYPE_ELEMENTAL && markovAction.getCard1().getSubtype() == Card.SUB_ETHER) {
                    //return dontPerformActionValue;
                }
            }

            // no desechar 3 cartas para poder robar 1
            if (markovAction.getActionType() == MarkovAction.ACTION_DISCARD_3_DRAW_1) {
                //return dontPerformActionValue;
            }
        }


        return notInterferedValue;
    }


    private static Experience generateExperience ( Game game, Player player, MarkovState prevState, MarkovAction markovAction){
        Experience experience = new Experience();
        experience.setLearningAction(player.getLearningAction());
        experience.setOwnPhase(ElementosEngine.getPlayerPhase(player));
        experience.setEnemyPhase(ElementosEngine.getPlayerPhase(game.getEnemy()));
        experience.setReward(markovAction.getReward());
        experience.setValue(markovAction.getValue());
        experience.setVictory(-1);
        return experience;
    }




}
