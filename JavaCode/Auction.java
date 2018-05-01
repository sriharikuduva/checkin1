import java.util.*;

public class Auction {
    private String Organization;
    private ArrayList<Items> items;
    private Clock startClock;
    private Clock endClock;
    
    public Auction(String organization, Clock startClock) {
        organization = organization;
        startClock = startClock;
        endClock = endClock;
    }
