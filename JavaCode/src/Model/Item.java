import java.io.Serializable;
import java.util.*;

/** Represents an item in an auction. **/
public class Item implements Serializable {
    //Group
    /** Item's name **/
    private String name;
    /** Item's quantity **/
    private int quantity;
    /** Item's description **/
    private String  description;
    /** Item's starting bid **/
    private int startingBid;
    /** Item's imagePath **/
    private String imagePath;
    /** Item's current list of bids **/
    private ArrayList<Bid> bids;

    /** Creates an Item with 5 parameters
     * @param name  Item's name
     * @param quantity Item's quantity
     * @param startingBid Item's starting bid
     * @param description Item's description
     * @param imagePath Item's image path */
    public Item(String name, int quantity, int startingBid, String description, String imagePath) {
        this.name = name;
        this.quantity = quantity;
        this.startingBid = startingBid;
        this.description = description;
        this.imagePath = imagePath;
        this.bids = new ArrayList<>();
    }

    //Group
    public void addBid(Bid bid) {
        bids.add(bid);
    }

    //Group
    public String getName() {
        return this.name;
    }
    
    public ArrayList<Bid> getBids() {
    	return this.bids;
    }
    
    //Shannon Weston
    public int getCurrentBid() {
        int bid = 0;
        
        if(bids.size() == 0) {
            //There are no bids yet so startingBid is bid to beat.
            bid = startingBid;
        } else {
            //Bids will be added to the list in ascending order.
            //Therefore, last value in bids will be the bid to beat.
            //To make this secure, bids must be immutable.
            bid = bids.get(bids.size() - 1).getAmount();
        }
        
        return bid;
    }
}


