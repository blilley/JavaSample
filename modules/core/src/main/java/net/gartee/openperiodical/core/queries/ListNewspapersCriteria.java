package net.gartee.openperiodical.core.queries;

public class ListNewspapersCriteria extends Criteria {
    private String nameContains;

    public ListNewspapersCriteria(String nameContains) {
        this.nameContains = nameContains;
    }

    public String getNameContains() {
        return nameContains;
    }
}
