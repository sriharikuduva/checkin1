import java.util.*;

public class Bidder {
    private String name;
    private String email;
    private String username;
    private String address;
    private String phoneNumber;
    private ArrayList<Bid> bids;
    private int balance;
  
  //item class
    //minimum bid
  //bid class
    //amount 
    //item
  
  
  //constructor
    public Bidder(final String name,final String email, final String username,
           final String address, final String phoneNumber, final int balance) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.bids = new ArrayList<Bid>();
    }
  
    /*(in UI) Before bid dialog is shown, check that balance is at least equal to minimum bid for item
      (in UI) If Bidder is at maximum bids already then do not show place bid option in UI*/
    public void placeBid(final int amount, final Item item) {
        //(in UI) UI should check if amount is valid before placeBid() is called.
        /*if(amount >= item.getMinimumBid()) {
            item.addBid(Name, amount);
        }*/
    
        Bid bid = new Bid(this.name, item.getName(), amount);
        item.addBid(bid);
        bids.add(bid);
    }
 
    public ArrayList<Bid> getBids() {
        return bids;
    }
}
