import java.util.HashSet;
import java.util.Scanner;

public class BidderConsole {

    private Bidder currBidder;
    private DataControlCenter dataControl;
    private StringBuilder sb;
    private Scanner input;

    public BidderConsole(Bidder currBidder, DataControlCenter dataControl) {
        this.currBidder = currBidder;
        this.dataControl = dataControl;
        this.sb = new StringBuilder();
        this.input = new Scanner(System.in);
    }

    public void invokeMenu() {
        this.sb.append("\nWelcome " + this.currBidder.getName() +
                "! You have been logged in as a Bidder.\n");
        this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
    }

    private void displayOptions() {
        this.sb.append("\nHere are your options: \n");
        this.sb.append("\ta) View Auctions I Have Placed Bids On\n");
        this.sb.append("\tb) View Items I Have Placed Bids On (In An Auction)\n");
        this.sb.append("\tc) View Items I Have Placed Bids On (In All Auctions)\n");
        this.sb.append("\td) View Auctions I Can Place Bids On\n");
        this.sb.append("\te) Bid For An Item In An Auction\n\n");
        this.sb.append("\tx) Logout and Terminate\n");
        this.sb.append("\nPlease enter your option letter (and press ENTER): ");
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
    
    private void choiceLogic(Character choice) {
        if (choice == 'a') {
            /** View Auctions I Have Placed Bids On **/
            HashSet<Auction> result =
                    this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            //TODO: Go to this.dataControl.getAuctionsCurrBidderHasBids(currBidder) and implement logic
            //TODO: Display result to user
            this.revert();
        } else if (choice == 'b') {
            /** View Items I Have Placed Bids On (In An Auction) **/
            /* Need to get the auction choice from the user before hand and pass in as currAuction */
            Auction dummyAuction = new Auction();
            this.dataControl.getItemsCurrBidderHasBidsOnInAnAuction(currBidder, dummyAuction);
            //TODO: Go to this.getItemsCurrBidderHasBidsOnInAnAuction(currBidder, currAuction) and implement logic
            //TODO: Display result to user (Items in that specific auction)
            this.revert();
        } else if (choice == 'c') {
            /** View Items I Have Placed Bids On (In All Auctions) **/
            HashSet<Auction> result =
                    this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            //TODO: Display result to user (Not the auctions, but items in each auction)
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

    private void revert() {
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
