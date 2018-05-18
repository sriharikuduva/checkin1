import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LoginScreen extends JOptionPane {
	 
	private JLabel userNameInput = new JLabel("Username: ");
	private JFrame frame = new JFrame("Parent");
	private String message = "Please input your username: ";
	
	

	public LoginScreen() {
		LoginScreen.showInputDialog(frame, message, "Login to Auction Central", OK_CANCEL_OPTION);
	}
	
	
}
