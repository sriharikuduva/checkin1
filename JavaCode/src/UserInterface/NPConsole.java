import java.util.HashSet;
import java.util.Scanner;

public class NPConsole {

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


    public void invokeMenu() {
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


    private void choiceLogic(Character choice) {
        if (choice == 'a') {
            /** View all submitted auction requests **/
            HashSet<Auction> result = this.dataControl.getSubmittedAuctionsByNPContact(currContact);
            //TODO: Go to this.dataControl.getSubmittedAuctionsByNPContact(currContact) and implement logic
            //TODO: Display result to user
            this.revert();
        } else if (choice == 'b') {
            /** Submit an auction request **/
            new AuctionForm(currContact, this).startAuctionApplication();
            //TODO: Go to AuctionForm.startAuctionApplication and implement logic
            //TODO: Diplay auction creation success/failiure to user
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
