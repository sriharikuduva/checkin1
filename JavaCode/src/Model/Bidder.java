import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/** Represents a bidder.
 * @author Hari G Kuduva
 */
public class Bidder extends User implements Serializable {
    /** List of bids the bidder has. **/
    private ArrayList<Bid> bids; // bid1 current bid 2 future  12 bid
    /** Bidder's name. **/
    private String name;
    /** Bidder's bank balance. **/
    private int balance;
    /** Number of items the bidder has bids on. */
    //private int currentItemCount;
    /** Max number of bids a bidder can have in an auction. **/
    public static final int MAX_ITEMS_WITH_BID_IN_AN_AUCTION = 8;
    /** Max number of bids a bidder can have in all auctions. **/
    public static final int MAX_ITEMS_WITH_BID_IN_ALL_AUCTIONS = 12;

    /** Creates a bidder with 6 parameters.
     * @param name bidder's name
     * @param email bidder's email
     * @param username bidder's username
     * @param address bidder's address
     * @param phoneNumber bidder's phoneNum
     * @param balance bidder's bank account */
    public Bidder(final String name,final String email, final String username,
        final String address, final String phoneNumber, final int balance) throws IOException, ClassNotFoundException {
    	super(email, username, address, phoneNumber);
    	this.name = name;
    	this.balance = balance;
        this.bids = new ArrayList<Bid>();
    }

    //Shannon Weston
    public String getName() {
        return this.name;
    }

//    public boolean bidPriceCheck(final Item item, final Bid bid) {
//        return (item.getCurrentBid() <= bid.getAmount());
//    }

//    public boolean auctionDateCheck(final Auction auction) { // TODO Remove for it's already been checked
//        return (LocalDateTime.now().compareTo(auction.getStart()) < 0);
//    }

//    public boolean itemNumberPerAuctionCheck() {
//        return true;
//    }

//    public boolean itemNumberAllAuctionsCheck() {
//        return (this.bids.size() < MAX_ITEMS_WITH_BID_IN_ALL_AUCTIONS);
//    }

//    public boolean[] isBidPlacable(final Auction auction, final Item item, final Bid bid) {
//        final boolean[] errors = new boolean[] {true, true, true};
//        if (!bidPriceCheck(item, bid)) {
//            errors[0] = false;
//        } else if (!auctionDateCheck(auction)) {
//            errors[1] = false;
//        //} else if (!itemNumberPerAuctionCheck()) {
//        //    errors[2] = false;
//        } else if (!itemNumberAllAuctionsCheck()) {
//            errors[2] = false;
//        }
//        return errors;
//    }

    public int getBalance(){
        return this.balance;
    }

    /**
     *
     * @param amount the amount that the bidder has entered to bid on an item.
     */
    public void payForBid(int amount) {
        this.balance -= amount;
    }
//    /** Checks if the bid is placeable on the auction date.
//     * @param auction used to check the auction date
//     * @return if the bid is placeable or not */
//    //Hari Kuduva
//    public boolean isBidPlaceableAuctionDate(final Auction auction) {
//        //When method is invoked, Model.Bid is attempting to be placed NOW
//        return (LocalDateTime.now().compareTo(auction.getStart()) < 0); // only pass condition
//    }

//    /** Checks if the bid is placeable with the given bid amount
//     * @param item the item that is being bid on
//     * @param bid needed to verify the bid amount
//     * @return if the bid is placeable or not */
//    //Shannon Weston
//    public boolean isBidPlaceableMinimumBid(final Item item, final Bid bid) {
//        return (item.getCurrentBid() <= bid.getAmount());
//    }

//    /** Checks if the bid is placeable (less than alloted # bids per auction)
//     * @param numberOfItemsWithBidInAnAuction number of items with bid in an auction
//     * @return if the bid is placeable or not */
//    //Maurice Chiu
//    public boolean isBidPlaceableItemWithBids(int numberOfItemsWithBidInAnAuction) {
//		/*if (numberOfItemsWithBidInAnAuction < MAX_ITEMS_WITH_BID_IN_AN_AUCTION) {
//			return true;
//		} else {
//			return false;
//		}*/ // BOOLEAN ZEN!!!
//		return numberOfItemsWithBidInAnAuction < MAX_ITEMS_WITH_BID_IN_AN_AUCTION;
//	}

//    /** Checks if a bid is placeable in future auctions.
//     * @param auction the future auction
//     * @param item the item to bid on
//     * @return if the bid is placeable or not */
//	//Baisal Urustanbekov
//    public boolean isBidPlaceableInFutureAuctions(final Auction auction, ArrayList<Item> item) {
//        //When method is invoked
//        return ((LocalDateTime.now().compareTo(auction.getEnd()) < 0) && item.size() <= 10);
//    }

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

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }
}
