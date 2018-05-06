import java.util.ArrayList;


/**
 * Contains all the information about an user of the program.
 * 
 * @author HariKuduva
 * @author ShannonWeston
 * @author BaisalUrustanbekov
 * @author MauriceChiu
 * @version 
 */
public class User {
	protected String email;
	protected String username;
	protected String address;
	protected String phoneNumber;
	
	/** Contains all the auction that the bidder has placed bids in. */
	protected ArrayList<Auction> auctions;
	

  //constructor - Group
    public User(final String email, final String username,
            final String address, final String phoneNumber) {
         
         this.email = email;
         this.username = username;
         this.address = address;
         this.phoneNumber = phoneNumber;
         this.auctions = new ArrayList<>();
    }

}
