//Shannon
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.IOException;
import java.time.*;
import java.util.*;

public class AuctionForm {
	//These values can be adjusted per company policy.
	//Auctions will not be scheduled beyond this limit.
	private static final int MAX_SCHEDULE_OUT_DAYS = 60;
	private static final int MIN_SCHEDULE_OUT_DAYS = 14;
	
	//Set by static constants 
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
    private LocalDateTime inputDate;
    private LocalDateTime endDate;

    public AuctionForm(NPContact currcontact, NPConsole npConsole, DataControlCenter dataControl) {
        this.currContact = currcontact;
        this.npConsole = npConsole;
        this.dataControl = dataControl;
        
        farthestDate = LocalDate.now().plusDays(MAX_SCHEDULE_OUT_DAYS);
        nearestDate = LocalDate.now().plusDays(MIN_SCHEDULE_OUT_DAYS);
        sb = new StringBuilder();
        input = new Scanner(System.in);
        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        requestedDateAndTime = "";
        inputDate = LocalDateTime.now();
        endDate = LocalDateTime.now();
    }

    public void startAuctionApplication() throws IOException, ClassNotFoundException {
        //System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        //TODO: Needs implementation on auction creation process
        
        sb.append("\nWelcome to the Auction Creation Form! \n");
        System.out.println(sb.toString());
        setAuctionDate();
    }
    
    public void setAuctionDate() throws IOException, ClassNotFoundException {
    	sb.setLength(0);
    	String farthestDateFormatted = farthestDate.format(fmt);
    	String nearestDateFormatted = nearestDate.format(fmt);

        sb.append("We are currently accepting auctions from "+ nearestDateFormatted + " up to " + farthestDateFormatted + ". \n");
        sb.append("Enter your date as follows or go back: MM/DD/YYYY HH:MM AM/PM (ex: 5/6/2018 3:44 PM) \n");
        sb.append("\tr) Return to Non-Profit Contact Main Menu. \n");
        sb.append("Requested Date: ");
        System.out.print(sb.toString());
        sb.setLength(0);
    	
        requestedDateAndTime = input.nextLine();
        
        String[] parts = requestedDateAndTime.split(" ");

        //This is not a magic number, it is based on the required input to parse the date and time.
        while(parts.length < 3) {
        	sb.append("\n**ERROR**\n ");

            sb.append("We are currently accepting auctions up through " + farthestDateFormatted + ". \n");
            sb.append("Enter your date as follows or go back: MM/DD/YYYY HH:MM AM/PM (ex: 5/6/2018 3:44 PM) \n");
            sb.append("\tr) Revert to main menu. \n");
            sb.append("Requested Date: ");
            System.out.print(sb.toString());
            sb.setLength(0);
            
            requestedDateAndTime = input.nextLine();
            parts = requestedDateAndTime.split(" ");
        }
        
        formatTime(parts);
        
        getDuration();
        
        checkAuction();

        confirmAuctionDateTime();
    }
    
    public void getDuration() {
    	sb.setLength(0);
    	sb.append("How long would you like your auction to run? \n");
    	sb.append("Please insert the duration of your auction in hours (ex: 4) \n");
    	sb.append("Auction Duration: ");
    	System.out.print(sb.toString());
    	int hours = input.nextInt();
    	
    	endDate = inputDate.plusHours(hours);
    }
    
    
    public void formatTime(String[] parts) {
    	String[] temp = parts[0].split("/");
        int[] monthDayYear = new int[3];
        for(int i = 0; i < temp.length; i++) {
        	monthDayYear[i] = Integer.parseInt(temp[i]);
        }

        String[] timeTemp = parts[1].split(":");
        
        int[] hourMinute = new int[timeTemp.length];
        for(int i = 0; i < timeTemp.length; i++) {
        	Integer integer = Integer.valueOf(timeTemp[i]);
        	hourMinute[i] = integer.intValue();
        }
        
        int hour = get24Hour(hourMinute[0], parts[2]);
        
        inputDate = LocalDateTime.of(monthDayYear[2], monthDayYear[0], monthDayYear[1], hour, hourMinute[1]);
    }
    
    /**Converts time given in 12 hour time to 24 hour time.*/
    public int get24Hour(int i, String s) {
    	int hour = 0;
    	if(s.contains("P") && i <= 12) {
    		hour = i + 12;
    	} else {
    		hour = i;
    	}
    	return hour;
    }

    //Unit Test
    public void checkAuction() throws IOException, ClassNotFoundException {
    	boolean valid = dataControl.isRequestedAuctionDateValid(inputDate);
    	if(valid) {
        	boolean available = dataControl.isRequestedAuctionDateAvailable(inputDate, endDate);
        	if(!available) {
        		System.out.println("Your date or time is unavailable. Please try again. \n");
        	}
    	} else {
    		//Date is too soon or too far away
    		System.out.println("Your requested date is too soon or too far away. Please try again");
    		setAuctionDate();
    	}
    }
    
    public void confirmAuctionDateTime() throws IOException, ClassNotFoundException {
    	String date = "";
    	
    	String inputFormatted = inputDate.format(fmt);
    	sb.append("Your Auction is scheduled for: " + inputFormatted.toString() + ". Thank you! \n");
    	createAuction();
    	//Auction available for online bidding LocalDateTime.now().plusDays(MIN_SCHEDULE_OUT_DAYS);
    	System.out.println(sb.toString());
    	
    	npConsole.invokeMenu();
    }
    
    public void createAuction() throws ClassNotFoundException, IOException {
    	Auction auction = new Auction();
    	auction.setOrganization(currContact.getName());
    	
    	LocalDateTime startOnline = LocalDateTime.now().plusDays(MIN_SCHEDULE_OUT_DAYS);
    	LocalDateTime start = inputDate;
    	
    	//This needs to be the given date
    	LocalDateTime end = endDate;
    	
    	auction.setStart(start);
    	auction.setOnlineStart(startOnline);
    	auction.setEnd(end);

    	dataControl.addAuction(auction);
    }
}