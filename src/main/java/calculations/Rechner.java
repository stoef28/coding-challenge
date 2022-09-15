package calculations;

import domain.House;
import domain.Storage;

import java.util.*;

public class Rechner {

    Map<Integer, List<PotentialAction>> potentialActionsPerYear = new HashMap<>();
    public List<PotentialAction> findStrat(List<House> houses) {
        houses.forEach(this::findMaxValue);

        List<PotentialAction> finalActions = new ArrayList<>();
        int maxYear = Collections.max(this.potentialActionsPerYear.keySet());
        for(int year = 0; year < maxYear; year++) {
            final int currentYear = year;
            System.out.println("current year = " + currentYear);
            System.out.println("KAPITAL vor Verkauf: " + Storage.KAPITAL);
            finalActions.stream()
                    .filter(a -> a.getVerkaufsJahr() == currentYear)
                    .forEach(a -> Storage.KAPITAL += a.getPreis() + a.getGewinn());
            System.out.println("KAPITAL nach Verkauf: " + Storage.KAPITAL);

            List<PotentialAction> actionList = this.potentialActionsPerYear.get(year); // null possible
            if(actionList != null) {
                for(PotentialAction action : actionList) {
                    System.out.println("current action: " + action);
                    if(Storage.KAPITAL >= action.getPreis() && notAlreadyBought(finalActions, action)) {
                        System.out.println(action + " added to final list!");
                        finalActions.add(action);
                        Storage.KAPITAL -= action.getPreis();
                    }

                }
            }

        }
        System.out.println("Bought Actions: " + finalActions);
        return finalActions;
    }

    private boolean notAlreadyBought(List<PotentialAction> finalActions, PotentialAction action) {
        return finalActions.stream()
                .noneMatch(
                        fixedAction -> fixedAction.getId().equals(action.getId()) && fixedAction.getVerkaufsJahr() > action.getKaufJahr());
    }

    private void findMaxValue(House house) {
        System.out.println("get max for house " + house.getId());
        List<Double> werte = house.getWerte();

//        double max = 0.0;
//        int start = -1;
//        int end = -1;
        for(int i = 0; i< werte.size() -1; i++) {
            for(int j = i+1; j< werte.size() ; j++) {
                double current = werte.get(j) - werte.get(i);
                if(current > 0) {
                    int start = i + house.getBaujahr();
                    int end = j + house.getBaujahr();
                    System.out.println("House " + house.getId() + " new max found: " + current + " from " + start + " to " + end);

                    PotentialAction action = new PotentialAction(house.getId(), werte.get(start - house.getBaujahr()), start, end, 0, current);

                    this.potentialActionsPerYear.computeIfAbsent(start, ArrayList::new);
                    List<PotentialAction> potActions = this.potentialActionsPerYear.get(start);
                    potActions.add(action);
                    potActions.sort(Comparator.comparingDouble(PotentialAction::getGewinn)
                                    .thenComparingDouble(PotentialAction::getPreis));
                    System.out.println("potActions for year " + start + ": " + potActions);
                    this.potentialActionsPerYear.replace(start, potActions);
                }
            }

        }


    }
}
