import domain.House;
import file.InputReader;

import java.io.IOException;
import java.util.List;

public class MainRunner {

    static List<String> filenames = List.of("inputA.txt");

    public static void main(String[] args) throws IOException {

        for (String filename : MainRunner.filenames){
            InputReader reader = new InputReader();
            List<House> read = reader.read(filename);
            System.out.println(read);
        }


    }
}
