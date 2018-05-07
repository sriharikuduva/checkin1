import java.io.IOException;
import java.util.Scanner;

public class UI_Starter {

    private static DataControlCenter dataControl;
    private static StringBuilder stringBuilder;
    private static Scanner input;

    /** Private constructor to prevent instantiation.*/
    private UI_Starter() { }


    public static void main(String... args) throws IOException, ClassNotFoundException {
        initVariables();
        invokeLoginScreen();
        input.close();
        stringBuilder.setLength(0);
    }

    private static void initVariables() throws IOException {
        dataControl = new DataControlCenter();
        stringBuilder = new StringBuilder();
        input = new Scanner(System.in);
    }

    private static void invokeLoginScreen() throws IOException, ClassNotFoundException {
        stringBuilder.append("Welcome User!\n");
        stringBuilder.append("Here are your options: \n");
        stringBuilder.append("\ta) Bidder Sign In\n");
        stringBuilder.append("\tb) Non Profit Sign In\n");
        stringBuilder.append("\nPlease enter your option letter (and press ENTER): ");
        System.out.print(stringBuilder);
        boolean isNonProfit = input.next().equals("b");
        stringBuilder.setLength(0); // clear out StringBuilder
        stringBuilder.append("\nPlease enter your username (and press ENTER): ");
        System.out.print(stringBuilder);
        stringBuilder.setLength(0);
        String username = input.next();
        verifyAndLaunchSpecificUser(isNonProfit, username);
    }

    private static void verifyAndLaunchSpecificUser(boolean isNonProfit, String username) throws IOException, ClassNotFoundException {
        if (isNonProfit && dataControl.isNonProfitValid(username)) {
            new NPConsole(dataControl.getNPContactByUsername(username), dataControl).invokeMenu();
        } else if (!isNonProfit && dataControl.isBidderValid(username)) {
            new BidderConsole(dataControl.getBidderByUsername(username), dataControl).invokeMenu();
        } else {
            stringBuilder.append("\nYou have entered an invalid login credential... terminating.");
            System.out.print(stringBuilder);
        }
    }
}
