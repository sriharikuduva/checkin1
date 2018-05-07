import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import javax.naming.AuthenticationNotSupportedException;
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

    private Scanner inputScanner;

    public DataControlCenter() { }

    private HashSet<NPContact> deserializeAllNPContacts() throws IOException, ClassNotFoundException {
        return (HashSet<NPContact>) new ObjectInputStream(getClass().
                getResourceAsStream("npcontact.bin")).readObject();
    }

    private HashSet<Bidder> deserializeAllBidders() throws IOException, ClassNotFoundException {
        return (HashSet<Bidder>) new ObjectInputStream(getClass().
                getResourceAsStream("bidders.bin")).readObject();
    }
    
    private HashSet<Auction> deserializeAllAuctions() throws IOException, ClassNotFoundException {
    	HashSet<Auction> allAuctions = new HashSet<Auction>();
    	try {
    		allAuctions = (HashSet<Auction>) new ObjectInputStream(getClass().
                  getResourceAsStream("auctions.bin")).readObject();
    	} catch (EOFException e) {
    		//System.out.println("Could not read in auctions.");
    		//e.printStackTrace();
    	}

    	return allAuctions;
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
        //TODO: make bid if placebale
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
    
    public void addAuction(Auction auction) throws ClassNotFoundException, IOException {
		HashSet<Auction> updateAuctions = new HashSet<Auction>();
		
    	try {
    		//System.out.println("Creating updateAuctions");
    		updateAuctions = this.getAuctions();
    		
//        	if(updateAuctions.size() > 0) {
//        		for(Auction outAuction : updateAuctions) {
//            		System.out.println("Existing Auctions: " + outAuction.getStart().toString());
//        		}
//        	}
    	} catch (Exception e) {
    		System.out.println("Can't print existing auctions.");
    	}
    	
    	try {
    		updateAuctions.add(auction);
    		
    		//System.out.println("update: " + updateAuctions.size());
    		
        	FileOutputStream f = new FileOutputStream(new File("auctions.bin"));
        	ObjectOutputStream o = new ObjectOutputStream(f);
 
        	Iterator<Auction> iter = updateAuctions.iterator();
        	
        	while(iter.hasNext()) {
            	//System.out.println("Wrote Auction to File. \n");
        		o.writeObject(iter.next());
        	}

        	o.close();
        	f.close();
    	} catch (Exception e){
    		System.out.println("Could Not Save Auction.");
    		e.printStackTrace();
    	}
    }
    
    public HashSet<Auction> getAuctions() throws ClassNotFoundException, IOException {
    	HashSet<Auction> allAuctions = new HashSet<Auction>();
    	for(Auction auction : this.deserializeAllAuctions()) {
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
}
