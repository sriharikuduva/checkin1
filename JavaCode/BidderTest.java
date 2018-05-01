import junit;
import java.util.*;


public class BidderTest {
    Bidder bidder;
    Item item;
    int amountAtLeastEqualToMinimum;
    
    @Before
    public void setUp() {
        bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu", "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        item = new Item("Sweater", 1, 50, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        amountAtLeastEqualToMinimum = 55;
    }
    @Test
    public void placeBid_bidIsAtLeastEqualToMinimumBidForItem_True() {
        bidder.placeBid(amountAtLeastEqualToMinimum, item);
        assertTrue(bidder.getBids().size() == 1);
    }
}
