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
        this.sb.append("\nWelcome " + this.currBidder.getName() + "!\n");
        displayOptions();
        System.out.print(this.sb);
        this.sb.setLength(0);
        this.choiceLogic(this.input.next().charAt(0));
    }

    private void displayOptions() {
        this.sb.append("Here are your options: \n");
        this.sb.append("\ta) View Auctions I Have Placed Bids On\n");
        this.sb.append("\tb) View Items I Have Placed Bids On (In An Auction)\n");
        this.sb.append("\tc) View Items I Have Placed Bids On (In All Auctions)\n");
        this.sb.append("\td) View Auctions I Can Place Bids On\n");
        this.sb.append("\te) Bid For An Item In An Auction\n\n");
        this.sb.append("\tx) Logout and Terminate\n");
        this.sb.append("Please enter your option letter (and press ENTER): ");

    }
    
    
    private void choiceLogic(Character choice) {
        if (choice == 'a') {
            HashSet<Auction> result =
                    this.dataControl.getAuctionsCurrBidderHasBids(currBidder);
            //TODO: Take the set and display list of auctions




        } else if (choice == 'b') {
            
        } else if (choice == 'c') {
            
        } else if (choice == 'd') {
            
        } else if (choice == 'e') {
            
        } else if (choice == 'x') {
            this.sb.append("You have been logged out, terminating...");
        }
        System.out.print(this.sb);
    }
}
