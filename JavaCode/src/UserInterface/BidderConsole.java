
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
    private Scanner inputScanner;
    private ArrayList<ItemWrapper> itemsWithAucName = new ArrayList<>();

    public BidderConsole(Bidder currBidder, DataControlCenter dataControl) {
        this.currBidder = currBidder;
        this.dataControl = dataControl;
        this.sb = new StringBuilder();
        this.input = new Scanner(System.in);
        
    }

    public void invokeMenu() throws ClassNotFoundException, IOException {
        this.sb.append("\nWelcome " + this.currBidder.getName() +
                "! You have been logged in as a Bidder.\n");
      
        //Populate bidder's inventory
        String fileName = currBidder.getName();
        
        this.inputScanner = new Scanner(getClass()
        		.getResourceAsStream(fileName + ".txt")); //files are named after bidder's name
        
		while (this.inputScanner.hasNextLine()) {
			String parts[] = this.inputScanner.nextLine().split(",");
			this.cleanParts(parts); //Trim off padding
			ItemWrapper item = new ItemWrapper(parts[0], LocalDateTime.parse(parts[1], formatter), 
					LocalDateTime.parse(parts[2], formatter), parts[3], Integer.parseInt(parts[4].trim()), 
					Integer.parseInt(parts[5].trim()), parts[6], parts[7]);
			itemsWithAucName.add(item);
			//item.toString();
		}
		
		for (ItemWrapper iwan : itemsWithAucName) {
			if (currBidder.auctions.containsKey(iwan.getOrgName())) {
				Item itemToAdd = iwan.getItem();
				currBidder.auctions.get(iwan.getOrgName()).items.add(itemToAdd);
			} else {
				Auction tempAuction = iwan.getAuction();
				tempAuction.items.add(iwan.getItem());
				currBidder.auctions.put(iwan.getOrgName(), tempAuction);
			}
		}
		
        	this.displayOptionsWithCheck();
        //this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
    }
    
    /**
     * Helper method for getting rid the paddings.
     * @param parts
     */
    private void cleanParts(String parts[]) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
    }
    
    private void displayOptionsWithCheck() {
    		this.sb.append("\nHere are your options: \n");
        if (currBidder.auctions.size() > 0) { // Don't show this option if bidder has no bids.
    			this.sb.append("\ta) View Auctions I Have Placed Bids On\n");
        		this.sb.append("\tb) View Items I Have Placed Bids On (In An Auction)\n");
        		this.sb.append("\tc) View Items I Have Placed Bids On (In All Auctions)\n");
        }
        this.sb.append("\td) View Auctions I Can Place Bids On\n");
        this.sb.append("\te) Bid For An Item In An Auction\n\n");
        this.sb.append("\tx) Logout and Terminate\n");
        this.sb.append("Please enter your option letter (and press ENTER): ");
        System.out.print(this.sb);
        this.sb.setLength(0);
    }
    
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

    /*
    [a] - View Auctions I Have Placed Bids On
    [b] - View Items I Have Placed Bids On (In An Auction)
    [c] - View Items I Have Placed Bids On (In All Auctions)
    [d] - View Auctions I Can Place Bids On
    [e] - Bid For An Item In An Auction
     */
    
    private void choiceLogic(Character choice) throws ClassNotFoundException, IOException {
        if (choice == 'a') {
            /** View Auctions I Have Placed Bids On **/
            //HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            //TODO: Go to this.dataControl.getAuctionsCurrBidderHasBids(currBidder) and implement logic
            //TODO: Display result to user
            StringBuilder sb = new StringBuilder();
            //char option = 'a';
        		int i = 1;
            for (Auction auc : currBidder.auctions.values()) {
        			sb.append("\t" + i + ". ");
            		sb.append(auc.getOrganization());
            		sb.append("\n");
            		//option++;
            		i++;
            }
            System.out.print(sb);
            
        		//System.out.print(currBidder.auctions.keySet()); //.entrySet()
            this.revert();
        } else if (choice == 'b') {
            /** View Items I Have Placed Bids On (In An Auction) **/
            /* Need to get the auction choice from the user before hand and pass in as currAuction */
            //Auction dummyAuction = new Auction();
            //this.dataControl.getItemsCurrBidderHasBidsOnInAnAuction(currBidder, dummyAuction);
            //TODO: Go to this.getItemsCurrBidderHasBidsOnInAnAuction(currBidder, currAuction) and implement logic
            //TODO: Display result to user (Items in that specific auction)
            StringBuilder sb = new StringBuilder();
    			char option = 'a';
    			for (Auction auc : currBidder.auctions.values()) {
    				sb.append("\t" + option + ") ");
    				sb.append(auc.getOrganization());
    				sb.append("\n");
    				option++;
    			}
    			System.out.print(sb);
            
            this.revert();
        } else if (choice == 'c') {
            /** View Items I Have Placed Bids On (In All Auctions) **/
            //HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            //TODO: Display result to user (Not the auctions, but items in each auction)
            StringBuilder sb = new StringBuilder();
	    		int num = 1;
	    		for (Auction auc : currBidder.auctions.values()) {
	    			for (Item itm : auc.items) {
		    			sb.append("\t" + num + ". ");
		      		sb.append(itm.getName());
		      		sb.append("\n");
		      		num++;
	    			}
	    		}
	    		System.out.print(sb);
            this.revert();
        } else if (choice == 'd') {
            /** View Auctions I Can Place Bids On **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderCanBidOn(currBidder);
            //TODO: Go to this.dataControl.getAuctionsCurrBidderCanBidOn(currBidder) and implement logic
            //TODO: Display result to user
            this.revert();
        } else if (choice == 'e') {
            /** Bid For An Item In An Auction **/
            /* Need to get the auction choice from the user before hand and pass in as currAuction */
            //this.dataControl.makeBid(currAuction, currBidder);
            Auction dummyAuction = new Auction();
            this.dataControl.makeBid(dummyAuction, currBidder);
            //TODO: Go to this.dataControl.makeBid(currAuction, currBidder) and implement logic?
            //TODO: Display verification to user (Pass/ Fail)
            this.revert();
        } else if (choice == 'x') {
            this.sb.append("You have been logged out, terminating...\n");
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
