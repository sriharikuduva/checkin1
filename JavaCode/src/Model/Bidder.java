import java.time.LocalDateTime;
import java.util.*;

/**
 * Carries extra information specific to bidders and defines
 * what a bidder can or cannot do.
 * 
 * @author HariKuduva
 * @author ShannonWeston
 * @author BaisalUrustanbekov
 * @author MauriceChiu
 * @version 
 */
public class Bidder extends User {
    //Group
    private ArrayList<Bid> bids;
    private String name;
    private int balance;
    public static final int MAX_ITEMS_WITH_BID_IN_AN_AUCTION = 4;

    //constructor - Group
    public Bidder(final String name,final String email, final String username,
            final String address, final String phoneNumber, final int balance) {
    		super(email, username, address, phoneNumber);
    		this.name = name;
    		this.balance = balance;
    		this.bids = new ArrayList<Bid>();
    }

    //Shannon Weston
    public String getName() {
        return this.name;
    }

    //Hari Kuduva
    public boolean isBidPlaceableAuctionDate(final Auction auction) {
        //When method is invoked, Model.Bid is attempting to be placed NOW
        return (LocalDateTime.now().compareTo(auction.getStartDate()) < 0); // only pass condition
    }

    //Shannon Weston
    public boolean isBidPlaceableMinimumBid(final Item item, final Bid bid) {
        return (item.getCurrentBid() <= bid.getAmount());
    }

    //Maurice Chiu
    public boolean isBidPlaceableItemWithBids(int numberOfItemsWithBidInAnAuction) {
		if (numberOfItemsWithBidInAnAuction < MAX_ITEMS_WITH_BID_IN_AN_AUCTION) {
			return true;
		} else {
			return false;
		}
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
