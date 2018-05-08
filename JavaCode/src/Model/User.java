import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class User implements Serializable {
	protected String name;
	protected String email;
    protected String username;
    protected String address;
    protected String phoneNumber;
    /** Contains all the auction that the bidder has placed bids in. */
	protected HashMap<String, Auction> auctions = new HashMap<String, Auction>();
	

  //constructor - Group
    public User(final String email, final String username,
            final String address, final String phoneNumber) {
         
         this.email = email;
         this.username = username;
         this.address = address;
         this.phoneNumber = phoneNumber;
         //this.auctions = 
    }

}
