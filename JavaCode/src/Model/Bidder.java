import java.time.LocalDateTime;
import java.util.*;

public class Bidder {
    //Group
    private String name;
    private String email;
    private String username;
    private String address;
    private String phoneNumber;
    private ArrayList<Bid> bids;
    private int balance;

    //constructor - Group
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

    //Shannon Weston
    public String getName() {
        return this.name;
    }

    //Hari Kuduva
    public boolean isBidPlaceable(final Auction auction) {
        //When method is invoked, Model.Bid is attempting to be placed NOW
        return (LocalDateTime.now().compareTo(auction.getStartDate()) < 0); // only pass condition
    }

    //Shannon Weston
    public boolean isBidPlaceable(final Item item, final Bid bid) {
        return (item.getCurrentBid() <= bid.getAmount());
    }

    //Group
    public ArrayList<Bid> getBids() {
        return this.bids;
    }

    @Override
    public String toString() {
        //For debugging
        return name + "\n" + email + "\n" + username + "\n" + address + "\n" + phoneNumber + "\n" + balance;
    }

    public String getUsername() {
        return this.username;
    }
}
