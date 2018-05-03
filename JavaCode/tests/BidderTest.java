import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BidderTest {

    private Bidder bidder;
    private LocalDateTime auctionStartDate;
    private LocalDateTime auctionEndDate;

    @Before
    public void setUp() {
        this.bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu",
                "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        this.auctionStartDate = LocalDateTime.now();
        this.auctionEndDate = this.auctionStartDate.plusDays(1);
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
}