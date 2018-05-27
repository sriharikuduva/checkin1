import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.Serializable;
import java.util.*;

/** Meant to represent an auction with its entities */

/**
 * @author Hari G Kuduva
 * @author Shannon Weston
 */
public class Auction implements Serializable {
	private static final int MIN_SCHEDULE_OUT_DAYS = 14;
	
    /** Organization's name **/
    private String organization;
    /** List of items that the auction has **/
    protected ArrayList<Item> items;
    /** Start time of the auction **/
    private LocalDateTime start;
    /** End time of the auction **/
    private LocalDateTime end;
    /** Online start time of the auction **/
    private LocalDateTime onlineStart;
    /** Auction Identification number **/
    private int auctionID;

    private boolean isCanceled;

    //Shannon Weston
    /** Creation date for the auction **/
    private LocalDateTime creationDate;

    /** Creates an auction with 3 parameters
     * @param organization the organization name
     * @param startClock the start time
     * @param endClock the end time
     */
    public Auction (String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this.items = new ArrayList<Item>();
        this.onlineStart = startClock.minusDays(MIN_SCHEDULE_OUT_DAYS);
        this.isCanceled = false;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public boolean isCanceled() {
        return isCanceled;
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
        //fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        onlineStart = this.start.minusDays(MIN_SCHEDULE_OUT_DAYS);
        this.isCanceled = false;
    }

    /** Creates an auction with 1 parameter
     * @param organization the organization name */
    public Auction (String organization) {
        this.organization = organization;
        this.creationDate = LocalDateTime.now();
        this.items = new ArrayList<Item>();
        //fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        onlineStart = this.start.minusDays(MIN_SCHEDULE_OUT_DAYS);
    }

    /** Creates an empty auction with 0 parameters */
    public Auction() {
        //Empty Auction for BidderConsole.java
    	this.items = new ArrayList<Item>();
    	this.creationDate = LocalDateTime.now();
    	this.isCanceled = false;
    	//onlineStart = start.minusDays(MIN_SCHEDULE_OUT_DAYS);
    }

    @Override
    public boolean equals(Object obj) {
        return this.auctionID == ((Auction) obj).auctionID;
    }

    @Override
    public int hashCode() {
        return this.auctionID;
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
    
    public void setOnlineStart(LocalDateTime date) {
    	this.onlineStart = date;
    }
    
    public LocalDateTime getOnlineStart() {
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

    public void setAuctionId(int nextAvailableAuctionId) {
        this.auctionID = nextAvailableAuctionId;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Auction by " + this.organization + " has " + this.items.size() + " items.\n");
    	sb.append("\tStart Time : " + this.start.toString());
    	sb.append("\n\tEnd Time : " + this.end.toString());
    	sb.append("\n\tOnline Launch: " + this.onlineStart.toString());
    	sb.append("\n\n");
    	return sb.toString();
    }
    
}