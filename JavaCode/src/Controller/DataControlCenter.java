import java.io.*;
import java.util.*;
public class DataControlCenter {

    private Scanner inputScanner;

    public DataControlCenter() {
        try {
            this.makeBiddersBinary("./JavaCode/Assets/bidders.bin");
            this.makeNPContactBinary("./JavaCode/Assets/npcontact.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeBiddersBinary(String output) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        this.inputScanner = new Scanner(getClass()
                .getResourceAsStream("masterBidderList.txt"));
        HashSet<Bidder> toSerialize = new HashSet<>();
        while (this.inputScanner.hasNextLine()) {
            String parts[] = this.inputScanner.nextLine().split(",");
            this.cleanParts(parts);
            toSerialize.add(new Bidder(parts[0], parts[1], parts[2],
                    parts[3], parts[4], Integer.parseInt(parts[5].trim())));
        }
        oos.writeObject(toSerialize);
    }

    private void makeNPContactBinary(String output) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        this.inputScanner = new Scanner(getClass()
                .getResourceAsStream("masterNPContactList.txt"));
        HashSet<NPContact> toSerialize = new HashSet<>();
        while (this.inputScanner.hasNextLine()) {
            String parts[] = this.inputScanner.nextLine().split(",");
            this.cleanParts(parts);
            toSerialize.add(new NPContact(parts[0], parts[1], parts[2],
                    parts[3], parts[4]));
        }
        oos.writeObject(toSerialize);
    }

    private void cleanParts(String parts[]) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
    }

    private HashSet<NPContact> deserializeAllNPContacts() throws IOException, ClassNotFoundException {
        return (HashSet<NPContact>) new ObjectInputStream(getClass().
                getResourceAsStream("npcontact.bin")).readObject();
    }

    private HashSet<Bidder> deserializeAllBidders() throws IOException, ClassNotFoundException {
        return (HashSet<Bidder>) new ObjectInputStream(getClass().
                getResourceAsStream("bidders.bin")).readObject();
    }

    public boolean isBidderValid(String username) throws IOException, ClassNotFoundException {
        for (Bidder bidder : this.deserializeAllBidders()) {
            if (bidder.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNonProfitValid(String username) throws IOException, ClassNotFoundException {
        for (NPContact contact : this.deserializeAllNPContacts()) {
            if (contact.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Bidder getBidderByUsername(String username) throws IOException, ClassNotFoundException {
        for (Bidder bidder : this.deserializeAllBidders()) {
            if (bidder.getUsername().equals(username)) {
                return bidder;
            }
        }
        return null;
    }

    public NPContact getNPContactByUsername(String username) throws IOException, ClassNotFoundException {
        for (NPContact contact : this.deserializeAllNPContacts()) {
            if (contact.getUsername().equals(username)) {
                return contact;
            }
        }
        return null;
    }

    public HashSet<Auction> getAuctionsCurrBidderHasBids(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of auctions that the bidder has bids in
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public HashSet<Item> getItemsCurrBidderHasBidsOnInAnAuction(Bidder currBidder, Auction specifc) {
        HashSet<Item> toSend = new HashSet<>();
        //TODO: return a Set of Items the Bidder has bids on in the specific Auction
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public HashSet<Auction> getAuctionsCurrBidderCanBidOn(Bidder currBidder) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return a Set of Auctions currBidder can place bids on
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }

    public void makeBid (Auction auction, Bidder currBidder) {
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        //TODO: make bid if placebale
        /* Implementation could look something like this??? */
            //currBidder.isBidPlaceable(auction);
            //currBidder.placeBid(new Bid(...));
    }

    public HashSet<Auction> getSubmittedAuctionsByNPContact(NPContact currContact) {
        HashSet<Auction> toSend = new HashSet<>();
        //TODO: return auctions submitted by currContact
        System.out.println("** NOTICE: NEEDS IMPLEMENTATION! **");
        return toSend;
    }
}
