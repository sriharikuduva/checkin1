import java.time.LocalDateTime;
import java.util.*;

/**
 * Contains all the information about an auction and its items for sell.
 * 
 * @author HariKuduva
 * @author ShannonWeston
 * @author BaisalUrustanbekov
 * @author MauriceChiu
 * @version 
 */
public class Auction {
    private String organization;
    protected ArrayList<Item> items;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Auction(String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this.organization = organization;
        this.startDate = startClock;
        this.endDate = endClock;
        this.items = new ArrayList<>();
    }

    public Auction () {

    }
    
    public String getOrganization() {
    		return this.organization;
    }
    
    public LocalDateTime getStartDate() {
        return this.startDate;
    }
    
    public ArrayList<Item> getItems() {
    		return this.items;
    }
}
