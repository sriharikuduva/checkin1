import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BidderTest {

    private Bidder bidder;
    private Item item;
    private int amountAtLeastEqualToMinimum;

    @Before
    public void setUp() {
        this.bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu",
                "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        this.item = new Item("Sweater", 1,
                50, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        this.amountAtLeastEqualToMinimum = 55;
    }


    @Test
    public void placeBid_bidIsAtLeastEqualToMinimumBidForItem_True() {
        this.bidder.placeBid(amountAtLeastEqualToMinimum, item);
        assertTrue(this.bidder.getBids().size() == 1);
    }
}