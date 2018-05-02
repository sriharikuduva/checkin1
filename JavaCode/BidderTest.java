import java.util.*;
import org.junit.*;
import Bidder.java;

public class BidderTest {
    private Bidder bidder;
    private Item item;
    private int amountEqualToMinimum;
    private int amountGreaterThanMinimum;
    private int amountLessThanMinimum;     


    //Group
    @Before
    public void setUp() {
        bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu", "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        item = new Item("Sweater", 1, 50, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        amountEqualToMinimum = 50;
        amountGreaterThanMinimum = 55;
	amountLessThanMinimum = 49; 
    }

    //Group
    @Test
    public void placeBid_bidIsEqualToMinimumBidForItem_True() {
        bidder.placeBid(amountEqualToMinimum, item);
        assertTrue(bidder.getBids().size() == 1);
    }
    
    //Shannon Weston
    @Test
    public void placeBid_bidIsGreaterThanMinimumForItem_True() {
        bidder.placeBid(amountGreaterThanMinimum, item);
        assertTrue(bidder.getBids().size() == 1);
    }
    
    //Shannon Weston
    @Test
    public void placeBid_bidIsLessThanMinimumForItem_False() {
        bidder.placeBid(amountLessThanMinimum, item);
        assertFalse(bidder.getBids().size() == 0);
    }
}
