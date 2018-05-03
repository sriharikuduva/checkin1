import java.time.LocalDateTime;
import java.util.*;

public class Auction {
    private String organization;
    private ArrayList<Item> items;
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
