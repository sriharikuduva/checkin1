import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataControlCenterTest {

    //Fields
    private int setNumberOfDays;
    private int setNumberOfDaysTwo;
    private int setNumberOfDaysThree;
    private int setNumberOfDay;
    private int setNumberOfDayTwo;
    private int setNumberOfDayThree;

    private ArrayList<Auction> auctions, auctionsOne, auctionstwo;
    private Auction auction, auctiontwo, auctionThree;
    private DataControlCenter dataControlCenter;


    @Before
    public void setUp() throws Exception {

        dataControlCenter = new DataControlCenter();
        //No auction can be scheduled more than a set number of days from the current date, default of 60 days.
        //No auction can be scheduled less than a set number of days from the current date, default of 14.
        setNumberOfDays = 55;
        setNumberOfDaysTwo = 60;
        setNumberOfDaysThree = 61;
        setNumberOfDay = 15;
        setNumberOfDayTwo = 14;
        setNumberOfDayThree = 10;

        auctions = new ArrayList<Auction>();
        auctionsOne = new ArrayList<Auction>();
        auctionstwo = new ArrayList<Auction>();

        auction = new Auction("American Cancer Society", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(20),  1);
        auctiontwo = new Auction("American Red Cross", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(30),  2);
        auctionThree = new Auction("Outlook on India", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(15),  2);

        auctions.add(auction); auctions.add(auctiontwo); auctions.add(auctionThree);
        auctionsOne.add(auction); auctionsOne.add(auctiontwo);
        auctionstwo.add(auction);

    }


    @Test
    public void isRequestedAuctionDateValid_forMaxSetNumberOfDateIsless_true() {
        assertTrue(dataControlCenter.isRequestedAuctionDateValid(LocalDateTime.now().plusDays(setNumberOfDays)));
    }

    @Test
    public void isRequestedAuctionDateValid_forMaxSetNumberOfDateIsSame_true() {
        assertTrue(dataControlCenter.isRequestedAuctionDateValid(LocalDateTime.now().plusDays(setNumberOfDaysTwo)));
    }

    @Test
    public void isRequestedAuctionDateValid_forMaxSetNumberOfDateIsMore_false() {
        assertFalse(dataControlCenter.isRequestedAuctionDateValid(LocalDateTime.now().plusDays(setNumberOfDaysThree)));
    }

    @Test
    public void isRequestedAuctionDateValid_forMinSetNumberOfDateIsless_true() {
        assertTrue(dataControlCenter.isRequestedAuctionDateValid(LocalDateTime.now().plusDays(setNumberOfDay)));
    }

    @Test
    public void isRequestedAuctionDateValid_forMinSetNumberOfDateIsSame_true() {
        assertTrue(dataControlCenter.isRequestedAuctionDateValid(LocalDateTime.now().plusDays(setNumberOfDayTwo)));
    }

    @Test
    public void isRequestedAuctionDateValid_forMinSetNumberOfDateIsMore_false() {
        assertFalse(dataControlCenter.isRequestedAuctionDateValid(LocalDateTime.now().plusDays(setNumberOfDayThree)));
    }

    @Test
    public void isAuctionAvailableForSubmissionRequest_forSetOfAuctionnumbers_fail() {
        assertFalse(dataControlCenter.isAuctionAvailableForSubmissionRequest(LocalDateTime.now(), auctionstwo));
    }

    @Test
    public void isAuctionAvailableForSubmissionRequest_forSetOfAuctionnumbers_false() {
        assertFalse(dataControlCenter.isAuctionAvailableForSubmissionRequest(LocalDateTime.now(), auctions));
    }


    

}

