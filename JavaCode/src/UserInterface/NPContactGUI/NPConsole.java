import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * @author Shannon Weston
 * @version 5/7/2018
 */
public class NPConsole {
	//ascii value of 'a'
	private static final int CHOICE = 97;

    private NPContact currContact;
    private DataControlCenter dataControl;
    private StringBuilder sb;
    private Scanner input;
    private int choice_int;

    public NPConsole(NPContact currContact, DataControlCenter dataControl) {
        this.currContact = currContact;
        this.dataControl = dataControl;
        this.sb = new StringBuilder();
        this.input = new Scanner(System.in);
        this.choice_int = CHOICE;
    }

    /** Invokes the main menu for the NPContact.
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
    public void invokeMenu() throws IOException, ClassNotFoundException {
        this.sb.append("\nWelcome " + this.currContact.getName() +
                "! You have been logged in as a Non-Profit Contact.\n");
        this.displayOptions();
        this.choiceLogic(this.input.next().charAt(0));
    }

    /** Display options for NPContact. **/
    private void displayOptions() {
        this.sb.append("\nHere are your options: \n");
        this.sb.append("\ta) View all submitted auction requests\n");
        this.sb.append("\tb) Submit an auction request\n");
        this.sb.append("\n\tx) Logout and Terminate\n");
        this.sb.append("Please enter your option letter (and press ENTER): ");
        System.out.print(this.sb);
        this.sb.setLength(0);
    }

    /** Choice logic to determine behavior.
     * @param choice determines behavior
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
    private void choiceLogic(Character choice) throws IOException, ClassNotFoundException {
        if (choice == 'a') {
            /** View all submitted auction requests **/
            HashSet<Auction> result = this.dataControl.getSubmittedAuctionsByNPContact(currContact);
            viewAuctions(result);
            //this.revert();
        } else if (choice == 'b') {
            new AuctionForm(currContact, this, dataControl).startAuctionApplication();
            this.revert();
        } else if (choice == 'x') {
            this.sb.append("You have been logged out, terminating...\n");
            this.dataControl.logOutNP();
        }
        System.out.print(this.sb);
        this.sb.setLength(0);
    }

    /** Reverts to the main NPContact menu.
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
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

    /**Displays the the NPContact their auction set.
     * @param auctions the auction set to display 
     * @throws IOException 
     * @throws ClassNotFoundException */
    private void viewAuctions(HashSet<Auction> auctions) throws ClassNotFoundException, IOException {
        sb.append("Here are all of your auctions: \n");
        choice_int = CHOICE;
        for(Auction auction : auctions)  {
        	sb.append("\t" + (char) choice_int + ")" + auction.toString());
        	choice_int++;
        }
        sb.append("You may choose an auction to view items.");
        this.revert();
    }
    
    //Calling this method and passing in an auction will add the items attached to this auction to the string builder.
    private void showItemsFromAuction(Auction auction) throws ClassNotFoundException, IOException {
    	for(Auction a : dataControl.getAuctions()) {
    		for(Item i : a.getItems()) {
    			this.sb.append(i.toString());
    		}
    	}
    	System.out.print(this.sb.toString());
    }
}