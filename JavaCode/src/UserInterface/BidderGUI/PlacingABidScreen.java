import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Observable;

/**
 * This Panel displays the items of an auction chosen by the Bidder.
 * @author MauriceChiu
 */
public class PlacingABidScreen extends Observable {
    private JPanel placingABidScreen;
    private Bidder currBidder;
    private DataControlCenter dataControl;

    public static final int NUM_OF_PIECES_OF_INFO = 5;
    public static final String BID_IS_LESS_THAN_MINIMUM = "Bid is less than the minimum acceptable";
    public static final String TOO_LATE_TO_BID = "The auction is starting soon. You can no longer bid on this item.";
    public static final String MAX_NUMBER_OF_ITEMS_REACHED_PER_AUCTION = "You have reached the maximum number of items you can bid on in this auction.";
    public static final String MAX_NUMBER_OF_ITEMS_REACHED_ALL_AUCTIONS = "You have reached the maximum number of items you can bid on in all auctions.";

    /**
     *
     * @param currBidder
     * @param dataControl
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public PlacingABidScreen (Bidder currBidder, DataControlCenter dataControl) throws IOException, ClassNotFoundException {
        this.placingABidScreen = new JPanel(new BorderLayout());
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
        this.placingABidScreen.add(new JLabel("\tHere are all the auctions you can bid on, please pick one to view the items: "), BorderLayout.NORTH);
        for (Auction auc : dataControl.getAllAuctions()){
            DateTimeFormatter dtformatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            JButton auctionButton = new JButton(auc.getOrganization() + "\t\t(Auction Date: " + auc.getStart().format(dtformatter) + ")");
            auctionButton.addActionListener((ActionEvent e1) -> {
                JFrame itemsFrame = new JFrame("Please select an item to bid on");
                itemsFrame.setLayout(new BorderLayout());
                //itemsFrame.add(new JLabel("\tPlease select an item to bid on: "), BorderLayout.NORTH);
                JPanel container = new JPanel(new GridLayout(auc.getItems().size(), 1));
                for (Item itm : auc.getItems()) {
                    JButton itemButton = new JButton(itm.getName() + "\t\t(Price: $" + itm.getCurrentBid() + ", Bulk Qty: " + itm.getQuantity() + ")");
                    itemButton.addActionListener((ActionEvent e2) -> {
//                        String price = EnhancedOptionPane.showInputDialog("Item Name: " + itm.getName() + "\n"
//                                + "Current Bid Price: $" + itm.getCurrentBid()  + "\n"
//                                + "Bulk Quantity: " + itm.getQuantity()  + "\n"
//                                + "Description: " + itm.getDescription() + "\n\n"
//                                + "Please Enter Your Bid Price: ",
//                                new Object[] {"Place Bid", "Cancel"});

                        String price = JOptionPane.showInputDialog(itemsFrame,
                                "Item Name: " + itm.getName() + "\n"
                                + "Current Bid Price: $" + itm.getCurrentBid()  + "\n"
                                + "Bulk Quantity: " + itm.getQuantity()  + "\n"
                                + "Description: " + itm.getDescription() + "\n\n"
                                + "Please Enter Your Bid Price: ",
                                "Place a bid",
                                JOptionPane.PLAIN_MESSAGE);
                        if (price != null && price != "") {
                            try {
                                int bidderBidPrice = Integer.parseInt(price);
                                Bid bid = new Bid(currBidder.getName(), itm.getName(), bidderBidPrice);
                                boolean[] failCheck = this.currBidder.isBidPlacable(auc, itm, bid);
                                boolean showErrorMsg = false;
                                StringBuilder errorMessage = new StringBuilder();
                                if(failCheck[0] == false) {
                                    errorMessage.append("The amount you have entered is less than the bid price of this item. Please try again.\n");
                                    showErrorMsg = true;
                                }
                                if(failCheck[1] == false) {
                                    errorMessage.append("It has passe the auction start time, you may no longer bid on this item.\n");
                                    showErrorMsg = true;
                                }
                                if(dataControl.getItemsCurrBidderHasBidsOnInAnAuction(currBidder, auc).size() > Bidder.MAX_ITEMS_WITH_BID_IN_AN_AUCTION) {
                                    errorMessage.append("You have already reached the maximum number of items you could bid on in this auction.\n");
                                    showErrorMsg = true;
                                }
                                if(failCheck[2] == false) {
                                    errorMessage.append("You have already reached the maximum number of items you could bid on in all future auctions.\n");
                                    showErrorMsg = true;
                                }

                                if (showErrorMsg == true) {
                                    JOptionPane.showMessageDialog(itemsFrame, errorMessage, "Fail", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    dataControl.placeBid(auc, itm, bid);
                                    JOptionPane.showMessageDialog(itemsFrame, "You have placed your bid successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
                                }

                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(itemsFrame, "Input Error! Please enter bid price in numbers and try again!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                    container.add(itemButton);
                }


                itemsFrame.add(new JLabel("\tItems listed by " + auc.getOrganization() + "\t\t(Auction Date: " + auc.getStart().format(dtformatter) + "): "), BorderLayout.NORTH);
                itemsFrame.add(container, BorderLayout.CENTER);
                itemsFrame.pack();
                itemsFrame.setLocationRelativeTo(null);
                itemsFrame.setVisible(true);
            });
            auctionFrame.add(auctionButton);
        }

        this.placingABidScreen.add(auctionFrame, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        this.placingABidScreen.add(back, BorderLayout.SOUTH);
        back.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(MainScreen_Bidder.BACK);
        });
    }

//    /**
//     *
//     * @param auc
//     * @return
//     */
//    private JTable getItemTable(Auction auc) {
//        String[] columns = new String[] {"Item Name", "Bulk Quantity", "Bid Price", "Description", "Image"};
//        final Class[] columnClass = new Class[] {String.class, String.class, String.class, String.class, String.class};
//        Object[][] itemList = new Object[auc.getItems().size()][NUM_OF_PIECES_OF_INFO];
//        int counter = 0;
//        for (Item item : auc.getItems()) {
//            itemList[counter][NUM_OF_PIECES_OF_INFO-5] = item.getName();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-4] = item.getQuantity();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-3] = item.getCurrentBid();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-2] = item.getDescription();
//            itemList[counter][NUM_OF_PIECES_OF_INFO-1] = item.getImagePath();
//            counter++;
//        }
//
//        DefaultTableModel model = new DefaultTableModel(itemList, columns) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                return columnClass[columnIndex];
//            }
//        };
//
//        return new JTable(model);
//    }

    /**
     *
     * @return
     */
    public JPanel getPlacingABidScreen() {
        return this.placingABidScreen;
    }
}