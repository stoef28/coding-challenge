package domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Action {
    private ActionEnum action;
    private String id;
    private int year;
    private int hypo;


    public Action(ActionEnum action, String id, int year, int hypo) {
        this.action = action;
        this.id = id;
        this.year = year;
        this.hypo = hypo;
    }

    public Action(ActionEnum action, String id, int year) {
        this.action = action;
        this.id = id;
        this.year = year;
    }
}
