import java.io.IOException;

public class SimpleKickStart {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        new AuctionCentralEmployeeFrame(new AuctionCentralEmployee("hari@uw.edu", "hari_admin",
                "1 Elm Street", "1234567891", "Hari Kuduva"), new DataControlCenter());
    }
}
