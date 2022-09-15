package calculations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class PotentialAction {

    private String id;

    private double preis;
    private int kaufJahr;
    private int verkaufsJahr;
    private int hypo;
    private double gewinn;

}
