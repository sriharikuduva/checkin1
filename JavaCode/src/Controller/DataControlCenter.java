import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import javax.naming.AuthenticationNotSupportedException;
public class DataControlCenter {
	private static final int MAX_AUCTIONS_PER_DAY = 2;

    private Scanner inputScanner;
    private ArrayList<Auction> masterListOfAuctions;

    public DataControlCenter() {
    	masterListOfAuctions = new ArrayList<Auction>();
    }

    private HashSet<NPContact> deserializeAllNPContacts() throws IOException, ClassNotFoundException {
        return (HashSet<NPContact>) new ObjectInputStream(getClass().
                getResourceAsStream("npcontact.bin")).readObject();
    }

    private HashSet<Bidder> deserializeAllBidders() throws IOException, ClassNotFoundException {
        return (HashSet<Bidder>) new ObjectInputStream(getClass().
                getResourceAsStream("bidders.bin")).readObject();
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

    public HashSet<Auction> getAuctionsCurrBidderHasBids(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of auctions that the bidder has bids in
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
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
        //TODO: make bid if placebale
        /* Implementation could look something like this??? */
            //currBidder.isBidPlaceable(auction);
            //currBidder.placeBid(new Bid(...));
    }

    public HashSet<Auction> getSubmittedAuctionsByNPContact(NPContact currContact) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return auctions submitted by currContact
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }
    
    public void addAuction(Auction auction) {
    	this.masterListOfAuctions.add(auction);
    }
    
    public ArrayList<Auction> getAuctions() {
    	return this.masterListOfAuctions;
    }
    
    //For Unit Test
    //No auction can be scheduled more than a set number of days from the current date, default of 60 days.
    //No auction can be scheduled less than a set number of days from the current date, default of 14.
    public boolean isRequestedAuctionDateValid(String[] parts) {
    	boolean available = true;
    	//is date > 14 days from now
    	//is date < 60 days from now
    	
    	return available;
    }
    
    //For Unit Test
    //No more than two auctions can occur on the same day in the entire system
    public boolean isRequestedAuctionDateAvailable(String[] parts) {
    	boolean available = true;
    	
    	int count = 0;
    	if(masterListOfAuctions.size() != 0) {
        	for(int i = 0; i < masterListOfAuctions.size(); i++) {
        		LocalDateTime dateTime = masterListOfAuctions.get(i).getStart();
        		dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        		String[] parseDate = dateTime.toString().split(" ");
        		
        		for(int j = 0; j < parts.length; j++) {
        			if(parts[j] == parseDate[j]) {
        				count++;
        			}
        		}
        		
        		if(count == MAX_AUCTIONS_PER_DAY) {
        			available = false;
        		}
        	}
    	}

    	return available;
    }
    
    public boolean isTimeAvailable(String[] parts) {
    	boolean available = true;
    	
    	return available;
    }
}
