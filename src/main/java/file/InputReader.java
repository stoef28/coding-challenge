package file;

import domain.House;
import domain.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {


    public List<House> read(String filename) throws IOException {
        List<String> lines = readLines(filename);
        Storage.KAPITAL = Double.parseDouble(lines.get(0));
        lines.remove(0);
        List<House> houses = lines.stream().map(this::buildHouse).collect(Collectors.toList());
        return houses;
    }

    private House buildHouse(String string) {
        String[] split = string.split(";");
        String[] values = split[3].split(",");
        List<Double> valuesDouble = Arrays.stream(values).map(Double::valueOf).collect(Collectors.toList());
        return House.builder().id(split[0]).baujahr(Integer.parseInt(split[1])).renditeObjekt((split[2].equals("1"))).werte(valuesDouble).build();
    }


    public List<String> readLines(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }

}
