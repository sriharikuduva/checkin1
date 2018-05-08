
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.IOException;
import java.time.*;
import java.util.*;

/**
 * Creates an auction from user input and adds it to the master list of auctions.
 * @author Shannon Weston
 * @version 5/7/2018
 */
public class AuctionForm {
	//These values need to be adjusted per company policy.
	private static final int MAX_SCHEDULE_OUT_DAYS = 60;
	private static final int MIN_SCHEDULE_OUT_DAYS = 14;
	
	private LocalDate farthestDate;
	private LocalDate nearestDate;
	
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

    /** Starts the auction application process.
     * @throws IOException the exception risk
     * @throws ClassNotFoundException the exception risk */
    public void startAuctionApplication() throws IOException, ClassNotFoundException {
        sb.append("\nWelcome to the Auction Creation Form! \n");
        System.out.println(sb.toString());
        setAuctionDate();
    }

    /** Sets the auction date.
     * @throws IOException the exception risk
     * @throws ClassNotFoundException the exception risk */
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

    /** Gets the duration of the auction from the user. **/
    public void getDuration() {
    	sb.setLength(0);
    	sb.append("How long would you like your auction to run? \n");
    	sb.append("Please insert the duration of your auction in hours (ex: 4) \n");
    	sb.append("Auction Duration: ");
    	System.out.print(sb.toString());
    	int hours = input.nextInt();
    	
    	endDate = inputDate.plusHours(hours);
    }

    /** Formats the given time
     * @param parts the time data to be formatted */
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

    /** Checks the auction to see if it is available for scheduling.
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
    public void checkAuction() throws IOException, ClassNotFoundException {
    	boolean valid = dataControl.isRequestedAuctionDateValid(inputDate);
    	if(valid) {
        	boolean available = dataControl.isRequestedAuctionDateAvailable(inputDate, endDate);
        	if(!available) {
        		System.out.println("Your date or time is unavailable. Please try again. \n");
        	}
    	} else {
    		System.out.println("Your requested date is too soon or too far away. Please try again");
    		setAuctionDate();
    	}
    }

    /** Displays confirmation to the user that their auction has been scheduled.
     * @throws IOException exception risk
     * @throws ClassNotFoundException exception risk */
    public void confirmAuctionDateTime() throws IOException, ClassNotFoundException {
    	String inputFormatted = inputDate.format(fmt);
    	sb.append("Your auction is scheduled for: " + inputFormatted.toString() + ". Thank you! \n");
    	createAuction();
    	sb.append("Your auction will be open for online bidding on: " + inputDate.plusDays(MIN_SCHEDULE_OUT_DAYS).format(fmt));
    	System.out.println(sb.toString());
    	npConsole.invokeMenu();
    }

    /** Creates the auction with the info gathered from the user. **/
    public void createAuction() {
    	Auction auction = new Auction();
    	auction.setOrganization(currContact.getName());
    	auction.setAuctionId(this.dataControl.getNextAvailableAuctionId());
    	
    	LocalDateTime startOnline = LocalDateTime.now().plusDays(MIN_SCHEDULE_OUT_DAYS);
    	LocalDateTime start = inputDate;
    	LocalDateTime end = endDate;
    	
    	auction.setStart(start);
    	auction.setOnlineStart(startOnline);
    	auction.setEnd(end);

    	dataControl.addAuction(auction);
    }
}