import java.time.LocalDateTime;
import java.util.*;
//import java.time.Clock;

public class Auction {
    private String organization;
    private ArrayList<Item> items;
    //private Clock startClock;
    //private Clock endClock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    public Auction(String organization, LocalDateTime startClock, LocalDateTime endClock) {
        this.organization = organization;
        this.startDate = startClock;
        this.endDate = endClock;
        this.items = new ArrayList<>();
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }
}
