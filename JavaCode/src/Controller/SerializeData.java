import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;

public class SerializeData {

    private static Scanner inputScanner;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private SerializeData() { }

    public static void main(String... args) throws IOException {
        /* NOTICE::: ONLY RUN THIS WHEN BIDDERS.BIN AND NPCONTACT.BIN ARE NOT IN ASSETS FOLDER
            OR IF THEIR ORIGINAL TXT FILES HAVE BEEN UPDATED (THEN DELETE THE OLD .BIN FILES AND RUN THIS) */
        serializeBidders("./JavaCode/Assets/bidders.bin");
        serializeNPContact("./JavaCode/Assets/npcontact.bin");
        serializeAuctions("./JavaCode/Assets/auctions.bin");
    }

    private static void serializeBidders(String output) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        inputScanner = new Scanner(SerializeData.class.getResourceAsStream("masterBidderList.txt"));
        HashSet<Bidder> toSerialize = new HashSet<>();
        while (inputScanner.hasNextLine()) {
            String parts[] = inputScanner.nextLine().split(",");
            cleanParts(parts);
            toSerialize.add(new Bidder(parts[0], parts[1], parts[2],
                    parts[3], parts[4], Integer.parseInt(parts[5].trim())));
        }
        oos.writeObject(toSerialize);
    }

    private static void serializeNPContact(String output) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        inputScanner = new Scanner(SerializeData.class
                .getResourceAsStream("masterNPContactList.txt"));
        HashSet<NPContact> toSerialize = new HashSet<>();
        while (inputScanner.hasNextLine()) {
            String parts[] = inputScanner.nextLine().split(",");
            cleanParts(parts);
            toSerialize.add(new NPContact(parts[0], parts[1], parts[2],
                    parts[3], parts[4]));
        }
        oos.writeObject(toSerialize);
    }

    private static void serializeAuctions(String output) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        inputScanner = new Scanner(SerializeData.class
                .getResourceAsStream("masterAuctionList.txt"));
        HashSet<Auction> toSerialize = new HashSet<>();
        while (inputScanner.hasNextLine()) {
            String parts[] = inputScanner.nextLine().split(",");
            cleanParts(parts);
            Auction temp = new Auction(parts[0],
                    LocalDateTime.parse(parts[1], formatter),
                    LocalDateTime.parse(parts[2], formatter),
                    Integer.parseInt(parts[3]));
            addItemsForAuction(temp);
            toSerialize.add(temp);
        }
        oos.writeObject(toSerialize);
    }

    private static void addItemsForAuction(Auction auction) {
        Scanner scan = new Scanner(SerializeData.class
                .getResourceAsStream(auction.getOrganization() + ".txt"));
        while(scan.hasNextLine()) {
            String parts[] = scan.nextLine().split(",");
            cleanParts(parts);
            if (Integer.parseInt(parts[5]) == auction.getAuctionID()) {
                auction.addItem(new Item(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]), parts[3], parts[4]));
            }
        }
    }

    private static void cleanParts(String parts[]) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
    }
}
