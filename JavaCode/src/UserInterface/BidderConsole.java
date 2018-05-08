
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
		
        //this.displayOptionsWithCheck();
        this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
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

    private void choiceLogic(Character choice) throws ClassNotFoundException, IOException {
        if (choice == 'a') {
            /** View Auctions I Have Placed Bids On **/
            HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            for (Auction auction : result) {
                sb.append(auction.toString());
            }
            System.out.print(sb);
            sb.setLength(0);
            this.revert();
        } else if (choice == 'b') {
            /** View Items I Have Placed Bids On (In An Auction) **/
            /* Need to get the auction choice from the user before hand and pass in as currAuction */

            //Auction dummyAuction = new Auction();
            //this.dataControl.getItemsCurrBidderHasBidsOnInAnAuction(currBidder, dummyAuction);
            /*StringBuilder sb = new StringBuilder();
    			char option = 'a';
    			for (Auction auc : currBidder.auctions.values()) {
    				sb.append("\t" + option + ") ");
    				sb.append(auc.getOrganization());
    				sb.append("\n");
    				option++;
    			}
    			System.out.print(sb);*/
            
            this.revert();
        } else if (choice == 'c') {
            /** View Items I Have Placed Bids On (In All Auctions) **/
            //HashSet<Auction> result = this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
           // HashSet<Auction>
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
            this.sb.append("You can place bids on these auction(s):\n");
            for (Auction auction : result) {
                this.sb.append(auction.toString());
            }
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
