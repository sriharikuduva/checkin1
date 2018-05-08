import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author MauriceChiu
 * @version May 7, 2018
 */
public class ItemWrapper {
	private String organization;
    private LocalDateTime start;
    private LocalDateTime end;
    
	private String name;
    private int quantity;
    private String  description;
    private int startingBid;
    private String imagePath;
    private ArrayList<Bid> bids;

    //Group
    public ItemWrapper(String organization, LocalDateTime startClock, LocalDateTime endClock, String name, int quantity, int startingBid, String description, String imagePath) {
    		this.organization = organization;
        this.start = startClock;
        this.end = endClock;
        
    		this.name = name;
        this.quantity = quantity;
        this.startingBid = startingBid;
        this.description = description;
        this.imagePath = imagePath;
    }
    
    public String getOrgName() {
    		return this.organization;
    }
    
    public Auction getAuction() {
    		Auction auction = new Auction(this.organization, this.start, this.end);
    		return auction;
    }
    
    public Item getItem() {
    		Item item = new Item(this.name, this.quantity, this.startingBid, this.description, this.imagePath);
    		return item;
    }
    
    @Override
    public String toString() {
    		return this.organization+this.name;
    }
}
