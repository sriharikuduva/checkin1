import java.util.ArrayList;
import java.util.HashSet;

public class User {
	protected String email;
	protected String username;
	protected String address;
	protected String phoneNumber;
	protected HashSet<Auction> auctions;
	protected ArrayList<Item> items; // TODO: Might not need this
    

  //constructor - Group
    public User(final String email, final String username,
            final String address, final String phoneNumber) {
         
         this.email = email;
         this.username = username;
         this.address = address;
         this.phoneNumber = phoneNumber;
         this.auctions = new HashSet<>();
         this.items = new ArrayList<>();
    }

}
