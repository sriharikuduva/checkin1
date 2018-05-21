
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

/** Console for the logged in Bidder.
 * @author Hari Kuduva */
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

    /** Invokes the main menu for the bidder.
     * @throws ClassNotFoundException exception risk
     * @throws IOException exception risk */
    public void invokeMenu() throws ClassNotFoundException, IOException {
        this.sb.append("\nWelcome " + this.currBidder.getName() +
                "! You have been logged in as a Bidder.\n");

        this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
    }

    /** Display the bidders options. **/
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

    /** Choice logic to decide the behavior
     * @param choice determines the behavior
     * @throws ClassNotFoundException exception risk
     * @throws IOException exception risk */
    private void choiceLogic(Character choice) throws ClassNotFoundException, IOException {
        if (choice == 'a') {
            /** View Auctions I Have Placed Bids On **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (Auction auc : result) {
            	
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
  			
  			System.out.print("\nPlease enter your option letter (and press ENTER): ");
  			char opt = this.input.next().charAt(0);
  			
  			if (options.containsKey(opt)) {
  				HashSet<Item> items =  this.dataControl.getItemsCurrBidderHasBidsOnInAnAuction(currBidder,  options.get(opt));
  				sb = new StringBuilder();
  				sb.append("Here are the items you have bids on in the auction: " + options.get(opt).getOrganization() + "\n");
  				int i = 1;
  				for (Item itm : items) {
	              	
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
            int i = 1;
            for (Auction auc : result) {
	    			    
	    			for (Item item : auc.getItems()) {
                        
                    		for (Bid bid : item.getBids()) {
                            if (bid.getBidder().equals(currBidder.getName())) {
                                sb.append("\t" + i + ". ");
                            		sb.append(item.toString() + "\n");
                            		i++;
                            }
                        }
                        //sb.append(item.toString() + "\n");
                    }
	    		}
	    		System.out.print(sb);
            this.revert();
        } else if (choice == 'd') {
            /** View Auctions I Can Place Bids On **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderCanBidOn(currBidder);
            this.sb.append("You can place bids on these auction(s):\n");
            int i = 1;
            for (Auction auction : result) {
                this.sb.append("    " + i + ". ");
            		this.sb.append(auction.toString());
            		i++;
            }
            this.revert();
        } else if (choice == 'e') {
            /** Bid For An Item In An Auction **/
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
				
				optionNumber = 'a';
				for (Item itm : auctionOptions.get(aucOpt).items) {
	              	sb.append("\t" + optionNumber + ") ");
	  	        		sb.append(itm.getName());
	  	        		sb.append("\n");
	  	        		itemOptions.put(optionNumber, itm);
	  	        		optionNumber++;
				}
				System.out.print(sb);
				System.out.println("\nPlease enter your option letter to place a bid (and press ENTER): ");
				
	  			char itemOpt = this.input.next().charAt(0);
	  			if (itemOptions.containsKey(itemOpt)) {
	  				System.out.println("Name: " + itemOptions.get(itemOpt).getName() 
	  						+ "\tQuantity: " + itemOptions.get(itemOpt).getQuantity() + "\n" 
	  						+ "Description: " + itemOptions.get(itemOpt).getDescription() + "\n"
	  						+ "Starting Bid: " + itemOptions.get(itemOpt).getCurrentBid() + "\n");
	  				System.out.println("\nPlease enter the amount you would like to bid for this item (and press ENTER): ");
	  				
	  				int bidPrice = this.input.nextInt();
	  				Bid bid = new Bid(currBidder.getName(), itemOptions.get(itemOpt).getName(), bidPrice);
	  				
	  				this.dataControl.makeBid(auctionOptions.get(aucOpt), auctionOptions.get(aucOpt).items, itemOptions.get(itemOpt), bid, currBidder);
	  			}
			}
			sb.setLength(0);
        		this.revert();
        } else if (choice == 'x') {
            this.dataControl.logOutBidder();
            this.sb.append("You have been logged out, terminating...\n");
        }
        System.out.print(this.sb);
        this.sb.setLength(0);
    }

    /** Reverts back the the main bidder menu.
     * @throws ClassNotFoundException exception risk
     * @throws IOException exception risk */
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
