import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Observable;

/**
 * This Panel displays the items that the Bidder has bid on in an auction.
 * @author MauriceChiu
 */
public class BidItemsInAnAuctionScreen extends Observable {
    private JPanel itemsInAuctionScreen;
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
    public BidItemsInAnAuctionScreen (Bidder currBidder, DataControlCenter dataControl) throws IOException, ClassNotFoundException {
        //System.out.println("Instance of BiddableAuctionsScreen created.");
        this.itemsInAuctionScreen = new JPanel(new BorderLayout());
        this.currBidder = currBidder;
        this.dataControl = dataControl;
        this.setupAuctions();
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void setupAuctions() throws IOException, ClassNotFoundException {
        JPanel auctionFrame = new JPanel(new GridLayout(dataControl.getAuctionsCurrBidderCanBidOn(currBidder).size(), 1));
        this.itemsInAuctionScreen.add(new JLabel("\tHere are all the auctions, please pick one to view the items that you have bid on: "), BorderLayout.NORTH);
        for (Auction auc : dataControl.getAuctionsCurrBidderHasBids(currBidder)){
            DateTimeFormatter dtformatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            JButton but = new JButton(auc.getOrganization() + "\t\t(Auction Date: " + auc.getStart().format(dtformatter) + ")");
            but.addActionListener((ActionEvent e) -> {
                JFrame itemsFrame = new JFrame();
                itemsFrame.setLayout(new BorderLayout());

                JPanel container = new JPanel();
                container.add(new JScrollPane(this.getItemTable(auc)));

                itemsFrame.add(new JLabel("\tHere are all the items you have bid on with " + auc.getOrganization() + ": "), BorderLayout.NORTH);
                itemsFrame.add(container, BorderLayout.CENTER);
                itemsFrame.pack();
                itemsFrame.setLocationRelativeTo(null);
                itemsFrame.setVisible(true);
            });
            auctionFrame.add(but);
        }

        this.itemsInAuctionScreen.add(auctionFrame, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        this.itemsInAuctionScreen.add(back, BorderLayout.SOUTH);
        back.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(MainScreen_Bidder.BACK);
        });
    }

    /**
     *
     * @param auc
     * @return
     */
    private JTable getItemTable(Auction auc) {
//        String[] columns = new String[] {"Item Name", "Bulk Quantity", "Bid Price", "Description", "Image"};
//        final Class[] columnClass = new Class[] {String.class, String.class, String.class, String.class, String.class};
//        Object[][] itemList = new Object[auc.getItems().size()][NUM_OF_PIECES_OF_INFO];
//        int counter = 0;
//        for (Item item : auc.getItems()) {
//            itemList[counter][NUM_OF_PIECES_OF_INFO-5] = item.getName();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-4] = item.getQuantity();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-3] = "$" + item.getCurrentBid();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-2] = item.getDescription();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-1] = item.getImagePath();
//            counter++;
//        }

        String[] columns = new String[] {"Item Name", "Your Bid Price"};
        final Class[] columnClass = new Class[] {String.class, String.class};
        Object[][] itemList = new Object[currBidder.getBids().size()][NUM_OF_PIECES_OF_INFO];

        System.out.println("bidder name: " + currBidder.getName()); // TODO remove
        System.out.println("bidder name: " + currBidder.getUsername()); // TODO remove
        System.out.println("bids[] size: " + currBidder.getBids().size()); // TODO remove

        for (Bid bid : currBidder.getBids()) {
            // TODO Hari, I would need a bid to save more information about an item. Auction ID is the minimum requirement.
            //if (bid.auctionID == auc.getAuctionID()) {
                System.out.println(bid.getItem()); // TODO remove
                itemList[currBidder.getBids().size()][NUM_OF_PIECES_OF_INFO - 2] = bid.getItem();
                itemList[currBidder.getBids().size()][NUM_OF_PIECES_OF_INFO - 1] = "$" + bid.getAmount();
            //}
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
    public JPanel getItemsInAuctionScreen() {
        return this.itemsInAuctionScreen;
    }
}