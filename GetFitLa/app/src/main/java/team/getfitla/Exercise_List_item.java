package team.getfitla;

/**
 * Created by Work on 2/23/18.
 */

public class Exercise_List_item {
    private String head;
    private String description;

    public Exercise_List_item(String head, String description) {
        this.head = head;
        this.description = description;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }
}
