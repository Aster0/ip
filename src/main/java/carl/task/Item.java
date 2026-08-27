package carl.task;

public class Item {

    private final String name;

    public Item (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public boolean hasNameMatch(String keyword) {
        return this.name.toLowerCase().contains(keyword.toLowerCase());
    }
}
