package views;

import business.ElementosEngine;
import business.MarkovLogic;
import model.*;
import model.enums.CardEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pabloestradaleon on 17/04/2021.
 */
public class PlayerVsMachineForm {

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlayerVsMachineForm");
        frame.setContentPane(new PlayerVsMachineForm().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    // GAME
    private Game game;
    private List<String> messages = new ArrayList<>();

    // SWING
    private Component imageToView;
    private JComboBox comboBoxCartasMano;
    private JButton jugarCartaButton;
    private JButton pasarTurnoButton;
    private JButton desastreNaturalButton;
    private JButton descartar3CartasRobarButton;

    private JProgressBar progressFire;
    private JProgressBar progressWater;
    private JProgressBar progressEarth;
    private JProgressBar progressAir;
    private JProgressBar progressEnemyFire;
    private JProgressBar progressEnemyWater;
    private JProgressBar progressEnemyEarth;
    private JProgressBar progressEnemyAir;
    private JButton reiniciarPartidaButton;
    private JScrollBar scrollBar1;
    private JTextArea textArea1;
    private JPanel MainPanel;
    private JButton continuarButton;
    private JTextArea textArea2;
    private JPanel imageCardPanel;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MainPanel = new JPanel();

        comboBoxCartasMano = new JComboBox();
        jugarCartaButton = new JButton();
        pasarTurnoButton = new JButton();
        desastreNaturalButton = new JButton();
        descartar3CartasRobarButton = new JButton();
        progressFire = new JProgressBar();
        progressWater = new JProgressBar();
        progressEarth = new JProgressBar();
        progressAir = new JProgressBar();
        progressEnemyFire = new JProgressBar();
        progressEnemyWater = new JProgressBar();
        progressEnemyEarth = new JProgressBar();
        progressEnemyAir = new JProgressBar();
        reiniciarPartidaButton = new JButton();
        scrollBar1 = new JScrollBar();
        textArea1 = new JTextArea();
        continuarButton = new JButton();
        textArea2 = new JTextArea();
        imageCardPanel = new JPanel();
        imageCardPanel.setLayout(new BorderLayout());


        MainPanel.add(comboBoxCartasMano);
        MainPanel.add(jugarCartaButton);
        MainPanel.add(pasarTurnoButton);
        MainPanel.add(desastreNaturalButton);
        MainPanel.add(descartar3CartasRobarButton);
        MainPanel.add(progressFire);
        MainPanel.add(progressWater);
        MainPanel.add(progressEarth);
        MainPanel.add(progressAir);
        MainPanel.add(progressEnemyFire);
        MainPanel.add(progressEnemyWater);
        MainPanel.add(progressEnemyEarth);
        MainPanel.add(progressEnemyAir);
        MainPanel.add(reiniciarPartidaButton);
        MainPanel.add(scrollBar1);
        MainPanel.add(textArea1);
        MainPanel.add(continuarButton);
        MainPanel.add(textArea2);
        MainPanel.add(imageCardPanel);

        progressFire.setMaximum(12);
        progressFire.setStringPainted(true);
        progressWater.setMaximum(12);
        progressWater.setStringPainted(true);
        progressEarth.setMaximum(12);
        progressEarth.setStringPainted(true);
        progressAir.setMaximum(12);
        progressAir.setStringPainted(true);

        progressEnemyFire.setMaximum(12);
        progressEnemyFire.setStringPainted(true);
        progressEnemyWater.setMaximum(12);
        progressEnemyWater.setStringPainted(true);
        progressEnemyEarth.setMaximum(12);
        progressEnemyEarth.setStringPainted(true);
        progressEnemyAir.setMaximum(12);
        progressEnemyAir.setStringPainted(true);

        jugarCartaButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                humanPlayCard();
            }
        });
        pasarTurnoButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skipTurn();
            }
        });

        // game things
        this.game = ElementosEngine.startGame(ElementosEngine.MODE_HUMAN_VS_MARKOV);
        messages = new ArrayList<>();
        addInfoText("Bienvenido a Elementos. ¡Es tu turno!");
        updateGui();

    }




    private Player getHumanPlayer(){
        return ElementosEngine.getPlayerByTurn(game,1);
    }
    private Player getMachinePlayer(){
        return ElementosEngine.getPlayerByTurn(game,2);
    }

    private void updateGui() {

        Player humanPlayer = getHumanPlayer();
        progressFire.setValue(humanPlayer.getFirePiecePos());
        progressFire.setString(Integer.toString(humanPlayer.getFirePiecePos()));
        progressWater.setValue(humanPlayer.getWaterPiecePos());
        progressWater.setString(Integer.toString(humanPlayer.getWaterPiecePos()));
        progressEarth.setValue(humanPlayer.getEarthPiecePos());
        progressEarth.setString(Integer.toString(humanPlayer.getEarthPiecePos()));
        progressAir.setValue(humanPlayer.getAirPiecePos());
        progressAir.setString(Integer.toString(humanPlayer.getAirPiecePos()));


        comboBoxCartasMano.removeAllItems();
        comboBoxCartasMano.setModel(new DefaultComboBoxModel() {
            @Override
            public int getSize() {
                return humanPlayer.getHandCards().size();
            }

            @Override
            public Object getElementAt(int index) {
                return humanPlayer.getHandCards().get(index);
            }
        });
        if (!humanPlayer.getHandCards().isEmpty())
        comboBoxCartasMano.setSelectedIndex(0);

        // que cuando cambie la seleccion del combo, se muestre texto de carta en el textarea2
        comboBoxCartasMano.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card card = (Card) comboBoxCartasMano.getSelectedItem();
                if (card!=null){
                    textArea2.setText(card.getName()+" - "+card.getDescription());

                    if (imageToView!=null)
                    imageCardPanel.remove(imageToView);
                    //imageCardPanel.add(new ImagePanel("/home/pablo/DEV/ELEMENTOS/CARTAS/JPG/adicia.jpg"));
                    try {
                        imageToView = new ImagePanel(card.getUrlImage());
                        imageCardPanel.add(imageToView, BorderLayout.CENTER);
                        //imageToView.setSize(50,90);
                        imageToView.validate();
                        imageToView.repaint();
                        imageCardPanel.validate();
                        imageCardPanel.repaint();

                        //JOptionPane.showMessageDialog(null, imageToView);
                    } catch (Exception exc){
                        exc.printStackTrace();
                    }

                }
            }
        });

        Player machinePlayer = getMachinePlayer();
        progressEnemyFire.setValue(machinePlayer.getFirePiecePos());
        progressEnemyFire.setString(Integer.toString(machinePlayer.getFirePiecePos()));
        progressEnemyWater.setValue(machinePlayer.getWaterPiecePos());
        progressEnemyWater.setString(Integer.toString(machinePlayer.getWaterPiecePos()));
        progressEnemyEarth.setValue(machinePlayer.getEarthPiecePos());
        progressEnemyEarth.setString(Integer.toString(machinePlayer.getEarthPiecePos()));
        progressEnemyAir.setValue(machinePlayer.getAirPiecePos());
        progressEnemyAir.setString(Integer.toString(machinePlayer.getAirPiecePos()));




    }



    private void addInfoText(String text){
        if (messages.size()<5){
            messages.add(text);
        } else {
            messages.remove(0);
            messages.add(text);
        }

        textArea1.setText("");
        for(int i=messages.size()-1;i>=0;i--){
            String spacing = "";
            for(int m=0;m<i;m++){
                spacing+="   ";
            }
            textArea1.setText(textArea1.getText()+"\n"+spacing+messages.get(i));
        }
    }


    private void beginHumanTurn(){
        game.setActivePlayer(getHumanPlayer());
        // robar
        if (game.getAge()>1 && getHumanPlayer().isCanDrawNextTurn()){
            Card card = ElementosEngine.playerDrawCard(game.getBoard(), getHumanPlayer());
            addInfoText("Has robado la carta "+card.getName()+" - "+card.getDescription());
        }

        // mantenimiento inicial despues de robar
        ElementosEngine.maintenanceAtTurnStarting(game, getHumanPlayer());

        // verificar victoria
        if (ElementosEngine.canWin(getHumanPlayer())){
            addInfoText("¡Reclama la victoria! ¡has ganado!");
            JOptionPane.showMessageDialog(null,"¡Reclama la victoria! ¡has ganado!");
        }
        updateGui();
    }

    private void skipTurn() {
        addInfoText("Pasas turno ");
        MarkovAction markovAction = new MarkovAction(MarkovAction.ACTION_DO_NOTHING, null);
        System.out.println("[HUMANO] ACCION: " + markovAction.toString());
        if (markovAction.getCard1()!=null){
            CardEnum cardEnum = ElementosEngine.getCardEnumByTypes(markovAction.getCard1().getType(),
                    markovAction.getCard1().getSubtype(), markovAction.getCard1().getValue());
            if (cardEnum!=null){
                System.out.println("   "+cardEnum.getName()+" - "+cardEnum.getDescription());
            }
        }
        ElementosEngine.playCard(game, getHumanPlayer(),markovAction);
        MarkovState humanState = MarkovLogic.getInitState(game, getHumanPlayer());
        MarkovState iaState = MarkovLogic.getInitState(game, getMachinePlayer());
        System.out.println("   Estado resultante HUMANO: "+humanState.toString());
        System.out.println("   Estado resultante IA: "+iaState.toString());
        endHumanTurn();
    }

    private void humanPlayCard(){
        Card card = (Card) comboBoxCartasMano.getSelectedItem();

        // verificar si esta permitido jugarla
        if (card!=null){
            // por boreas, puede estar prohibido avanzar en un elemento
            if (!getHumanPlayer().isProgressAllowed()) {
                if ((card.getType() == Card.TYPE_FORWARD || card.getType() == Card.TYPE_SPECIAL_FORWARD ||
                        card.getType() == Card.TYPE_ELEMENTAL) && card.getSubtype() == getHumanPlayer().getElementProgressBanned()) {
                    JOptionPane.showMessageDialog(null,"Accion no permitida");
                    return;
                }
            }
        }



        if (card!=null){
            addInfoText("Juegas la carta "+card.getName() + " - "+card.getDescription());
            MarkovAction markovAction = new MarkovAction(MarkovAction.ACTION_PLAY_CARD, card);

            // preguntar elemento si es que procede
            if ((card.getType()==Card.TYPE_FORWARD && card.getSubtype()==Card.SUB_ETHER) ||
                (card.getType()==Card.TYPE_ELEMENTAL && card.getSubtype()==Card.SUB_ETHER) ||
                    (card.getType()==Card.TYPE_ACTION_GOD && card.getValue()==Card.ACTION_GOD_ADICIA) ||
                (card.getType()==Card.TYPE_ACTION_GOD && card.getValue()==Card.ACTION_GOD_ARTEMISA) ||
                    (card.getType()==Card.TYPE_ACTION_GOD && card.getValue()==Card.ACTION_GOD_BOREAS) ||
                (card.getType()==Card.TYPE_ACTION_GOD && card.getValue()==Card.ACTION_GOD_HADES) ||
                    (card.getType()==Card.TYPE_ACTION_GOD && card.getValue()==Card.ACTION_GOD_HERMES) ||
                    (card.getType()==Card.TYPE_ACTION_GOD && card.getValue()==Card.ACTION_GOD_TIQUE)){
                int element = -1;
                Object[] possibilities = {"Fuego", "Agua", "Tierra","Aire"};
                String s = (String)JOptionPane.showInputDialog(
                        null,
                        "¿En que elemento deseas realizar la accion?:\n",
                        "Elegir elemento",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        "Fuego");

                //If a string was returned, say so.
                if ((s != null) && (s.length() > 0)) {
                    if ("Fuego".equals(s)){
                        element = Card.SUB_FIRE;
                    } else if ("Agua".equals(s)){
                        element = Card.SUB_WATER;
                    } else if ("Tierra".equals(s)){
                        element = Card.SUB_EARTH;
                    } else {
                        element = Card.SUB_AIR;
                    }
                }
                markovAction.setElement(element);
            }

            System.out.println("[HUMANO] ACCION: " + markovAction.toString());
            if (markovAction.getCard1()!=null){
                CardEnum cardEnum = ElementosEngine.getCardEnumByTypes(markovAction.getCard1().getType(),
                        markovAction.getCard1().getSubtype(), markovAction.getCard1().getValue());
                if (cardEnum!=null){
                    System.out.println("   "+cardEnum.getName()+" - "+cardEnum.getDescription());
                }
            }
            ElementosEngine.playCard(game, getHumanPlayer(),markovAction);
            MarkovState humanState = MarkovLogic.getInitState(game, getHumanPlayer());
            MarkovState iaState = MarkovLogic.getInitState(game, getMachinePlayer());
            System.out.println("   Estado resultante HUMANO: "+humanState.toString());
            System.out.println("   Estado resultante IA: "+iaState.toString());

            endHumanTurn();
        }
    }

    private void endHumanTurn(){
        game.setTurn(game.getTurn()+1);
        performMachineTurn();
    }

    private void performMachineTurn() {
        game.setActivePlayer(getMachinePlayer());

        // robar
        if (game.getAge()>1 && getMachinePlayer().isCanDrawNextTurn()){
            ElementosEngine.playerDrawCard(game.getBoard(), getMachinePlayer());
        }

        // mantenimiento inicial despues de robar
        ElementosEngine.maintenanceAtTurnStarting(game, getMachinePlayer());

        System.out.println("Cartas en mano de la IA");
        for (Card card: getMachinePlayer().getHandCards()){
            System.out.println("   CARTA:"+card.getName()+" "+card.getDescription());
        }



        // verificar victoria de la IA
        if (ElementosEngine.canWin(getMachinePlayer())){
            addInfoText("¡Tu rival consigue victoria! ¡has perdido!");
            JOptionPane.showMessageDialog(null,"¡Tu rival consigue victoria! ¡has perdido!");
        }

        // decision de accion
        MarkovAction action = MarkovLogic.findBestAction(game, getMachinePlayer());
        if (action.getActionType()==MarkovAction.ACTION_PLAY_CARD){
            addInfoText("Tu rival juega la carta "+ action.getCard1().getName()+" - "+action.getCard1().getDescription());
            ElementosEngine.playCard(game, getMachinePlayer(),action);

        }

        // imprimir resultados
        System.out.println("[IA] ACCION: " + action.toString());
        if (action.getCard1()!=null){
            CardEnum cardEnum = ElementosEngine.getCardEnumByTypes(action.getCard1().getType(),
                    action.getCard1().getSubtype(), action.getCard1().getValue());
            if (cardEnum!=null){
                System.out.println("   "+cardEnum.getName()+" - "+cardEnum.getDescription());
            }
        }


        MarkovState humanState = MarkovLogic.getInitState(game, getHumanPlayer());
        MarkovState iaState = MarkovLogic.getInitState(game, getMachinePlayer());
        System.out.println("   Estado resultante HUMANO: "+humanState.toString());
        System.out.println("   Estado resultante IA: "+iaState.toString());



        game.setTurn(game.getTurn()+1);
        game.setAge(game.getAge()+1);

        beginHumanTurn();

    }

}
