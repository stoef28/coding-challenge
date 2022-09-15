package file;

import domain.Action;
import domain.ActionEnum;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputWriter {

    private BufferedWriter bufferedWriter;

    public void writeFile(String filename, Map<Integer, List<Action>> actions) throws IOException {
        String taskLetter = filename.substring(filename.length() - 5, filename.length() - 4);
        bufferedWriter = new BufferedWriter(new java.io.FileWriter("output" + taskLetter + ".txt"));

        List<Integer> years = new ArrayList<>(actions.keySet());
        Collections.sort(years);

        for (int year: years) {
            write(String.valueOf(year));
            newLine();
            for (Action action: actions.get(year)){
                if (action.getAction().equals(ActionEnum.KAUFEN)){
                    writeKaufen(action);
                } else {
                    writeGeneralAction(action);
                }
            }
        }
        close();
    }

    private void writeGeneralAction(Action action) {
        write(String.valueOf(action.getAction()));
        write(" ");
        write(action.getId());
        newLine();
    }

    private void writeKaufen(Action action) {
        write(String.valueOf(action.getAction()));
        write(" ");
        write(action.getId());
        write(" ");
        write(String.valueOf(action.getHypo()));
        newLine();
    }


    private void newLine() {
        try {
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void write(String string) {
        try {
            bufferedWriter.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
