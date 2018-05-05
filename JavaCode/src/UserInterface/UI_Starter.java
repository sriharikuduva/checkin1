import javax.xml.crypto.Data;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UI_Starter {

    private static DataControlCenter dataControl;
    private static StringBuilder stringBuilder;
    private static Scanner input;
    private static boolean isLoggedIn;
    private static boolean isBidder;


    /** Private constructor to prevent instantiation.*/
    private UI_Starter() { }

    public static void main(String... args) {
        initVariables();
        invokeLoginScreen();
        if (isLoggedIn) {
            if (isBidder) {
                invokeBidderMenu();
            } else {
                invokeNPMenu();
            }
        } else {
            stringBuilder.append("\nYou have entered an invalid login credential... terminating.");
            System.out.print(stringBuilder);
        }
    }

    private static void initVariables() {
        dataControl = new DataControlCenter();
        stringBuilder = new StringBuilder();
        input = new Scanner(System.in);
    }

    private static void invokeLoginScreen() {
        stringBuilder.append("Welcome User!\n");
        stringBuilder.append("Here are your options: \n");
        stringBuilder.append("\t1) Bidder Sign In\n");
        stringBuilder.append("\t2) Non Profit Sign In\n");
        stringBuilder.append("\nPlease enter your option number (and press ENTER): ");
        System.out.print(stringBuilder);
        boolean isNonProfit = input.nextInt() == 2;
        stringBuilder.setLength(0); // clear out StringBuilder
        stringBuilder.append("\nPlease enter your username (and press ENTER): ");
        System.out.print(stringBuilder);
        stringBuilder.setLength(0);
        String username = input.next();
        isLoggedIn = (verifyLogin(isNonProfit, username));
    }

    private static boolean verifyLogin(boolean isNonProfit, String username) {
        isBidder = (isNonProfit) ? false : true;
        if (isNonProfit) {
            return dataControl.isNonProfitValid(username);
        }
        return dataControl.isBidderValid(username);
    }

    private static void invokeBidderMenu() {
        
    }

    private static void invokeNPMenu() {

    }
}
