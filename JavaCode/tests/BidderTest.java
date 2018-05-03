import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BidderTest {
    
    //Group
    private Bidder bidder;
    
    //Shannon Weston
    private static int STARTING_BID = 50;
    private static int EQUAL_BID = 50;
    private static int GREATER_BID = STARTING_BID + 1;
    private static int LESS_BID = STARTING_BID - 1;
    private Bid equalBid;
    private Bid greaterBid;
    private Bid lessBid;
    private Item item;

    //Hari Kuduva
    private LocalDateTime auctionStartDate;
    private LocalDateTime auctionEndDate;
    


    @Before
    public void setUp() {
        //Group
        this.bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu",
                "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        
        //Hari Kuduva
        this.auctionStartDate = LocalDateTime.now();
        this.auctionEndDate = this.auctionStartDate.plusDays(1);
        
        //Shannon Weston
        this.item = new Item("Sweater", 1, STARTING_BID, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        this.equalBid = new Bid(this.bidder.getName(), item.getName(), EQUAL_BID);
        this.greaterBid = new Bid(this.bidder.getName(), item.getName(), GREATER_BID);
        this.lessBid = new Bid(this.bidder.getName(), item.getName(), LESS_BID);

    }

    //Hari Kuduva
    @Test
    public void isBidPlaceable_DayBeforeAuctionStarts_PASS() {
        Auction auctionOneDayStartingFromNow = new Auction("Boys and Girls Club",
               this.auctionStartDate.plusDays(1), this.auctionEndDate.plusDays(1));
        assertTrue(this.bidder.isBidPlaceable(auctionOneDayStartingFromNow));
    }

    //Hari Kuduva
    @Test
    public void isBidPlaceable_SameDayAsAuctionStartDate_FAIL() {
        Auction auctionSameDayFromNow = new Auction("American Red Cross",
               this.auctionStartDate, this.auctionEndDate);
        assertFalse(this.bidder.isBidPlaceable(auctionSameDayFromNow));
    }

    //Hari Kuduva
    @Test
    public void isBidPlaceable_AfterAuctionEndDate_FAIL() {
        Auction auctionEndDateOneDayBeforeNow = new Auction("American Cancer Society",
               this.auctionStartDate.minusDays(2), this.auctionEndDate.minusDays(2));
        assertFalse(this.bidder.isBidPlaceable(auctionEndDateOneDayBeforeNow));
    }
    
    //Shannon Weston
    @Test
    public void isBidPlaceable_BidEqualToMinimum_True() {
        assertTrue(this.bidder.isBidPlaceable(item, equalBid));
    }
    
    //Shannon Weston
    @Test
    public void isBidPlaceable_BidGreaterThanMinimum_True() {
        assertTrue(this.bidder.isBidPlaceable(item, greaterBid));
    }
    
    //Shannon Weston
    @Test
    public void isBidPlaceable_BidLessThanMinimum_False() {
        assertFalse(this.bidder.isBidPlaceable(item, lessBid));
    }
}