import java.util.*;

public class Item {
    private String name;
    private int quantity;
    private String  description;
    private int startingPrice;
    private String imagePath;
    private ArrayList<Bid> bids;
    
    public Item(String name, int quantity, int startingPrice, String description, String imagePath) {
        this.name = name;
        this.quantity = quantity;
        this.startingPrice = startingPrice;
        this.description = description;
        this.imagePath = imagePath;
        this.bids = new ArrayList<>();
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }
    
    public String getName() {
        return this.name;
    }
}
