import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class NPConsole {
	//ascii value of 'a'
	private static final int CHOICE = 97;

    private NPContact currContact;
    private DataControlCenter dataControl;
    private StringBuilder sb;
    private Scanner input;


    public NPConsole(NPContact currContact, DataControlCenter dataControl) {
        this.currContact = currContact;
        this.dataControl = dataControl;
        this.sb = new StringBuilder();
        this.input = new Scanner(System.in);
    }

    public void invokeMenu() throws IOException, ClassNotFoundException {
        this.sb.append("\nWelcome " + this.currContact.getName() +
                "! You have been logged in as a Non-Profit Contact.\n");
        this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
    }

    private void displayOptions() {
        this.sb.append("\nHere are your options: \n");
        this.sb.append("\ta) View all submitted auction requests\n");
        this.sb.append("\tb) Submit an auction request\n");
        this.sb.append("\n\tx) Logout and Terminate\n");
        this.sb.append("Please enter your option letter (and press ENTER): ");
        System.out.print(this.sb);
        this.sb.setLength(0);
    }

    /* Welcome ContactName! Please make a selection:
    [a] - View all submitted auction requests
    [b] - Submit an auction request
    [x] - Logout/Exit */


    private void choiceLogic(Character choice) throws IOException, ClassNotFoundException {
        if (choice == 'a') {
            /** View all submitted auction requests **/
            HashSet<Auction> result = this.dataControl.getSubmittedAuctionsByNPContact(currContact);
            //TODO: Read existing auctions from file
            //TODO: Go to this.dataControl.getSubmittedAuctionsByNPContact(currContact) and implement logic
            //TODO: Display result to user - Done
            
            viewAuctions(result);
            this.revert();
        } else if (choice == 'b') {
            /** Submit an auction request **/
        	//TODO: Save new auction to file
            new AuctionForm(currContact, this, dataControl).startAuctionApplication();
            this.revert();
        } else if (choice == 'x') {
            this.sb.append("You have been logged out, terminating...\n");
            this.dataControl.logOutNP();
        }
        System.out.print(this.sb);
        this.sb.setLength(0);
    }

    private void revert() throws IOException, ClassNotFoundException {
        this.sb.append("\n\tr) Revert to main menu" +
                "\nPlease enter your option letter (and press ENTER): ");
        System.out.print(this.sb);
        this.sb.setLength(0);
        if (this.input.next().charAt(0) == 'r') {
            this.displayOptions();
            this.choiceLogic(this.input.next().charAt(0));
        }
    }
    
    private void viewAuctions(HashSet<Auction> auctions) {
        sb.append("Here are all of your auctions: \n");
        for(Auction auction : auctions)  {
        	int i = CHOICE;
        	sb.append("\t" + (char) i + ")" + auction.toString());
        	i++;
        }
        sb.append("You may choose an auction to view items. \n");
        sb.append("Please enter your option letter (and press ENTER): ");
    }
}
