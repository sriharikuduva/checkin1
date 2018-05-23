import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

public class AuctionsInChronoOrderScreen extends Observable {

    private JPanel chronoOrderScreen;
    private DataControlCenter dataControl;

    public AuctionsInChronoOrderScreen (DataControlCenter dcc) throws IOException, ClassNotFoundException {
        this.chronoOrderScreen = new JPanel(new BorderLayout());
        this.dataControl = dcc;
        this.setElements();
    }

    public JPanel getChronoOrderScreen() {
        return this.chronoOrderScreen;
    }

    private void setElements() throws IOException, ClassNotFoundException {
        JPanel container = new JPanel(new GridLayout(7, 1));
        container.add(new JLabel("\tSorted Chronologically by End Date"));
        container.add(new JLabel("\tPast auction(s): "));
        container.add(new JScrollPane(
                this.getAuctionTable(this.dataControl.getPastAuctions())));

        container.add(new JLabel("\tLive auction(s): "));
        container.add(new JScrollPane(
                this.getAuctionTable(this.dataControl.getActiveAuctions())));

        container.add(new JLabel("\tFuture auction(s): "));
        container.add(new JScrollPane(
                this.getAuctionTable(this.dataControl.getFutureAuctions())));

        this.chronoOrderScreen.add(container, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        this.chronoOrderScreen.add(back, BorderLayout.SOUTH);
        back.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(5);
        });
    }

    private JTable getAuctionTable(HashSet<Auction> auctions) {
        String[] columns = new String[] {"Auction Name", "Number Items", "Start Date", "End Date"};
        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class, String.class
        };
        Object[][] auctionTiming = new Object[auctions.size()][4];
        int counter = 0;
        ArrayList<Auction> auctionList = this.dataControl.sortAuctionSet(auctions);
        for (Auction auction : auctionList) {
            auctionTiming[counter][0] = auction.getOrganization();
            auctionTiming[counter][1] = auction.getItems().size();
            auctionTiming[counter][2] = auction.getStart().toString();
            auctionTiming[counter][3] = auction.getEnd().toString();
            counter++;
        }
        DefaultTableModel model = new DefaultTableModel(auctionTiming, columns) {
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

    private Auction[] arrayifyAuctions() throws IOException, ClassNotFoundException {
        HashSet<Auction> auctions = this.dataControl.deserializeAllAuctions();
        Auction [] toSend = new Auction[auctions.size()];
        int counter = 0;
        for (Auction auction : auctions) {
            toSend[counter] = auction;
            counter++;
        }
        return toSend;
    }
}
