package main;

import business.ElementosEngine;
import business.FileIO;
import business.MarkovLogic;
import model.Experience;
import model.Game;
import model.Player;
import views.PlayerVsMachineForm;

import javax.swing.*;
import java.util.List;

public class ElementosMain {


    public static void main (String[] args){

        int modeMarkovVictories = 0;
        int modeLearningVictories = 0;
        for (int i=0;i<3000; i++) {
            Game game = ElementosEngine.startGame(ElementosEngine.MODE_MARKOV_VS_LEARNING);
            ElementosEngine.playMachineVsMachine(game);
            if (game.getEnemy().isVictory() && game.getEnemy().getMode()== Player.MODE_MARKOV){
                modeMarkovVictories++;
            }
            if (game.getEnemy().isVictory() && game.getEnemy().getMode()== Player.MODE_LEARNING_POLICY){
                modeLearningVictories++;
            }
        }
        System.out.println("Markov Victories "+modeMarkovVictories);
        System.out.println("Learning Victories "+modeLearningVictories);

        List<Experience> experienceList = FileIO.readFile();
        System.out.println("Experiencias: " + experienceList.size());

        int sucesos1 = 0;
        int sucesos2 = 0;
        int sucesos3 = 0;
        int sucesos4 = 0;
        int victorias1 = 0;
        int victorias2 = 0;
        int victorias3 = 0;
        int victorias4 = 0;

        int sucesos1_fase1 = 0;
        int sucesos1_fase2 = 0;
        int sucesos1_fase3 = 0;
        int sucesos2_fase1 = 0;
        int sucesos2_fase2 = 0;
        int sucesos2_fase3 = 0;
        int sucesos3_fase1 = 0;
        int sucesos3_fase2 = 0;
        int sucesos3_fase3 = 0;
        int sucesos4_fase1 = 0;
        int sucesos4_fase2 = 0;
        int sucesos4_fase3 = 0;
        int victorias1_fase1 = 0;
        int victorias1_fase2 = 0;
        int victorias1_fase3 = 0;
        int victorias2_fase1 = 0;
        int victorias2_fase2 = 0;
        int victorias2_fase3 = 0;
        int victorias3_fase1 = 0;
        int victorias3_fase2 = 0;
        int victorias3_fase3 = 0;
        int victorias4_fase1 = 0;
        int victorias4_fase2 = 0;
        int victorias4_fase3 = 0;

        for (Experience experience: experienceList){
            if (experience.getLearningAction()==1){
                sucesos1++;
                if (experience.getOwnPhase()==1){
                    sucesos1_fase1++;
                } else if (experience.getOwnPhase()==2){
                    sucesos1_fase2++;
                } else {
                    sucesos1_fase3++;
                }
                if(experience.getVictory()==1){
                    victorias1++;
                    if (experience.getOwnPhase()==1){
                        victorias1_fase1++;
                    } else if (experience.getOwnPhase()==2){
                        victorias1_fase2++;
                    } else {
                        victorias1_fase3++;
                    }
                }
            }
            if (experience.getLearningAction()==2){
                sucesos2++;
                if (experience.getOwnPhase()==1){
                    sucesos2_fase1++;
                } else if (experience.getOwnPhase()==2){
                    sucesos2_fase2++;
                } else {
                    sucesos2_fase3++;
                }
                if(experience.getVictory()==1){
                    victorias2++;
                    if (experience.getOwnPhase()==1){
                        victorias2_fase1++;
                    } else if (experience.getOwnPhase()==2){
                        victorias2_fase2++;
                    } else {
                        victorias2_fase3++;
                    }
                }
            }
            if (experience.getLearningAction()==3){
                sucesos3++;
                if (experience.getOwnPhase()==1){
                    sucesos3_fase1++;
                } else if (experience.getOwnPhase()==2){
                    sucesos3_fase2++;
                } else {
                    sucesos3_fase3++;
                }
                if(experience.getVictory()==1){
                    victorias3++;
                    if (experience.getOwnPhase()==1){
                        victorias3_fase1++;
                    } else if (experience.getOwnPhase()==2){
                        victorias3_fase2++;
                    } else {
                        victorias3_fase3++;
                    }
                }
            }
            if (experience.getLearningAction()==4){
                sucesos4++;
                if (experience.getOwnPhase()==1){
                    sucesos4_fase1++;
                } else if (experience.getOwnPhase()==2){
                    sucesos4_fase2++;
                } else {
                    sucesos4_fase3++;
                }
                if(experience.getVictory()==1){
                    victorias4++;
                    if (experience.getOwnPhase()==1){
                        victorias4_fase1++;
                    } else if (experience.getOwnPhase()==2){
                        victorias4_fase2++;
                    } else {
                        victorias4_fase3++;
                    }
                }
            }
        }


        System.out.println("Sucesos 1="+sucesos1 + " victorias="+victorias1 + " sf1="+sucesos1_fase1 + " vf1="+victorias1_fase1 + " sf2="+sucesos1_fase2 + " vf2="+victorias1_fase2 + " sf3="+sucesos1_fase3 + " vf3="+victorias1_fase3);
        System.out.println("Sucesos 2="+sucesos2 + " victorias="+victorias2 + " sf1="+sucesos2_fase1 + " vf1="+victorias2_fase1 + " sf2="+sucesos2_fase2 + " vf2="+victorias2_fase2 + " sf3="+sucesos2_fase3 + " vf3="+victorias2_fase3);
        System.out.println("Sucesos 3="+sucesos3 + " victorias="+victorias3 + " sf1="+sucesos3_fase1 + " vf1="+victorias3_fase1 + " sf2="+sucesos3_fase2 + " vf2="+victorias3_fase2 + " sf3="+sucesos3_fase3 + " vf3="+victorias3_fase3);
        System.out.println("Sucesos 4="+sucesos4 + " victorias="+victorias4 + " sf1="+sucesos4_fase1 + " vf1="+victorias4_fase1 + " sf2="+sucesos4_fase2 + " vf2="+victorias4_fase2 + " sf3="+sucesos4_fase3 + " vf3="+victorias4_fase3);


    }

}
