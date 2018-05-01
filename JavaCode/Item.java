import java.util.*;

public class Item {
    private String name;
    private int quantity;
    private String  description;
    private int startingPrice;
    private String imagePath;
    private ArrayList<Bid> bids;
    
    public Item(String name, int quantity, int startingPrice, String description, String imagePath) {
        name = name;
        quantity = quantity;
        startingPrice = startingPrice;
        description = description;
        imagePath = imagePath;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }
    
    public String getName() {
        return this.name;
    }
}
