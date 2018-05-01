import java.util.*;

public class Bid {
    private String bidderName;
    private String itemName;
    private int amount;
    
    public Bid(String biddername, String itemName, int amount) {
        bidderName = bidderName;
        itemName = itemName;
        amount = amount;
    }
    
    public String getBidder(){
        return bidderName;
    }
    
    public String getItem() {
        return itemName;
    }
    
    public int getAmount() {
        return amount;
    }
}
