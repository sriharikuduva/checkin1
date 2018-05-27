import javax.swing.*;
import java.awt.*;

/**
 * @author MauriceChiu
 */
public class EnhancedOptionPane {
    public static String showInputDialog(final Object message, final Object[] options) throws HeadlessException {
        final JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, options);

        pane.setWantsInput(true);
        //pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        //pane.setMessageType(PLAIN_MESSAGE);
        //pane.selectInitialValue();
        //pane.setOptions();


        final String title = "Place a bid"; //UIManager.getString("OptionPane.inputDialogTitle", null);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setVisible(true);
        dialog.dispose();
        final Object value = pane.getInputValue();

        return (value == JOptionPane.UNINITIALIZED_VALUE) ? null : (String) value;
    }
}
