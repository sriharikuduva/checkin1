//Shannon
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.*;
import java.util.*;

public class AuctionForm {
	//These values can be adjusted per company policy.
	//Auctions will not be scheduled beyond this limit.
	private static final int MAX_SCHEDULE_OUT_DAYS = 60;
	private static final int MIN_SCHEDULE_OUT_DAYS = 14;
	
	//Set by constants
	private LocalDate farthestDate;
	private LocalDate nearestDate;
	
	//input from NPConsole
    private NPConsole npConsole;
    private NPContact currContact;
    private DataControlCenter dataControl;
    
    private StringBuilder sb;
    private Scanner input;
    private DateTimeFormatter fmt;
    private String requestedDateAndTime;

    public AuctionForm(NPContact currcontact, NPConsole npConsole, DataControlCenter dataControl) {
        this.currContact = currcontact;
        farthestDate = LocalDate.now().plusDays(MAX_SCHEDULE_OUT_DAYS);
        nearestDate = LocalDate.now().plusDays(MIN_SCHEDULE_OUT_DAYS);
        sb = new StringBuilder();
        
        input = new Scanner(System.in);
        
        //"Go Back" location
        this.npConsole = npConsole;
        this.dataControl = dataControl;
        
        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        requestedDateAndTime = "";
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
    	String nearestDateFormatted = nearestDate.format(fmt);

        sb.append("We are currently accepting auctions from "+ nearestDateFormatted + " up to " + farthestDateFormatted + ". \n");
    	sb.append("Please enter your desired auction date below. \n");
    	//sb.append("\n");
        sb.append("Enter your date as follows: MM/DD/YY HH:MM AM/PM (ex: 5/6/18 3:44 PM) \n");
        sb.append("\tr) Return to Non-Profit Contact Main Menu. \n");
        System.out.print(sb.toString());
        sb.setLength(0);
    	
        requestedDateAndTime = input.nextLine();
        
        String[] parts = requestedDateAndTime.split(" ");
        //This is not a magic number, it is based on the required input to parse the date and time.
        while(parts.length < 3) {
        	sb.append("**ERROR**Please use format: MM/DD/YY HH:MM AM/PM (ex: 5/6/18 3:44 PM) \n \n");

            sb.append("We are currently accepting auctions up through " + farthestDateFormatted + ". \n");
        	sb.append("Please enter your desired auction date below. \n");
        	//sb.append("\n");
            sb.append("Enter your date as follows or revert: MM/DD/YY HH:MM AM/PM (ex: 5/6/18 3:44 PM) \n");
            sb.append("\tr) Revert to main menu. \n");
            System.out.print(sb.toString());
            sb.setLength(0);
            
            requestedDateAndTime = input.nextLine();
            parts = requestedDateAndTime.split(" ");
        }
        
        checkAuction(parts);
    }

    //Unit Test
    public void checkAuction(String[] parts) {
    	boolean valid = dataControl.isRequestedAuctionDateValid(parts);
    	if(valid) {
        	boolean available = dataControl.isRequestedAuctionDateAvailable(parts);
        	if(available) {
        		checkTime(parts);
        	} else {
        		System.out.println("Your Date is valid, your time is not available. Please Try Again.");
        		setAuctionDate();
        	}
    	} else {
    		//Date is too soon or too far away
    		System.out.println("Your Date is too soon or too far away. Please try again");
    		setAuctionDate();
    	}
    }
    
    public void checkTime(String[] parts) {
    	boolean available = dataControl.isTimeAvailable(parts);
    	if(available) {
    		confirmAuctionDateTime(parts);
    	}
    }
  
    
    public void confirmAuctionDateTime(String[] parts) {
    	String date = "";
    	for(int i = 0; i < parts.length; i++) {
    		date = date + parts[i];
    	}
    	sb.append("Your Auction is scheduled for: " + date + ". Thank you! \n");
    	createAuction(parts);
    	sb.append("Your Auction is now available online.");

    	System.out.println(sb.toString());
    	
    	npConsole.invokeMenu();
    }
    
    public void createAuction(String[] parts) {
    	
    	String date = parts[0];
    	String time = parts[1];
    	time.concat(parts[2]);
    	
    	Auction auction = new Auction();
    	auction.setOrganization(currContact.getName());
    	
    	LocalDateTime start = LocalDateTime.now();
    	
    	//This needs to be the given date
    	LocalDateTime end = LocalDateTime.now();
    	
    	auction.setStart(start);
    	auction.setEnd(end);

    	dataControl.addAuction(auction);
    	
    }
}