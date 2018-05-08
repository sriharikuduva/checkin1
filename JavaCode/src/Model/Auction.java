import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.Serializable;
import java.util.*;

public class Auction implements Serializable {
    private String organization;
    private ArrayList<Item> items;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime onlineStart;
    private int auctionID;
    //private DateTimeFormatter fmt;

    //Shannon Weston
    private LocalDateTime creationDate;

    public Auction (String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this(organization, startClock, endClock, -1);
        this.items = new ArrayList<Item>();
        //fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        onlineStart = LocalDateTime.now();
    }

    public Auction(String organization, LocalDateTime startClock, LocalDateTime endClock, int auctionID) {
        this.organization = organization;
        this.start = startClock;
        this.end = endClock;
        this.items = new ArrayList<Item>();
        this.creationDate = LocalDateTime.now();
        this.auctionID = auctionID;
        //fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        onlineStart = LocalDateTime.now();
    }

    public Auction (String organization) {
        this.organization = organization;
        this.creationDate = LocalDateTime.now();
        this.items = new ArrayList<Item>();
        //fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        onlineStart = LocalDateTime.now();
    }
    
    public Auction() {
        //Empty Auction for BidderConsole.java
    	this.items = new ArrayList<Item>();
    	onlineStart = LocalDateTime.now();
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
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Auction has " + this.items.size() + " items.");
    	sb.append(" Start Time : " + this.start.toString());
    	sb.append(" End Time : " + this.end.toString());
    	sb.append(" Online Launch: " + this.onlineStart.toString());
    	sb.append("\n");
    	return sb.toString();
    }
}