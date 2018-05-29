import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;


public class BidderTest {

    private DataControlCenter dataControl;
    private Bidder normalBidder;

    @Before
    public void setUp() throws IOException, ClassNotFoundException {
        this.dataControl = new DataControlCenter();
        this.normalBidder = new Bidder("Baisal", "Baisal@baisal.baisal", "baisal",
                "123 Elm Street", "1234567891", 10000);
    }

    /*As a bidder, I want a view in brief of all auctions on which I can bid.
There exists more than one auction in the past (should not be listed),
 and more than one auction in the future on which I can bid.
    [Each additional success and failure case for business rules
        for bidder placing a bid should also be tested]
 */

    //    public HashSet<Auction> getAuctionsCurrBidderCanBidOn(Bidder currBidder)
    // throws ClassNotFoundException, IOException {

    @Test
    public void getAuctionsCurrBidderCanBidOn_PastAuctionExists_False()
            throws IOException, ClassNotFoundException {
        //HashSet<Auction> auctions = this.dataControl.getAuctionsCurrBidderCanBidOn();


    }

}