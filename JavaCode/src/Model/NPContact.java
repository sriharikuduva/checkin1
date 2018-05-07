import java.util.Arrays;

public class NPContact extends User{

    private String orgName;

    public NPContact(String orgName, String email,
                     String username, String address, String phoneNumber) {

    		super(email, username, address, phoneNumber);
        this.orgName = orgName;
    }

    public String getUsername() {
        return super.username;
    }

    public String getName() {
        return this.orgName;
    }
}
