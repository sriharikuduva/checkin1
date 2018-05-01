import junit;
import java.util.*;


public class BidderTest {
    Bidder bidder;
    Item item;
    
    @Before
    public void setUp() {
        bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu", "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        item = new Item("Sweater", 1, "Old", 50, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
    }
    @Test
    public void placeBid_bidIsAtLeastEqualToMinimumBidForItem_True() {
        assertTrue(bidder.placeBid(amountAtLeastEqualToMinimum, item);
    }
}
