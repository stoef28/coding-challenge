import calculations.PotentialAction;
import calculations.Rechner;
import domain.Action;
import domain.ActionEnum;
import domain.House;
import file.InputReader;
import file.OutputWriter;

import java.io.IOException;
import java.util.ArrayList;
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

            Rechner rechner = new Rechner();
            List<PotentialAction> finalActions = rechner.findStrat(read);
            Map<Integer, List<Action>> actions = new HashMap<>();
            finalActions
                    .forEach(potentialAction -> {
                       Action kauf = new Action(ActionEnum.KAUFE, potentialAction.getId(), potentialAction.getKaufJahr(), potentialAction.getHypo());
                       actions.computeIfAbsent(potentialAction.getKaufJahr(), ArrayList::new);
                        List<Action> kaufActionList = actions.get(potentialAction.getKaufJahr());
                        kaufActionList.add(kauf);
                        actions.replace(potentialAction.getKaufJahr(), kaufActionList);

                        Action verkauf = new Action(ActionEnum.VERKAUFE, potentialAction.getId(), potentialAction.getVerkaufsJahr(), potentialAction.getHypo());
                        actions.computeIfAbsent(potentialAction.getVerkaufsJahr(), ArrayList::new);
                        List<Action> verkaufActionList = actions.get(potentialAction.getVerkaufsJahr());
                        verkaufActionList.add(verkauf);
                        actions.replace(potentialAction.getVerkaufsJahr(), verkaufActionList);
                    });


//            actions.put(3, List.of(new Action(ActionEnum.KAUFE, "baumstrasse", 3, 50)));
//            actions.put(2, List.of(new Action(ActionEnum.VERKAUFE, "bahnhofstrasse", 3, 50)));


            OutputWriter outputWriter = new OutputWriter();

            outputWriter.writeFile(filename, actions);
        }


    }
}
