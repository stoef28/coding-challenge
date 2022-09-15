package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@Builder
public class House {
    private String id;
    private int baujahr;
    private boolean renditeObjekt;
    private List<Double> werte;
}
