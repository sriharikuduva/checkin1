import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.*;

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

    public DataControlCenter() throws IOException, ClassNotFoundException {
        this.addedAuctions = new HashSet<>();
        this.updatedAuctions = new HashSet<>();
        this.nextAvailableAuctionId = findNextAvailableAuctionId();
    }

    public int findNextAvailableAuctionId() {
        int max = Integer.MIN_VALUE;
        for (Auction auction : this.addedAuctions) {
            max = (auction.getAuctionID() > max) ? auction.getAuctionID() : max;
        }
        return max + 1;
    }

    public int getNextAvailableAuctionId() {
        this.nextAvailableAuctionId++;
        return this.nextAvailableAuctionId - 1;

    }
    private HashSet<NPContact> deserializeAllNPContacts() throws IOException, ClassNotFoundException {
        return (HashSet<NPContact>) new ObjectInputStream(getClass().
                getResourceAsStream("npcontact.bin")).readObject();
    }

    private HashSet<Bidder> deserializeAllBidders() throws IOException, ClassNotFoundException {
        return (HashSet<Bidder>) new ObjectInputStream(getClass().
                getResourceAsStream("bidders.bin")).readObject();
    }

    public HashSet<Auction> deserializeAllAuctions() throws IOException, ClassNotFoundException {
        return (HashSet<Auction>) new ObjectInputStream(getClass()
                .getResourceAsStream("auctions.bin")).readObject();
    }

    public boolean isBidderValid(String username) throws IOException, ClassNotFoundException {
        for (Bidder bidder : this.deserializeAllBidders()) {
            if (bidder.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNonProfitValid(String username) throws IOException, ClassNotFoundException {
        for (NPContact contact : this.deserializeAllNPContacts()) {
            if (contact.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Bidder getBidderByUsername(String username) throws IOException, ClassNotFoundException {
        for (Bidder bidder : this.deserializeAllBidders()) {
            if (bidder.getUsername().equals(username)) {
                return bidder;
            }
        }
        return null;
    }

    public NPContact getNPContactByUsername(String username) throws IOException, ClassNotFoundException {
        for (NPContact contact : this.deserializeAllNPContacts()) {
            if (contact.getUsername().equals(username)) {
                return contact;
            }
        }
        return null;
    }

    public HashSet<Auction> getAuctionsCurrBidderHasBids(Bidder currBidder) throws ClassNotFoundException, IOException {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of auctions that the bidder has bids in
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
//
//        for(Auction auction : this.getAuctions()) {
//        	for(Item item : auction.getItems()) {
//        		for(Bid bid : item.getBids()) {
//        			if(bid.getBidder().equals(currBidder.getName())) {
//        				toSend.add(auction);
//        			}
//        		}
//        	}
//        }

        return toSend;
    }

    public HashSet<Item> getItemsCurrBidderHasBidsOnInAnAuction(Bidder currBidder, Auction specifc) {
        HashSet<Item> toSend = new HashSet<>();
        //TODO: return a Set of Items the Bidder has bids on in the specific Auction
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public HashSet<Auction> getAuctionsCurrBidderCanBidOn(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of Auctions currBidder can place bids on
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public void makeBid (Auction auction, Bidder currBidder) {
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        //TODO: make bid if placeable
        /* Implementation could look something like this??? */
            //currBidder.isBidPlaceable(auction);
            //currBidder.placeBid(new Bid(...));
    }

    public HashSet<Auction> getSubmittedAuctionsByNPContact(NPContact currContact) throws ClassNotFoundException, IOException {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return auctions submitted by currContact
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");

        for(Auction auction : this.getAuctions()) {
        	if(auction.getOrganization() == currContact.getName()) {
        		toSend.add(auction);
        	}
        }
        return toSend;
    }

    public void logOutNP() throws IOException, ClassNotFoundException {
        HashSet<Auction> toSerialize = this.deserializeAllAuctions();
        for (Auction a : this.addedAuctions) {
            toSerialize.add(a);
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./JavaCode/Assets/auctions.bin"));
        oos.writeObject(toSerialize);
        this.addedAuctions.clear();
    }
    
    public void addAuction(Auction auction) {
        this.addedAuctions.add(auction);
    }
    
    public HashSet<Auction> getAuctions() throws ClassNotFoundException, IOException {
    	HashSet<Auction> allAuctions = new HashSet<Auction>();
    	for(Auction auction : this.addedAuctions) {
    		allAuctions.add(auction);
    	}

    	return allAuctions;
    }
    
    //For Unit Test
    //No auction can be scheduled more than a set number of days from the current date, default of 60 days.
    //No auction can be scheduled less than a set number of days from the current date, default of 14.
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
    
    //For Unit Test
    //No more than two auctions can occur on the same day in the entire system
    public boolean isRequestedAuctionDateAvailable(LocalDateTime inputDate, LocalDateTime endDate) throws IOException, ClassNotFoundException {
    	boolean available = true;
    	int count = 0;

    	//iterate through hashSet of auctions
    	if(this.getAuctions().isEmpty()) {
    		return available;
    	}
        for (Auction auction : this.getAuctions()) {

        	System.out.println("existing auction: " + auction.getStart().toString());
        	System.out.println("requested: " + inputDate.toString());

            if (auction.getStart().equals(inputDate)) {
            	//System.out.println("Found date");
                count++;
        		if(count == MAX_AUCTIONS_PER_DAY) {
        			available = false;
        			break;
        		}
        		//Checks only if auction being requested is on a previously scheduled date
        		if(!isTimeAvailable(inputDate, endDate)) {
        			available = false;
        		}
            }
        }
    	return available;
    }
    
    public boolean isTimeAvailable(LocalDateTime inputDate, LocalDateTime endDate) throws IOException, ClassNotFoundException {
    	LocalTime time = inputDate.toLocalTime();
    	LocalTime endInput = endDate.toLocalTime();
    	boolean available = true;
    	
    	//iterate through hashSet of auctions
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

    /** DEBUGGIN PURPOSES (Test to see if serialization for bidder's bids is working) **/
    public void placeBidDebugger(Bidder currBidder, int amount) throws IOException, ClassNotFoundException {
        //Places a bid on OON auction (Auc.ID = 5) on the first item under currBider name and amount.
        Auction oonAuction = null;
        for (Auction a : this.deserializeAllAuctions()) {
            if (a.getAuctionID() == 5) {
                oonAuction = a;
            }
        }
        Item item = oonAuction.getItems().get(0);
        item.addBid(new Bid(currBidder.getName(), item.getName(), amount));
        this.updatedAuctions.add(oonAuction);
    }

    public void logOutBidder() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./JavaCode/Assets/auctions.bin"));
        HashSet<Auction> toSerialize = this.deserializeAllAuctions();
        for(Auction needToReplaceInSerializingSet : this.updatedAuctions) {
            for (Auction a : toSerialize) {
                if (needToReplaceInSerializingSet.getAuctionID() == a.getAuctionID()) {
                    toSerialize.remove(a);
                    toSerialize.add(needToReplaceInSerializingSet);
                }
            }
        }
        this.updatedAuctions.clear();
        oos.writeObject(toSerialize);
    }
}
