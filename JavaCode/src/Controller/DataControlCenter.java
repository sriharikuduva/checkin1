import java.util.*;

/**
 * Read in list of existing bidders and contacts from files.
 * 
 * @author HariKuduva
 * @author ShannonWeston
 * @author BaisalUrustanbekov
 * @author MauriceChiu
 * @version 
 */
public class DataControlCenter {

    private HashSet<Bidder> masterBidderList;
    private HashSet<NPContact> masterNPContactList;
    private Scanner inputScanner;

    public DataControlCenter() {
        this.masterBidderList = getBidderList();
        this.masterNPContactList = getNPContactList();
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

    private HashSet<NPContact> getNPContactList() {
        HashSet<NPContact> toSend = new HashSet<>();
        this.inputScanner = new Scanner(getClass()
                .getResourceAsStream("masterNPContactList.txt"));
        while (this.inputScanner.hasNextLine()) {
            String parts[] = this.inputScanner.nextLine().split(",");
            this.cleanParts(parts);
            toSend.add(new NPContact(parts[0], parts[1], parts[2], parts[3], parts[4]));
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
            if (bidder.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNonProfitValid(String username) {
        for (NPContact contact : this.masterNPContactList) {
            if (contact.getUsername().equals(username)) {
                return true;
            }
        }
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

    public NPContact getNPContactByUsername(String username) {
        for (NPContact contact : this.masterNPContactList) {
            if (contact.getUsername().equals(username)) {
                return contact;
            }
        }
        return null;
    }

    public HashSet<Auction> getAuctionsCurrBidderHasBids(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>(currBidder.auctions);
        //TODO: return a Set of auctions that the bidder has bids in
        //System.out.println("** NOTICE1: NEEDS IMPLEMENTATION! **");
        
//        StringBuilder sb = new StringBuilder();
//        char option = 'a';
//        for (Auction auc : currBidder.auctions) {
//        		sb.append("\t" + option + ") ");
//        		sb.append(auc.getOrganization());
//        		sb.append("\n");
//        		option++;
//        }
//        System.out.print(sb);
        return toSend;
    }

    public HashSet<Item> getItemsCurrBidderHasBidsOnInAnAuction(Bidder currBidder, Auction specifc) {
        HashSet<Item> toSend = new HashSet<>();
        //TODO: return a Set of Items the Bidder has bids on in the specific Auction
        System.out.println("** NOTICE2: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public HashSet<Auction> getAuctionsCurrBidderCanBidOn(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of Auctions currBidder can place bids on
        System.out.println("** NOTICE3: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public void makeBid (Auction auction, Bidder currBidder) {
        System.out.println("** NOTICE4: NEEDS IMPLEMENTATION! **");
        //TODO: make bid if placebale
        /* Implementation could look something like this??? */
            //currBidder.isBidPlaceable(auction);
            //currBidder.placeBid(new Bid(...));
    }

    public HashSet<Auction> getSubmittedAuctionsByNPContact(NPContact currContact) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return auctions submitted by currContact
        System.out.println("** NOTICE5: NEEDS IMPLEMENTATION! **");
        return toSend;
    }
}
