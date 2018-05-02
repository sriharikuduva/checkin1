import java.util.*;
import java.time.Clock;


public class Auction {
    private String organization;
    private ArrayList<Item> items;
    private Clock startClock;
    private Clock endClock;
    
    public Auction(String organization, Clock startClock, Clock endClock) {
        this.organization = organization;
        this.startClock = startClock;
        this.endClock = endClock;
    }
}
