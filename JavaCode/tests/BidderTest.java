import java.beans.Transient;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BidderTest {
    Bidder bidder;
    Item item;
    int amountAtLeastEqualToMinimum;

    @BeforeEach
    public void setUp() {
        bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu",
                                 "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        item = new Item("Sweater", 1, 50, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        amountAtLeastEqualToMinimum = 55;
    }
    @Test
    public void placeBid_bidIsAtLeastEqualToMinimumBidForItem_True() {
        bidder.placeBid(amountAtLeastEqualToMinimum, item);
        fail("no implementation!");
        //assertTrue(bidder.getBids().size() == 1);
    }

    @Test 
    public void test_Test() {
        assertTrue(true, "Should be true");
    }
}
