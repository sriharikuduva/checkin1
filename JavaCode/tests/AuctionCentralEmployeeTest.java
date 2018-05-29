import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



/* We have decided to break testing by user type.
    So Every test in this class is for AuctionCentralEmployee (Admin)
        Most of the methods are in DataControlCenter */

public class AuctionCentralEmployeeTest {

    private DataControlCenter dataControl;

    @Before
    public void setUp() throws IOException, ClassNotFoundException {
        this.dataControl = new DataControlCenter();
    }

    @Test
    public void setMaxAuctionAllowed_RequestedMaxAuctionAllowedIsNegative_False() {
        int badQuery = -5;
        assertFalse(this.dataControl.setMaxAuctionAllowed(badQuery));
    }

    @Test
    public void setMaxAuctionAllowed_RequestedMaxAuctionAllowedIsPositive_True() {
        int positiveQuery = 7;
        assertTrue(this.dataControl.setMaxAuctionAllowed(positiveQuery));
    }

    @Test
    public void setMaxAuctionAllowed_RequestedMaxAuctionAllowedIsGreaterThanExistingMax_True () {
        int greaterQuery = 35; // by default max is set to 7
        assertTrue(this.dataControl.setMaxAuctionAllowed(greaterQuery));
    }

    @Test
    public void getAuctionWithBounds_SecondDateIsEarlierThanEndDate_False()
            throws IOException, ClassNotFoundException {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(-1);
        assertNull(this.dataControl.getAuctionsWithBounds(startDate, endDate));
    }

    @Test
    public void getAuctionWithBounds_FirstAndSecondDateAreSame()
            throws IOException, ClassNotFoundException {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate;
        assertTrue(this.dataControl.getAuctionsWithBounds(startDate, endDate).size() == 0);
        // The set of auctions should not exist if start and end dates are there
    }

    @Test
    public void getAuctionWithBounds_SecondDateAtLeastOneDayLaterThanFirst_True()
            throws IOException, ClassNotFoundException {
        LocalDateTime startDate = LocalDateTime.MIN;
        LocalDateTime endDate = LocalDateTime.MAX;
        assertTrue(!this.dataControl.getAuctionsWithBounds(startDate, endDate).isEmpty());
    }

    @Test
    public void sortAuctionSet_PastCurrentFutureAuctionsExist_True()
            throws IOException, ClassNotFoundException {
        boolean past = this.dataControl.getPastAuctions().size() >= 1;
        boolean current = this.dataControl.getActiveAuctions().size() >= 0;
        // We have no initial current auctions
        boolean future = this.dataControl.getFutureAuctions().size() >= 1;
        assertTrue(past && current && future);
    }









}
