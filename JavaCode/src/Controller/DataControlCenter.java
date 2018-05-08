import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.*;

/** 
 * 
 * @author Shannon Weston
 * @version 5/7/2018
 */
public class DataControlCenter {
	//These values can be adjusted per company policy.
	/**Sets the farthest date an auction can be scheduled.*/
	private static final int MAX_SCHEDULE_OUT_DAYS = 60;
	/**Sets the soonest date an auction can be scheduled.*/
	private static final int MIN_SCHEDULE_OUT_DAYS = 14;
	/**Set the maximum number of auctions that can be scheduled in any given day.*/
	private static final int MAX_AUCTIONS_PER_DAY = 2;
	/**Time distance (minimum) between end of one auction and start of next.*/
	private static final int STOP_TO_START_HOUR_GAP = 2;
    private HashSet<Auction> addedAuctions;
    private HashSet<Auction> updatedAuctions;
    private Scanner inputScanner;
    private int nextAvailableAuctionId;
    //private DataControlCenter dataControl = new DataControlCenter();

    public DataControlCenter() throws IOException, ClassNotFoundException {
        this.addedAuctions = new HashSet<>();
        this.updatedAuctions = new HashSet<>();
        this.nextAvailableAuctionId = findNextAvailableAuctionId();
    }

    /** Finds the next available auction id when creating auctions.
     * @return next available auction id */
    public int findNextAvailableAuctionId() {
        int max = Integer.MIN_VALUE;
        for (Auction auction : this.addedAuctions) {
            max = (auction.getAuctionID() > max) ? auction.getAuctionID() : max;
        }
        return max + 1;
    }

    /** Gets the next available auction id.
     * @return next available auction id */
    public int getNextAvailableAuctionId() {
        this.nextAvailableAuctionId++;
        return this.nextAvailableAuctionId - 1;

    }

    /** Deserializes all NPContacts from the bin file.
     * @return HashSet<NPContacts>
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk
     */
    private HashSet<NPContact> deserializeAllNPContacts() throws IOException, ClassNotFoundException {
        return (HashSet<NPContact>) new ObjectInputStream(getClass().
                getResourceAsStream("npcontact.bin")).readObject();
    }

    /** Deserializes all Bidders from the bin file.
     * @return HashSet<Bidder>
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk
     */
    private HashSet<Bidder> deserializeAllBidders() throws IOException, ClassNotFoundException {
        return (HashSet<Bidder>) new ObjectInputStream(getClass().
                getResourceAsStream("bidders.bin")).readObject();
    }

    /** Deserializes all Auctions from the bin file.
     * @return HashSet<Auction>
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk
     */
    public HashSet<Auction> deserializeAllAuctions() throws IOException, ClassNotFoundException {
        return (HashSet<Auction>) new ObjectInputStream(getClass()
                .getResourceAsStream("auctions.bin")).readObject();
    }

    /** Returns boolean val if the bidder is valid
     * @param username bidder's username
     * @return true if bidder is valid
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
    public boolean isBidderValid(String username) throws IOException, ClassNotFoundException {
        for (Bidder bidder : this.deserializeAllBidders()) {
            if (bidder.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /** Returns boolean val if the NPContact is valid
     * @param username npcontact's username
     * @return true if npcontact is valid
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk
     */
    public boolean isNonProfitValid(String username) throws IOException, ClassNotFoundException {
        for (NPContact contact : this.deserializeAllNPContacts()) {
            if (contact.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /** Gets a specific bidder by his/her username
     * @param username the username
     * @return Bidder
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
    public Bidder getBidderByUsername(String username) throws IOException, ClassNotFoundException {
        for (Bidder bidder : this.deserializeAllBidders()) {
            if (bidder.getUsername().equals(username)) {
                return bidder;
            }
        }
        return null;
    }

    /** Gets a specific NPContact by his/her username
     * @param username the username
     * @return NPContact */
    public NPContact getNPContactByUsername(String username) throws IOException, ClassNotFoundException {
        for (NPContact contact : this.deserializeAllNPContacts()) {
            if (contact.getUsername().equals(username)) {
                return contact;
            }
        }
        return null;
    }

    /** Retuns a set of auctions the bidder has bids in
     * @param currBidder the current bidder
     * @return HashSet<Auction> */
    public HashSet<Auction> getAuctionsCurrBidderHasBids(Bidder currBidder) throws ClassNotFoundException, IOException {
        HashSet<Auction> toSend = new HashSet<>();
        for (Auction auction : this.deserializeAllAuctions()) {
            for (Item item : auction.getItems()) {
                for (Bid bid : item.getBids()) {
                    if (bid.getBidder().equals(currBidder.getName())) {
                            toSend.add(auction);
                    }
                }
            }
        }
        return toSend;
    }

    /** Returns a set of Items the bidder has bid on in an auction.
     * @param currBidder the bidder
     * @param specific the auction
     * @return HashSet<Item> */
    public HashSet<Item> getItemsCurrBidderHasBidsOnInAnAuction(Bidder currBidder, Auction specific) throws IOException, ClassNotFoundException {
        HashSet<Item> toSend = new HashSet<>();
        for(Auction a : this.deserializeAllAuctions()) {
	        	if(a.toString().equals(specific.toString())) {
	            	for(Item i : a.getItems()) {
	            		for(Bid b : i.getBids()) {
	            			if(b.getBidder().equals(currBidder.getName())) {
	            				toSend.add(i);
	            			}
	            		}
	            	}
	        	}
        }
        return toSend;
    }

    /** Gets a set of auctions the bidder can bid on.
     * @param currBidder the bidder
     * @return set of auctions */
    public HashSet<Auction> getAuctionsCurrBidderCanBidOn(Bidder currBidder) throws ClassNotFoundException, IOException {
    	//Need to be able to list items in auctions (not just here)
        HashSet<Auction> toSend = new HashSet<>();
        for(Auction a : this.deserializeAllAuctions()) {
        	if(a.getOnlineStart().isBefore(LocalDateTime.now())) {
        		if(a.getEnd().isAfter(LocalDateTime.now())) {
                	toSend.add(a);
        		}
        	}
        }
        return toSend;
    }

    public void makeBid (Auction auction, ArrayList<Item> items, Item item, Bid bid, Bidder currBidder) throws ClassNotFoundException, IOException {
        boolean check1 = currBidder.isBidPlaceableAuctionDate(auction);
        boolean check2 = currBidder.isBidPlaceableInFutureAuctions(auction, items);
        
        	HashSet<Item> itemsOfAuction = getItemsCurrBidderHasBidsOnInAnAuction(currBidder,  auction);
        	int numberOfItemsWithBidInAnAuction = itemsOfAuction.size();
        
        boolean check3 = currBidder.isBidPlaceableItemWithBids(numberOfItemsWithBidInAnAuction);
        boolean check4 = currBidder.isBidPlaceableMinimumBid(item, bid);
        
        if (!check1) {
        		System.out.println("\tSorry, it has passed the auction end time, \n"
    				+ "\tyou may no longer bid on this item.");
        }
        
        if (!check2) {
        		System.out.println("\tSorry, you have already reached the maximum number of items\n"
    				+ "\tyou could bid on in all future auctions.");
        }
        
        if (!check3) {
        		System.out.println("\tSorry, you have already reached the maximum number of items\n"
    				+ "\tyou could bid on in an auction.");
        }
        
        if (!check4) {
        		System.out.println("\tSorry, the amount you have entered is less than the\n"
        				+ "\tbid price of this item."); 
        }
        
        if (check1 && check2 && check3 && check4) {
        		placeBid(auction, item, bid);
        		System.out.println("Congradulations. You have placed a bid successfully!");
        } 
    }

    /** Gets a set of auctions that were submitted as requests by NPContact
     * @param currContact the NPContact
     * @return set of auctions */
    public HashSet<Auction> getSubmittedAuctionsByNPContact(NPContact currContact) throws ClassNotFoundException, IOException {
        HashSet<Auction> toSend = new HashSet<>();
        for(Auction a : this.deserializeAllAuctions()) {
	        	if(a.getOrganization().equals(currContact.getName())) {
	        		toSend.add(a);
	        	}
        }
        for (Auction a : this.addedAuctions) {
            toSend.add(a);
        }
        
        return toSend;
    }

    /** Logs out the NPContact and serializes their information */
    public void logOutNP() throws IOException, ClassNotFoundException {
        HashSet<Auction> toSerialize = this.deserializeAllAuctions();
        for (Auction a : this.addedAuctions) {
            toSerialize.add(a);
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./JavaCode/Assets/auctions.bin"));
        oos.writeObject(toSerialize);
        this.addedAuctions.clear();
    }

    /** Adds an auction
     * @param auction the auction */
    public void addAuction(Auction auction) {
        this.addedAuctions.add(auction);
    }

    /** Gets the auctions.
     * @return Set of auctions */
    public HashSet<Auction> getAuctions() {
    	HashSet<Auction> allAuctions = new HashSet<Auction>();
    	for(Auction auction : this.addedAuctions) {
    		allAuctions.add(auction);
    	}

    	return allAuctions;
    }

    /** Returns true if the auction date is valid
     * @param inputDate the input date
     * @return true if date is valid */
    public boolean isRequestedAuctionDateValid(LocalDateTime inputDate) {
    	boolean available = true;
    	LocalDate earliest = LocalDate.now().plusDays(MIN_SCHEDULE_OUT_DAYS);
    	LocalDate latest = LocalDate.now().plusDays(MAX_SCHEDULE_OUT_DAYS);
    	LocalDate date = inputDate.toLocalDate();
    	
    	if(date.isBefore(earliest)) {
    		available = false;
    	}

    	if(date.isAfter(latest)) {
    		available = false;
    	}
    	return available;
    }

    /** Returns a boolean value depending if the auction date is available.
     * @param inputDate start date of auction
     * @param endDate end date of auction
     * @return true if auction date is available */
    public boolean isRequestedAuctionDateAvailable(LocalDateTime inputDate, LocalDateTime endDate) throws IOException, ClassNotFoundException {
    	boolean available = true;
    	int count = 0;
    	if(this.getAuctions().isEmpty()) {
    		return available;
    	}
        for (Auction auction : this.getAuctions()) {

        	System.out.println("existing auction: " + auction.getStart().toString());
        	System.out.println("requested: " + inputDate.toString());

            if (auction.getStart().equals(inputDate)) {
                count++;
        		if(count == MAX_AUCTIONS_PER_DAY) {
        			available = false;
        			break;
        		}
        		if(!isTimeAvailable(inputDate, endDate)) {
        			available = false;
        		}
            }
        }
    	return available;
    }

    /** Returns boolean value depending if auction time is available
     * @param inputDate start date
     * @param endDate end date
     * @return boolean */
    public boolean isTimeAvailable(LocalDateTime inputDate, LocalDateTime endDate) throws IOException, ClassNotFoundException {
    	LocalTime time = inputDate.toLocalTime();
    	LocalTime endInput = endDate.toLocalTime();
    	boolean available = true;
    	for (Auction auction : this.getAuctions()) {
            if (time.isBefore(auction.getEnd().toLocalTime().plusHours(STOP_TO_START_HOUR_GAP))) {
            	available = false;
            }
            if(endInput.isAfter(auction.getStart().toLocalTime().minusHours(STOP_TO_START_HOUR_GAP))) {
            	available = false;
            }
        }
    	return available;
    }
	

    public boolean isAuctionAvailableForSubmissionRequest(LocalDateTime inputDate, ArrayList<Auction> auctions) {
        int counter = 0;
        for (Auction auction : auctions) {
               if (inputDate.toLocalTime().isBefore(auction.getStart().toLocalTime()) && counter <= 2) {
                   return true;
               }
               counter++;
           }

       return false;
    }

    /** Logs the bidder out and serializes the data. */
    public void logOutBidder() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./JavaCode/Assets/auctions.bin"));

        HashSet<Auction> toSerialize = new HashSet<>();
        for (Auction replace : this.updatedAuctions) {
            for (Auction original : this.deserializeAllAuctions()) {
                if (replace.getAuctionID() == original.getAuctionID()) {
                    toSerialize.add(replace);
                }
            }
        }
        for (Auction original : this.deserializeAllAuctions()) {
            if (!toSerialize.contains(original)) {
                toSerialize.add(original);
            }
        }
        this.updatedAuctions.clear();
        oos.writeObject(toSerialize);
    }

    /** Places the bid
     * @param item the item
     * @param bid the bid */
    public void placeBid(Auction auction, Item item, Bid bid) {
		this.updatedAuctions.add(auction);
    		item.addBid(bid);
    }
}
