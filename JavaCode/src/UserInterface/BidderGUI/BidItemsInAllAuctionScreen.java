import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
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

    public static final int NUM_OF_PIECES_OF_INFO = 5;

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
        List<Item> itemsList = new ArrayList<>();
        int itemCount = 0;
        for (Auction auc : dataControl.getAuctionsCurrBidderHasBids(currBidder)) {
            for (Item itm : auc.getItems()) {
                itemsList.add(itm);
                itemCount++;
            }
        }

        String[] columns = new String[] {"Item Name", "Bulk Quantity", "Bid Price", "Description", "Image"};
        final Class[] columnClass = new Class[] {String.class, String.class, String.class, String.class, String.class};
        Object[][] itemList = new Object[itemCount][NUM_OF_PIECES_OF_INFO];
        int counter = 0;
        for (Item item : itemsList) {
            itemList[counter][NUM_OF_PIECES_OF_INFO-5] = item.getName();
            itemList[counter][NUM_OF_PIECES_OF_INFO-4] = item.getQuantity();
            itemList[counter][NUM_OF_PIECES_OF_INFO-3] = "$" + item.getCurrentBid();
            itemList[counter][NUM_OF_PIECES_OF_INFO-2] = item.getDescription();
            itemList[counter][NUM_OF_PIECES_OF_INFO-1] = item.getImagePath();
            counter++;
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