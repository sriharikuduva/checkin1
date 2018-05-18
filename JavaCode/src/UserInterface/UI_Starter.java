import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** UI Kickstart of the program.
 * BIN FILES MUST EXIST BEFORE EXECUTION!
 * @author Hari Kuduva
 */
public class UI_Starter {
    /** Main Data Control. **/
    private static DataControlCenter dataControl;
    private static StringBuilder stringBuilder;
    private static Scanner input;

    /** Private constructor to prevent instantiation.*/
    private UI_Starter() { }


    public static void main(String... args) throws IOException, ClassNotFoundException {
        dataControl = new DataControlCenter();	
        login();
        
//        initVariables();
//        invokeLoginScreen();
//        
//        input.close();
    }
    
    private static void login() throws ClassNotFoundException, IOException{
    	JFrame frame = new JFrame("Auction Central");
    	
    	String[] userTypeOptions = {"Bidder", "Non-Profit Contact", "Auction Central Employee"};
    	
    	Object userTypeInput = JOptionPane.showInputDialog(frame, "What type of user are you?", "Choose User Type", JOptionPane.CANCEL_OPTION, null, userTypeOptions, "None");
    	
    	String userType = (String) userTypeInput;

    	String username;
    	username = (String) JOptionPane.showInputDialog(frame, "Please Enter Your Username: ", "Login to Auction Central", JOptionPane.OK_CANCEL_OPTION);
    	verifyAndLaunchSpecificUser(userType, username);
    }
    
    private static void verifyAndLaunchSpecificUser(String usertype, String username) throws IOException, ClassNotFoundException {
    	switch (usertype) {
    	case "Bidder":
    		if(dataControl.isBidderValid(username)) {
    			new BidderConsole(dataControl.getBidderByUsername(username), dataControl).invokeMenu();
    		}
    	case "Non-Profit Contact":
    		if(dataControl.isNonProfitValid(username)) {
    			new NPConsole(dataControl.getNPContactByUsername(username), dataControl).invokeMenu();
    		}
    	case "Auction Central Employee":
    		//dataControl.isAuctionCentralValid(username);
    	}
    }

//    /** Initialize variables here. **/
//    private static void initVariables() throws IOException, ClassNotFoundException {
//        dataControl = new DataControlCenter();
//        stringBuilder = new StringBuilder();
//        input = new Scanner(System.in);
//    }

//    /** Invokes the login screen for the user.
//     * @throws IOException exception risk
//     * @throws ClassNotFoundException exception risk */
//    private static void invokeLoginScreen() throws IOException, ClassNotFoundException {
//        stringBuilder.append("Welcome User!\n");
//        stringBuilder.append("Here are your options: \n");
//        stringBuilder.append("\ta) Bidder Sign In\n");
//        stringBuilder.append("\tb) Non Profit Sign In\n");
//        stringBuilder.append("\nPlease enter your option letter (and press ENTER): ");
//        System.out.print(stringBuilder);
//        boolean isNonProfit = input.next().equals("b");
//        stringBuilder.setLength(0); // clear out StringBuilder
//        stringBuilder.append("\nPlease enter your username (and press ENTER): ");
//        System.out.print(stringBuilder);
//        stringBuilder.setLength(0);
//        String username = input.next();
//        verifyAndLaunchSpecificUser(isNonProfit, username);
//    }

//    /** Verification of the user login cred and lauches a specifc console.
//     * @param isNonProfit true if user is NPContact
//     * @param username username of user
//     * @throws IOException exception risk
//     * @throws ClassNotFoundException exception risk */
//    private static void verifyAndLaunchSpecificUser(boolean isNonProfit, String username) throws IOException, ClassNotFoundException {
//        if (isNonProfit && dataControl.isNonProfitValid(username)) {
//            new NPConsole(dataControl.getNPContactByUsername(username), dataControl).invokeMenu();
//        } else if (!isNonProfit && dataControl.isBidderValid(username)) {
//            new BidderConsole(dataControl.getBidderByUsername(username), dataControl).invokeMenu();
//        } else {
//            stringBuilder.append("\nYou have entered an invalid login credential... terminating.");
//            System.out.print(stringBuilder);
//        }
//    }
}
