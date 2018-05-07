import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Auction implements Serializable {
	private static final long serializableUID = 1L;
	
    private String organization;
    protected ArrayList<Item> items;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime onlineStart;
    
    //Shannon Weston
    private LocalDateTime creationDate;

    public Auction(String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this.organization = organization;
        this.start = startClock;
        this.end = endClock;
        this.items = new ArrayList<Item>();
        
        this.creationDate = LocalDateTime.now();
    }

    public Auction (String organization) {
        this.organization = organization;
        this.creationDate = LocalDateTime.now();
    }
    
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
    
    public void setOnlineStart(LocalDateTime date) {
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
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Start Date: " + this.start);
    	sb.append("End Date: " + this.end);
    	sb.append("Opened Online: " + this.onlineStart);
    	sb.append("Number of Items: " + this.items.size());
    	
    	return sb.toString();
    }
}