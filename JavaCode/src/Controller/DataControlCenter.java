import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
public class DataControlCenter {

    private HashSet<Bidder> masterBidderList;
    private File file;
    private Scanner inputScanner;

    public DataControlCenter() {
        this.file = null;
        this.masterBidderList = getBidderList();
    }

    private HashSet<Bidder> getBidderList() {
        HashSet<Bidder> toSend = new HashSet<>();
        this.inputScanner = new Scanner(getClass()
                    .getResourceAsStream("masterBidderList.txt"));
        while (this.inputScanner.hasNextLine()) {
            String parts[] = this.inputScanner.nextLine().split(",");
            this.cleanParts(parts);
            toSend.add(new Bidder(parts[0], parts[1], parts[2],
                    parts[3], parts[4], Integer.parseInt(parts[5].trim())));
        }
        return toSend;
    }


    private void cleanParts(String parts[]) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
    }

    public boolean isBidderValid(String username) {
        for (Bidder bidder : this.masterBidderList) {
            String bidderUserName = bidder.getUsername();
            if (bidder.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNonProfitValid(String username) {
        return false;
    }

    public Bidder getBidderByUsername(String username) {
        for (Bidder bidder : this.masterBidderList) {
            if (bidder.getUsername().equals(username)) {
                return bidder;
            }
        }
        return null;
    }

    public NPContact getNPByUsername(String username) {
        return null;
    }

    public HashSet<Auction> getAuctionsCurrBidderHasBids(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of auctions that the bidder has bids in
        System.out.println("** NEEDS IMPLEMENTATION **");
        return toSend;
    }
}
