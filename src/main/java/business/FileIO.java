package business;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by pabloestradaleon on 12/06/2021.
 */
public class FileIO {

    public static final String fileName = "learning.txt";


    public static void addExperience(Experience experience){
        String toWrite = "";

        toWrite += experience.getLearningAction()+";";
        toWrite += experience.getOwnPhase()+";";
        toWrite += experience.getEnemyPhase()+";";
        toWrite += experience.getReward()+";";
        toWrite += experience.getValue()+";";
        toWrite += experience.getVictory()+";";

        appendToLearningFile(toWrite);
    }


    private static void appendToLearningFile(String line){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(line+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static List<Experience> readFile(){
        List<Experience> retVal = new ArrayList<>();
        try{
            File file = new File((fileName));
            InputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                //resultStringBuilder.append(line).append("\n");
                Experience exp = parseExperience(line);
                retVal.add(exp);
            }
            return retVal;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static Experience parseExperience(String line) {
        Experience experience = new Experience();
        StringTokenizer st = new StringTokenizer(line, ";");
        experience.setLearningAction(Integer.parseInt(st.nextToken()));
        experience.setOwnPhase(Integer.parseInt(st.nextToken()));
        experience.setEnemyPhase(Integer.parseInt(st.nextToken()));
        experience.setReward(Double.parseDouble(st.nextToken()));
        experience.setValue(Double.parseDouble(st.nextToken()));
        experience.setVictory(Integer.parseInt(st.nextToken()));
        return experience;
    }


}
