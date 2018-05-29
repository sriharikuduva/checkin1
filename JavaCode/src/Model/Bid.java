import java.io.Serializable;

/** Represents a bid.
 * @author Hari G Kuduva
 */
public class Bid implements Serializable {
    /** Name of the bidder. **/
    private String bidderName;
    /** Name of the item. **/
    private String itemName;
    /** Bid amount. **/
    private int amount;

    private int auctionId;


    /** Creates a bid with 3 parameters
     * @param bidderName the bidder's name
     * @param itemName the item's name
     * @param amount bid amount */
    public Bid(String bidderName, String itemName, int amount, int auctionId) {
        this.bidderName = bidderName;
        this.itemName = itemName;
        this.amount = amount;
        this.auctionId = auctionId;
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
