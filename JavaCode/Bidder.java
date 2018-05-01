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
  public Bidder(final String name,final String email, final String username, final String address, final String phoneNumber, final int balance) {
    name = name;
    email = email;
    username = username;
    address = address;
    phoneNumber = phoneNumber;
    balance = balance;
    bids = new ArrayList<Bid>();
  }
  
  //(in UI) Before bid dialog is shown, check that balance is at least equal to minimum bid for item
  //(in UI) If Bidder is at maximum bids already then do not show place bid option in UI
  public void placeBid(final int amount, final Item item) {
    //(in UI) UI should check if amount is valid before placeBid() is called.
//     if(amount >= item.getMinimumBid()) {
//       item.addBid(Name, amount);
//     }
    
      bids.add(new Bid(item, amount));
  }
  
}
