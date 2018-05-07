import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class SerializeData {

    private static Scanner inputScanner;

    private SerializeData() { }

    public static void main(String... args) throws IOException {
        /* NOTICE::: ONLY RUN THIS WHEN BIDDERS.BIN AND NPCONTACT.BIN ARE NOT IN ASSETS FOLDER
            OR IF THEIR ORIGINAL TXT FILES HAVE BEEN UPDATED (THEN DELETE THE OLD .BIN FILES AND RUN THIS) */
        serializeBidders("./JavaCode/bidders.bin");
        //serializeNPContact("./JavaCode/Assets/npcontact.bin");
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

    private static void cleanParts(String parts[]) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
    }
}
