package pl.edu.agh.demo1;

import java.util.Date;

/**
 * Created by bwolcerz on 27.11.2017.
 */

public class Item {
    private String description;
    public Item() {
    }

    public Item(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
