import org.junit.Before;
import org.junit.Test;
import java.time.Clock;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BidderTest {

    private Bidder bidder;
    private Item item;
    private int averageBidAmount;
    private LocalDateTime auctionStartDate;
    private LocalDateTime auctionEndDate;

    @Before
    public void setUp() {
        this.bidder = new Bidder("John Smith", "john@uw.edu", "john@uw.edu",
                "123 Elm Street, Tacoma, WA", "2061234567", 10000);
        this.item = new Item("Sweater", 1,
                50, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        this.averageBidAmount = 100;
        this.auctionStartDate = LocalDateTime.now();
        this.auctionEndDate = this.auctionStartDate.plusDays(1);
    }

    /*No bids can be placed on or after 12:00am on the starting date of the auction
                (date and time is in the local time zone for the auction).
        -Bidder wants to change bid the day before an auction PASS
        -Bidder wants to place bid on the auction date FAIL
        -Bidder wants to place bid the day after an auction ends FAIL */

    //5b, Test1
    @Test
    public void placeBidAuction_DayBeforeAuctionStarts_PASS() {
        Auction auctionOneDayStartingFromNow = new Auction("Boys and Girls Club",
                this.auctionStartDate.plusDays(1), this.auctionEndDate);
        this.bidder.placeBidAuction(this.averageBidAmount, auctionOneDayStartingFromNow, item);
        assertTrue(this.bidder.getBids().size() == 1);
    }

    //5b, Test2
    @Test
    public void placeBidAuction_SameDayAsAuctionStartDate_FAIL() {
        Auction auctionSameDayFromNow = new Auction("American Red Cross",
                this.auctionStartDate, this.auctionEndDate);
        this.bidder.placeBidAuction(this.averageBidAmount, auctionSameDayFromNow, item);
        assertTrue(this.bidder.getBids().size() == 0);
    }

    //5b, Test3
    @Test
    public void placeBidAuction_AfterAuctionEndDate_FAIL() {
        Auction auctionEndDateOneDayBeforeNow = new Auction("American Cancer Society",
                this.auctionStartDate.minusDays(2), this.auctionEndDate.minusDays(1));
        this.bidder.placeBidAuction(this.averageBidAmount, auctionEndDateOneDayBeforeNow, item);
        assertTrue(this.bidder.getBids().size() == 0);
    }
}