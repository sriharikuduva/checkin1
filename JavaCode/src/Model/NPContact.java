import java.util.Arrays;

import org.hamcrest.core.Is;

/**
 * Carries extra information specific to Non-Profit contacts and defines
 * what a contact can or cannot do.
 * 
 * @author HariKuduva
 * @author ShannonWeston
 * @author BaisalUrustanbekov
 * @author MauriceChiu
 * @version 
 */
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
