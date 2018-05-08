import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.*;

/** Meant to represent an auction with its entities */
public class Auction implements Serializable {
    /** Organization's name **/
    private String organization;
    /** List of items that the auction has **/
    private ArrayList<Item> items;
    /** Start time of the auction **/
    private LocalDateTime start;
    /** End time of the auction **/
    private LocalDateTime end;
    /** Online start time of the auction **/
    private LocalDateTime onlineStart;
    /** Auction Identification number **/
    private int auctionID;

    //Shannon Weston
    /** Creation date for the auction **/
    private LocalDateTime creationDate;

    /** Creates an auction with 3 parameters
     * @param organization the organization name
     * @param startClock the start time
     * @param endClock the end time
     */
    public Auction (String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this(organization, startClock, endClock, -1);
    }

    /**
     * Creates an auction with 4 parameters
     * @param organization the organization name
     * @param startClock the start time
     * @param endClock the end time
     * @param auctionID the auction identification number
     */
    public Auction(String organization, LocalDateTime startClock, LocalDateTime endClock, int auctionID) {
        this.organization = organization;
        this.start = startClock;
        this.end = endClock;
        this.items = new ArrayList<Item>();
        this.creationDate = LocalDateTime.now();
        this.auctionID = auctionID;
    }

    /** Creates an auction with 1 parameter
     * @param organization the organization name */
    public Auction (String organization) {
        this.organization = organization;
        this.creationDate = LocalDateTime.now();
    }

    /** Creates an empty auction with 0 parameters */
    public Auction() {
        //Empty Auction for BidderConsole.java
    }
    
    public void setOrganization(String organization) {
    	this.organization = organization;
    }
    
    public String getOrganization() {
    	return this.organization;
    }

    public LocalDateTime getStart() {
        return this.start;
    }
    
    public void setStart(LocalDateTime date) {
        this.start = date;
    }
    
    public void setEnd(LocalDateTime date) {
        this.end = date;
    }
    
    public LocalDateTime getEnd() {
    	return this.end;
    }
    
    public void setOnlneStart(LocalDateTime date) {
    	this.onlineStart = date;
    }
    
    public LocalDateTime getOnlineStart(LocalDateTime date) {
    	return onlineStart;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
    
    public ArrayList<Item> getItems() {
    	return this.items;
    }

    public int getAuctionID() {
        return this.auctionID;
    }

    public void setOnlineStart(LocalDateTime startOnline) {
        this.onlineStart = startOnline;
    }

    public void setAuctionId(int nextAvailableAuctionId) {
        this.auctionID = nextAvailableAuctionId;
    }
}