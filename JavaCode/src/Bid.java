import java.util.*;

public class Bid {
    private String bidderName;
    private String itemName;
    private int amount;

    public Bid(String bidderName, String itemName, int amount) {
        this.bidderName = bidderName;
        this.itemName = itemName;
        this.amount = amount;
    }
    
    public String getBidder(){
        return this.bidderName;
    }
    
    public String getItem() {
        return this.itemName;
    }
    
    public int getAmount() {
        return this.amount;
    }
}
