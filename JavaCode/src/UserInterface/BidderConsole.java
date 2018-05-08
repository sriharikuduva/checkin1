
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.naming.AuthenticationNotSupportedException;

import sun.security.action.GetBooleanAction;

public class BidderConsole {

    private Bidder currBidder;
    private DataControlCenter dataControl;
    private StringBuilder sb;
    private Scanner input;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /** This is for scanning in bidder's inventory */

    public BidderConsole(Bidder currBidder, DataControlCenter dataControl) {
        this.currBidder = currBidder;
        this.dataControl = dataControl;
        this.sb = new StringBuilder();
        this.input = new Scanner(System.in);
    }

    public void invokeMenu() throws ClassNotFoundException, IOException {
        this.sb.append("\nWelcome " + this.currBidder.getName() +
                "! You have been logged in as a Bidder.\n");

        this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
    }
    
//    private void displayOptionsWithCheck() {
//    		this.sb.append("\nHere are your options: \n");
//        if (currBidder.auctions.size() > 0) { // Don't show this option if bidder has no bids.
//    			this.sb.append("\ta) View Auctions I Have Placed Bids On\n");
//        		this.sb.append("\tb) View Items I Have Placed Bids On (In An Auction)\n");
//        		this.sb.append("\tc) View Items I Have Placed Bids On (In All Auctions)\n");
//        }
//        this.sb.append("\td) View Auctions I Can Place Bids On\n");
//        this.sb.append("\te) Bid For An Item In An Auction\n\n");
//        this.sb.append("\tx) Logout and Terminate\n");
//        this.sb.append("Please enter your option letter (and press ENTER): ");
//        System.out.print(this.sb);
//        this.sb.setLength(0);
//    }
    
    private void displayOptions() {
        this.sb.append("\nHere are your options: \n");
        this.sb.append("\ta) View Auctions I Have Placed Bids On\n");
        this.sb.append("\tb) View Items I Have Placed Bids On (In An Auction)\n");
        this.sb.append("\tc) View Items I Have Placed Bids On (In All Auctions)\n");
        this.sb.append("\td) View Auctions I Can Place Bids On\n");
        this.sb.append("\te) Bid For An Item In An Auction\n\n");
        this.sb.append("\tx) Logout and Terminate\n");
        this.sb.append("Please enter your option letter (and press ENTER): ");
        System.out.print(this.sb);
        this.sb.setLength(0);

    }

    private void choiceLogic(Character choice) throws ClassNotFoundException, IOException {
        if (choice == 'a') {
            /** View Auctions I Have Placed Bids On **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);

            StringBuilder sb = new StringBuilder();
            for (Auction auc : result) {
            	int i = 1;
            	sb.append("\t" + i + ". ");
	        		sb.append(auc.getOrganization());
	        		sb.append("\n");
	        		i++;
            }
            System.out.print(sb);
            sb.setLength(0);
            this.revert();
        } else if (choice == 'b') {
            /** View Items I Have Placed Bids On (In An Auction) **/
            /* Need to get the auction choice from the user before hand and pass in as currAuction */
        		
        		HashSet<Auction> auctions = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
        		HashMap<Character, Auction> options = new HashMap<>();
        		
            StringBuilder sb = new StringBuilder();

  			char optionNumber = 'a';
  			for (Auction auc : auctions) {
  				sb.append("\t" + optionNumber + ") ");
  				sb.append(auc.getOrganization());
  				sb.append("\n");
  				options.put(optionNumber, auc);
  				optionNumber++;
  			}
  			
  			System.out.print(sb);
  			System.out.println("\nPlease enter your option letter (and press ENTER): ");
  			char opt = this.input.next().charAt(0);
  			
  			if (options.containsKey(opt)) {
  				HashSet<Item> items =  this.dataControl.getItemsCurrBidderHasBidsOnInAnAuction(currBidder,  options.get(opt));
  				sb = new StringBuilder();
  				for (Item itm : items) {
	              	int i = 1;
	              	sb.append("\t" + i + ". ");
	  	        		sb.append(itm.getName());
	  	        		sb.append("\n");
	  	        		i++;
  				}
  				System.out.print(sb);
  			}
            sb.setLength(0);
            this.revert();
        } else if (choice == 'c') {
            /** View Items I Have Placed Bids On (In All Auctions) **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            StringBuilder sb = new StringBuilder();
	    		for (Auction auc : result) {
                    for (Item item : auc.getItems()) {
                        sb.append(item.toString() + "\n");
                    }
	    		}
	    		System.out.print(sb);
            this.revert();
        } else if (choice == 'd') {
            /** View Auctions I Can Place Bids On **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderCanBidOn(currBidder);
            this.sb.append("You can place bids on these auction(s):\n");
            for (Auction auction : result) {
                this.sb.append(auction.toString());
            }
            this.revert();
        } else if (choice == 'e') {
            /** Bid For An Item In An Auction **/
            /* Need to get the auction choice from the user before hand and pass in as currAuction */
            //this.dataControl.makeBid(currAuction, currBidder);
            //TODO: Go to this.dataControl.makeBid(currAuction, currBidder) and implement logic?
            //TODO: Display verification to user (Pass/ Fail)
        		StringBuilder sb = new StringBuilder();
        		HashSet<Auction> auctions = this.dataControl.getAuctionsCurrBidderCanBidOn(currBidder);
        		HashMap<Character, Auction> auctionOptions = new HashMap<>();
       		System.out.print("You can place bids on these auction(s):\n");
            
			char optionNumber = 'a';
			for (Auction auc : auctions) {
				sb.append("\t" + optionNumber + ") ");
				sb.append(auc.getOrganization());
				sb.append("\n");
				auctionOptions.put(optionNumber, auc);
				optionNumber++;
			}
			
			System.out.print(sb);
			System.out.println("\nPlease enter your option letter (and press ENTER): ");
			char aucOpt = this.input.next().charAt(0);
			
			HashMap<Character, Item> itemOptions = new HashMap<>();
			
			if (auctionOptions.containsKey(aucOpt)) {
				sb = new StringBuilder();
				
				for (Item itm : auctionOptions.get(aucOpt).items) {
					optionNumber = 'a';
	              	sb.append("\t" + optionNumber + ". ");
	  	        		sb.append(itm.getName());
	  	        		sb.append("\n");
	  	        		itemOptions.put(optionNumber, itm);
	  	        		optionNumber++;
				}
				System.out.print(sb);
				System.out.println("\nPlease enter your option letter to place a bid (and press ENTER): ");
				
	  			char itemOpt = this.input.next().charAt(0);
	  			if (itemOptions.containsKey(itemOpt)) {
	  				this.dataControl.makeBid(auctionOptions.get(aucOpt), itemOptions.get(itemOpt), currBidder);
	  			}
			}
			
			
			sb.setLength(0);
            
            //Auction dummyAuction = new Auction();
            //this.dataControl.makeBid(dummyAuction, currBidder);
        		this.revert();
        } else if (choice == 'x') {
            this.dataControl.logOutBidder();
            this.sb.append("You have been logged out, terminating...\n");
        } else if (choice == 'y') {
            /** FOR DEBUGGING PURPOSE, REMOVE THIS ELSEIF BRANCH WHEN SUBMITTING **/
            this.dataControl.placeBidDebugger(currBidder, 50);
            this.revert();
        } else if (choice == 'z') {
            /** FOR DEBUGGING PURPOSE, REMOVE THIS ELSEIF BRANCH WHEN SUBMITTING **/
            for (Auction a : this.dataControl.deserializeAllAuctions()) {
                if (a.getAuctionID() == 5) {
                    Item i = a.getItems().get(0);
                    for (Bid bid : i.getBids()) {
                        System.out.println("Bidder name: " + bid.getBidder() + ", Bid amount = " + bid.getAmount() +
                                ", Item name: " + bid.getItem());
                    }
                }
            }
        }
        System.out.print(this.sb);
        this.sb.setLength(0);
    }

    private void revert() throws ClassNotFoundException, IOException {
        this.sb.append("\n\tr) Revert to main menu" +
                "\nPlease enter your option letter (and press ENTER): ");
        System.out.print(this.sb);
        this.sb.setLength(0);
        if (this.input.next().charAt(0) == 'r') {
            this.displayOptions();
            this.choiceLogic(this.input.next().charAt(0));
        }
    }
}
