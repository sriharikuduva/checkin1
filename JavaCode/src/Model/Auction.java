import java.time.LocalDateTime;
import java.util.*;

public class Auction {
    private String organization;
    private ArrayList<Item> items;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    //Shannon Weston
    private LocalDateTime creationDate;

    public Auction(String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this.organization = organization;
        this.startDate = startClock;
        this.endDate = endClock;
        this.items = new ArrayList<>();
        
        this.creationDate = LocalDateTime.now();
    }

    public Auction (String organization) {
        this.organization = organization;
        this.creationDate = LocalDateTime.now();
    }
    
    public Auction() {
        //Empty Auction for BidderConsole.java
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(LocalDateTime date) {
        this.startDate = date;
    }
    
    public void setEndDate(LocalDateTime date) {
        this.endDate = date;
    }
    
    public void addItem(Item item) {
        this.items.add(item);
    }
}