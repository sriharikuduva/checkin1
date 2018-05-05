import java.util.Arrays;

public class NPContact {

    private String orgName;
    private String email;
    private String username;
    private String address;
    private String phoneNum;


    public NPContact(String orgName, String email,
                     String username, String address, String phoneNum) {

        this.orgName = orgName;
        this.email = email;
        this.username = username;
        this.address = address;
        this.phoneNum = phoneNum;



    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.orgName;
    }
}
