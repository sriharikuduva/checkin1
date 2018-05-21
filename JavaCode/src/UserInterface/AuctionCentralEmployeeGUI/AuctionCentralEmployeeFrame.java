import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class AuctionCentralEmployeeFrame implements Observer {

    private AuctionCentralEmployee currAdmin;
    private DataControlCenter dataControl;
    private JFrame frame;

    public AuctionCentralEmployeeFrame(AuctionCentralEmployee currAdmin, DataControlCenter dataControl) {
        this.currAdmin = currAdmin;
        this.dataControl = dataControl;
        this.frame = new JFrame();
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainScreen_Admin main = new MainScreen_Admin(currAdmin);
        main.addObserver(this);
        this.frame.add(main.getMainScreen(), BorderLayout.CENTER);

        this.frame.setSize(400,500);
        this.frame.setVisible(true);
    }


    @Override
    public void update(Observable parent, Object arg) {
        int result = (Integer) arg;
        if (result == 1) {
            System.out.println("First button");
        } else if (result == 2) {
            System.out.println("Second button");
        } else if (result == 3) {
            System.out.println("Third button");
        } else if (result == 4) {
            System.out.println("Fourth button");
        } else {
            System.out.println("Fail");
        }


    }
}
