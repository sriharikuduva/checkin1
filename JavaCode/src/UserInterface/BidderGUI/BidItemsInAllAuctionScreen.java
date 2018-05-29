import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * This Panel displays the items that the Bidder has bid on in all auctions.
 * @author MauriceChiu
 */
public class BidItemsInAllAuctionScreen extends Observable {
    private JPanel itemsInAllAuctionScreen;
    private Bidder currBidder;
    private DataControlCenter dataControl;

    public static final int NUM_OF_PIECES_OF_INFO = 2;

    /**
     *
     * @param currBidder
     * @param dataControl
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public BidItemsInAllAuctionScreen(Bidder currBidder, DataControlCenter dataControl) throws IOException, ClassNotFoundException {
        this.itemsInAllAuctionScreen = new JPanel(new BorderLayout());
        this.currBidder = currBidder;
        this.dataControl = dataControl;
        this.setupFrame();
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void setupFrame() throws IOException, ClassNotFoundException {
        this.itemsInAllAuctionScreen.add(new JLabel("\tHere are all the items you have bid on: "), BorderLayout.NORTH);
        JPanel auctionFrame = new JPanel();
        auctionFrame.add(new JScrollPane(this.getItemTable()));

        this.itemsInAllAuctionScreen.add(auctionFrame, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        this.itemsInAllAuctionScreen.add(back, BorderLayout.SOUTH);
        back.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(MainScreen_Bidder.BACK);
        });
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private JTable getItemTable() throws IOException, ClassNotFoundException {
        String[] columns = new String[] {"Item Name", "Your Bid Price"};
        final Class[] columnClass = new Class[] {String.class, String.class};
        Object[][] itemList = new Object[currBidder.getBids().size()][NUM_OF_PIECES_OF_INFO];

        System.out.println("bidder name: " + currBidder.getName()); // TODO remove
        System.out.println("bidder name: " + currBidder.getUsername()); // TODO remove
        System.out.println("bids[] size: " + currBidder.getBids().size()); // TODO remove


        for (Bid bid : currBidder.getBids()) {
        //for (Bid bid : dataControl.getItemsCurrBidderHasBi(currBidder))
            System.out.println(bid.getItem()); // TODO remove
            itemList[currBidder.getBids().size()][NUM_OF_PIECES_OF_INFO-2] = bid.getItem();
            itemList[currBidder.getBids().size()][NUM_OF_PIECES_OF_INFO-1] = "$" + bid.getAmount();
        }

        DefaultTableModel model = new DefaultTableModel(itemList, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };

        return new JTable(model);
    }

    /**
     *
     * @return
     */
    public JPanel getItemsInAllAuctionScreen() {
        return this.itemsInAllAuctionScreen;
    }
}