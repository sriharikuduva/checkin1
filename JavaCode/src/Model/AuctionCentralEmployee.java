import java.io.Serializable;

public class AuctionCentralEmployee extends User implements Serializable {
    private String name;

    public AuctionCentralEmployee(final String email, final String username,
                                  final String address, final String phoneNumber, String name) {
        super(email, username, address, phoneNumber);
        this.name = name;
    }

    public String getUsername() {
        return super.username;
    }

    public String getName() {
        return this.name;
    }
}
