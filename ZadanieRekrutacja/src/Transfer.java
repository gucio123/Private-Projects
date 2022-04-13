import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Transfer {
    private JButton TransferDoneButton;
    private JTextField amountField;
    private JTextField recipientField;
    private JLabel recipientsId;
    private JLabel Amount;
    private JPanel panel1;

    public Transfer(Client client, Platform platform) {
        amountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        recipientField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        TransferDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (Integer.parseInt(String.valueOf(amountField.getText())) > 0 &&
                            Integer.parseInt(String.valueOf(amountField.getText())) <= client.getZlBalance()) {
                        if(platform.getListOfClients().containsKey(Integer.parseInt(recipientField.getText())))
                            platform.transfer(client, Integer.parseInt(recipientField.getText()),
                                    Integer.parseInt(amountField.getText()));
                    }
                }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Transfer");
//        frame.setContentPane(new Transfer().panel1);
//        frame.setDefaultCloseOperation(JFrame.);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
