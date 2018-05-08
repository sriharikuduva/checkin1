import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/** Represents a bidder. **/
public class Bidder extends User implements Serializable {
    /** List of bids the bidder has. **/
    private ArrayList<Bid> bids;
    /** Bidder's name. **/
    private String name;
    /** Bidder's bank balance. **/
    private int balance;
    /** Max number of bids a bidder can have in an auction. **/
    public static final int MAX_ITEMS_WITH_BID_IN_AN_AUCTION = 4;

    /** Creates a bidder with 6 parameters.
     * @param name bidder's name
     * @param email bidder's email
     * @param username bidder's username
     * @param address bidder's address
     * @param phoneNumber bidder's phoneNum
     * @param balance bidder's bank account */
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

    /** Checks if the bid is placeable on the auction date.
     * @param auction used to check the auction date
     * @return if the bid is placeable or not */
    //Hari Kuduva
    public boolean isBidPlaceableAuctionDate(final Auction auction) {
        //When method is invoked, Model.Bid is attempting to be placed NOW
        return (LocalDateTime.now().compareTo(auction.getStart()) < 0); // only pass condition
    }

    /** Checks if the bid is placeable with the given bid amount
     * @param item the item that is being bid on
     * @param bid needed to verify the bid amount
     * @return if the bid is placeable or not */
    //Shannon Weston
    public boolean isBidPlaceableMinimumBid(final Item item, final Bid bid) {
        return (item.getCurrentBid() <= bid.getAmount());
    }

    /** Checks if the bid is placeable (less than alloted # bids per auction)
     * @param numberOfItemsWithBidInAnAuction number of items with bid in an auction
     * @return if the bid is placeable or not */
    //Maurice Chiu
    public boolean isBidPlaceableItemWithBids(int numberOfItemsWithBidInAnAuction) {
		/*if (numberOfItemsWithBidInAnAuction < MAX_ITEMS_WITH_BID_IN_AN_AUCTION) {
			return true;
		} else {
			return false;
		}*/ // BOOLEAN ZEN!!!
		return numberOfItemsWithBidInAnAuction < MAX_ITEMS_WITH_BID_IN_AN_AUCTION;
	}

    /** Checks if a bid is placeable in future auctions.
     * @param auction the future auction
     * @param item the item to bid on
     * @return if the bid is placeable or not */
	//Baisal Urustanbekov
    public boolean isBidPlaceableInFutureAuctions(final Auction auction, ArrayList<Item> item) {
        //When method is invoked
        return ((LocalDateTime.now().compareTo(auction.getEnd()) < 0) && item.size() <= 10);
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
