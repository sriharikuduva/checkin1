import java.io.Serializable;

/** Represents a Auction Central User. */
public class User implements Serializable {
    /** User's name **/
	protected String name;
	/** User's email **/
	protected String email;
	/** User's username **/
    protected String username;
    /** User's address **/
    protected String address;
    /** User's phone number. **/
    protected String phoneNumber;


    /** Creates a user with 4 parameters.
     * @param email User's email
     * @param username User's username
     * @param address User's address
     * @param phoneNumber User's phoneNumber */
    public User(final String email, final String username,
            final String address, final String phoneNumber) {
         this.email = email;
         this.username = username;
         this.address = address;
         this.phoneNumber = phoneNumber;
    }

}
