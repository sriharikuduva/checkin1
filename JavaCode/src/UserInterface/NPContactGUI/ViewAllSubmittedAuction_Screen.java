import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

public class ViewAllSubmittedAuction_Screen extends Observable {

    private JPanel viewAllAuctions;
    private DataControlCenter dataControl;
    private NPContact currContact;
    private HashSet<JButton> button;

    public ViewAllSubmittedAuction_Screen (DataControlCenter dcc, NPContact currContact) throws IOException, ClassNotFoundException {
        this.viewAllAuctions = new JPanel(new BorderLayout());
        this.dataControl = dcc;
        this.currContact = currContact;
        this.setElements();
    }

   // public void ViewAllSubmittedAuction_Screen(NPContact currContact) {
     //   this.currContact = currContact;
   // }

    public JPanel getViewAllAuctionsScreen() throws IOException, ClassNotFoundException {
        this.viewAllAuctions.removeAll();
        this.setElements();
        return this.viewAllAuctions;
    }

    private void setElements() throws IOException, ClassNotFoundException {
        JPanel container = new JPanel(new GridLayout(7, 1, 20, 20));

        container.add(new JLabel("\t Here are all of your submitted auction requests "));

        container.add(new JScrollPane(this.getAuctionTable(this.dataControl.getSubmittedAuctionsByNPContact(currContact))));

//        JTable chart = new JTable();
//        chart = this.getAuctionTable(this.dataControl.getSubmittedAuctionsByNPContact(currContact));
//        container.add(chart);


        /// Trying with buttons extra code

//        button = new HashSet<JButton>();
//        HashSet<Auction> list = new HashSet<Auction>();
//        list = this.dataControl.getSubmittedAuctionsByNPContact(currContact);
//
//        for(int i = 0; i < list.size(); i++ ) {
//            button.add(new JButton(list.toString()).)
//        }




        //////


        this.viewAllAuctions.add(container, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        this.viewAllAuctions.add(back, BorderLayout.SOUTH);
        back.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(5);
        });
    }


    private JTable getAuctionTable(HashSet<Auction> auctions) {
        String[] columns = new String[] {"Auction Name", "Auction ID", "Number Items", "Start Date", "End Date"};
        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class, String.class, String.class
        };
        Object[][] auctionTiming = new Object[auctions.size()][5];
        int counter = 0;
        ArrayList<Auction> auctionList = this.dataControl.sortAuctionSet(auctions);
        for (Auction auction : auctionList) {
            auctionTiming[counter][0] = auction.getOrganization();
            auctionTiming[counter][1] = auction.getAuctionID() + "";
            auctionTiming[counter][2] = auction.getItems().size();
            auctionTiming[counter][3] = auction.getStart().toString();
            auctionTiming[counter][4] = auction.getEnd().toString();
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


}
