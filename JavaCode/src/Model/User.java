import java.io.Serializable;

public class User implements Serializable {
	protected String name;
	protected String email;
    protected String username;
    protected String address;
    protected String phoneNumber;

  //constructor - Group
    public User(final String email, final String username,
            final String address, final String phoneNumber) {
         
         this.email = email;
         this.username = username;
         this.address = address;
         this.phoneNumber = phoneNumber;
    }

}