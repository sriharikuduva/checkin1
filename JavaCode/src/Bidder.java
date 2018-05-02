import java.time.LocalDateTime;
import java.util.*;

public class Bidder {
    private String name;
    private String email;
    private String username;
    private String address;
    private String phoneNumber;
    private ArrayList<Bid> bids;
    private int balance;
  
  //constructor
    public Bidder(final String name,final String email, final String username,
           final String address, final String phoneNumber, final int balance) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.bids = new ArrayList<Bid>();
    }

    public boolean isBidPlaceable(final Auction auction) {
        return (LocalDateTime.now().compareTo(auction.getStartDate()) < 0); // only pass condition
    }
 
    public ArrayList<Bid> getBids() {
        return this.bids;
    }
}
