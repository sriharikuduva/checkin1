import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.*;
import java.util.*;

public class AuctionForm {
	//These values can be adjusted per company policy.
	private static final int MAX_AUCTIONS_PER_DAY = 2;
	//Auctions will not be scheduled beyond this limit.
	private static final int MAX_SCHEDULE_OUT_DAYS = 60;
	
	//Set with MAX_SCHEDULE_OUT_DAYS
	private LocalDate farthestDate;
	
    private NPContact currContact;
    private StringBuilder sb;
    
    private Auction newAuction;
    
    private Scanner input;
    
    private NPConsole npConsole;
    private DateTimeFormatter fmt;
    private String requestedDate;

    public AuctionForm(NPContact currcontact, NPConsole npConsole) {
        this.currContact = currcontact;
        farthestDate = LocalDate.now().plusDays(MAX_SCHEDULE_OUT_DAYS);
        //farthestDate = LocalDateTime.now().plusDays(MAX_SCHEDULE_OUT_DAYS);
        sb = new StringBuilder();
        
        newAuction = new Auction(currContact.getName());
        
        input = new Scanner(System.in);
        
        //"Go Back" location
        this.npConsole = npConsole;
        
        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        requestedDate = LocalDateTime.now().toString();
        
    }

    public void startAuctionApplication() {
        //System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        //TODO: Needs implementation on auction creation process
        
        sb.append("Welcome to the Auction Creation Form! \n");
        System.out.println(sb.toString());
        setAuctionDate();
    }
    
    public void setAuctionDate() {
    	sb.setLength(0);
    	String farthestDateFormatted = farthestDate.format(fmt);
        sb.append("We are currently accepting auctions up through " + farthestDateFormatted + ". \n");
        //sb.append("We are currently accepting auctions up through " + farthestDate + ". \n");
    	sb.append("Please enter your desired auction date below. \n");
    	sb.append("\n");
        sb.append("Enter your date as follows: MM.DD.YY HH:MMam/pm (ex: 5/6/18 3:44 PM) \n");
        System.out.print(sb.toString());
        
        requestedDate= input.next();

        
        if(requestedDate.equals("r")) {
        	npConsole.invokeMenu();
        } else if(!requestedDate.contains(" : ")) {
        	sb.setLength(0);
        	sb.append("Please include a start time for your auction.");
        	setAuctionDate();
        }

        //Check that date is within farthestDate limit.
        if(isRequestedAuctionDateAvailable(requestedDate)) {
        	setAuctionTime();
        }
    }
    
    public boolean isRequestedAuctionDateAvailable(String date) {
    	boolean available = true;
    	//probably a System check
//    	if(numberOfExistingAuctionsOnDate(date) < MAX_AUCTIONS_PER_DAY) {
//    		return false;
//    	} 
    	
    	return available;
    }
    
    public int numberOfAvailableAuctionTimes(String date) {
    	return 1;
    }
    
    public void setAuctionTime() {
    	//display available auction times for date
    	
    	//back to date selection option in case user doesn't like times available
//    	if(false) {
//    		setAuctionDate();
//    	}
    	
    	if(isRequestedAuctionTimeAvailable()) {
        	confirmAuctionDateTime(requestedDate);
    	}
    }
    
    //Controller
    public boolean isRequestedAuctionTimeAvailable() {
    	boolean available = true;
    	return available;
    }
    
    public void confirmAuctionDateTime(String date) {
    	sb.append("Your Auction is scheduled for: " + date + ". Thank you! \n");
    	npConsole.invokeMenu();
    }
}