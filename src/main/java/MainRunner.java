import domain.Action;
import domain.ActionEnum;
import domain.House;
import file.InputReader;
import file.OutputWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRunner {

    static List<String> filenames = List.of("inputA.txt");

    public static void main(String[] args) throws IOException {

        for (String filename : MainRunner.filenames){
            InputReader reader = new InputReader();
            List<House> read = reader.read(filename);
            System.out.println(read);

            Map<Integer, List<Action>> actions = new HashMap<>();

            actions.put(3, List.of(new Action(ActionEnum.KAUFE, "baumstrasse", 3, 50)));
            actions.put(2, List.of(new Action(ActionEnum.VERKAUFE, "bahnhofstrasse", 3, 50)));


            OutputWriter outputWriter = new OutputWriter();

            outputWriter.writeFile(filename, actions);
        }


    }
}
