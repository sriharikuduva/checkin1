import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.*;

import static junit.framework.TestCase.*;


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

    //Maurice Chiu
    private Bidder bidderWithLessBiddedItemsThanMaximum;
    private Bidder bidderWithMaxNumberOfBiddedItems;

    //Baisal Urustanbekov
    private ArrayList<Item> itemsExceeded;
    private ArrayList<Item> itemsNotExceeded;
    private Item itemOne, itemTwo, itemThree, itemFour, itemFive, itemSix, itemSeven, itemEight, itemNine, itemTen;



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

        //Maurice Chiu
        this.bidderWithLessBiddedItemsThanMaximum =
                new Bidder("Aaron", "aaron@uw.edu", "aaron@uw.edu", "234 1st St, Tacoma, WA", "2069876543", 20100);
        this.bidderWithMaxNumberOfBiddedItems =
                new Bidder("Betty", "betty@uw.edu", "betty@uw.edu", "345 2nd St, Tacoma, WA", "2064567891", 19800);

        //Baislabek Urustanbekov
        itemsExceeded = new ArrayList<Item>();
        itemsNotExceeded = new ArrayList<Item>();
        this.item = new Item("Sweater", 1, STARTING_BID, "Blue Polka Dot Knit Sweater", "Path/To/File.jpg");
        this.itemsExceeded.add(item);
        this.itemsNotExceeded.add(item);
        this.itemOne = new Item("Backback", 1, STARTING_BID, "Customizable black Backpack", "Path/To/File.jpg");
        this.itemsExceeded.add(itemOne);
        this.itemsNotExceeded.add(itemOne);
        this.itemTwo = new Item("Watch", 1, STARTING_BID, "Golden Watch", "Path/To/File.jpg");
        this.itemsExceeded.add(itemTwo);
        this.itemsNotExceeded.add(itemTwo);
        this.itemThree = new Item("Table", 1, STARTING_BID, "Coffee Table", "Path/To/File.jpg");
        this.itemsExceeded.add(itemThree);
        this.itemsNotExceeded.add(itemThree);
        this.itemFour = new Item("TV", 1, STARTING_BID, "New Release Sumsung", "Path/To/File.jpg");
        this.itemsExceeded.add(itemFour);
        this.itemsNotExceeded.add(itemFour);
        this.itemFive = new Item("Table", 1, STARTING_BID, "Desktop Table", "Path/To/File.jpg");
        this.itemsExceeded.add(itemFive);
        this.itemsNotExceeded.add(itemFive);
        this.itemSix = new Item("Bed", 1, STARTING_BID, "King Size Bed ", "Path/To/File.jpg");
        this.itemsExceeded.add(itemSix);
        this.itemsNotExceeded.add(itemSix);
        this.itemSeven = new Item("Computer", 1, STARTING_BID, "Desktop Computer", "Path/To/File.jpg");
        this.itemsExceeded.add(itemSeven);
        this.itemsNotExceeded.add(itemSeven);
        this.itemEight = new Item("Phone", 1, STARTING_BID, "Sumsumg Galaxy 8", "Path/To/File.jpg");
        this.itemsExceeded.add(itemEight);
        this.itemsNotExceeded.add(itemEight);
        this.itemNine = new Item("Coffee Machine", 1, STARTING_BID, "Coffee Brewer", "Path/To/File.jpg");
        this.itemsExceeded.add(itemNine);
        this.itemTen = new Item("Car", 1, STARTING_BID, "Honda Civic 2013", "Path/To/File.jpg");
        this.itemsExceeded.add(itemTen);

    }

    //Hari Kuduva
    @Test
    public void isBidPlaceable_DayBeforeAuctionStarts_TRUE() {
        Auction auctionOneDayStartingFromNow = new Auction("Boys and Girls Club",
                this.auctionStartDate.plusDays(1), this.auctionEndDate.plusDays(1));
        assertTrue(this.bidder.isBidPlaceableAuctionDate(auctionOneDayStartingFromNow));
    }

    //Hari Kuduva
    @Test
    public void isBidPlaceable_SameDayAsAuctionStartDate_FALSE() {
        Auction auctionSameDayFromNow = new Auction("American Red Cross",
                this.auctionStartDate, this.auctionEndDate);
        assertFalse(this.bidder.isBidPlaceableAuctionDate(auctionSameDayFromNow));
    }

    //Hari Kuduva
    @Test
    public void isBidPlaceable_AfterAuctionEndDate_FALSE() {
        Auction auctionEndDateOneDayBeforeNow = new Auction("American Cancer Society",
                this.auctionStartDate.minusDays(2), this.auctionEndDate.minusDays(2));
        assertFalse(this.bidder.isBidPlaceableAuctionDate(auctionEndDateOneDayBeforeNow));
    }

    //Shannon Weston
    @Test
    public void isBidPlaceable_BidEqualToMinimum_True() {
        assertTrue(this.bidder.isBidPlaceableMinimumBid(item, equalBid));
    }

    //Shannon Weston
    @Test
    public void isBidPlaceable_BidGreaterThanMinimum_True() {
        assertTrue(this.bidder.isBidPlaceableMinimumBid(item, greaterBid));
    }

    //Shannon Weston
    @Test
    public void isBidPlaceable_BidLessThanMinimum_False() {
        assertFalse(this.bidder.isBidPlaceableMinimumBid(item, lessBid));
    }

    //Maurice Chiu
    @Test
    public void isBidPlaceableItemWithBids_oneLessItemWithBidThanMaximumInAnAuction_true() {
        assertTrue(bidderWithLessBiddedItemsThanMaximum.
                isBidPlaceableItemWithBids(Bidder.MAX_ITEMS_WITH_BID_IN_AN_AUCTION-1));
    }

    //Maurice Chiu
    @Test
    public void isBidPlaceableItemWithBids_maximumNumberOfItemsWithBidInAnAuction_false() {
        assertFalse(bidderWithMaxNumberOfBiddedItems.
                isBidPlaceableItemWithBids(Bidder.MAX_ITEMS_WITH_BID_IN_AN_AUCTION));
    }

    //Baisal Urustanbekov
    @Test
    public void isBidPlaceableInFutureAuctions_bidderNotExceededMaxItem_true() {
        Auction auction = new Auction("Boys and Girls Club",
                this.auctionStartDate.plusDays(1), this.auctionEndDate.plusDays(1));
        assertTrue(this.bidder.isBidPlaceableInFutureAuctions(auction,itemsNotExceeded));
    }

    //Baisal Urustanbekov
    @Test
    public void isBidPlaceableInFutureAuctions_bidderNotExceededMaxItem_fasle() {
        Auction auction = new Auction("Boys and Girls Club",
                this.auctionStartDate.plusDays(1), this.auctionEndDate.plusDays(1));
        assertFalse(this.bidder.isBidPlaceableInFutureAuctions(auction,itemsExceeded));
    }

    //Baisal Urustanbekov
    @Test
    public void isBidPlaceableInFutureAuctions_bidderExceededMaxItem_true() {
        Auction auction = new Auction("Boys and Girls Club",
                this.auctionStartDate.plusDays(1), this.auctionEndDate.plusDays(1));
        assertTrue(this.bidder.isBidPlaceableInFutureAuctions(auction,itemsNotExceeded));
    }

    //Baisal Urustanbekov
    @Test
    public void isBidPlaceableInFutureAuctions_bidderExceededMaxItem_fasle() {
        Auction auction = new Auction("Boys and Girls Club",
                this.auctionStartDate.plusDays(1), this.auctionEndDate.plusDays(1));
        assertFalse(this.bidder.isBidPlaceableInFutureAuctions(auction,itemsExceeded));
    }
}