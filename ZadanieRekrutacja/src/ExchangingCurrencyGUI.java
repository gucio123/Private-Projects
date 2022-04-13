import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ExchangingCurrencyGUI {
    private JButton changeButton;
    private JComboBox Currency2;
    private JComboBox Currency1;
    private JTextField amountOfMoney;
    private JPanel panel1;
    private String[] currencies = {"PLN", "EUR", "USD"};
    public ExchangingCurrencyGUI(Client client, Platform platform) {
        Currency1.addItem("PLN");
        Currency1.addItem("EUR");
        Currency1.addItem("USD");
        makingNewComboBox();
        amountOfMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        Currency1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makingNewComboBox();
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                platform.exchange(client, ( String ) Currency1.getSelectedItem(), ( String ) Currency2.getSelectedItem(),
                        Integer.parseInt(amountOfMoney.getText()));
            }
        });
    }
    private void makingNewComboBox(){
        Currency2.removeAllItems();
        for(int i = 0; i < currencies.length; i++){
            if(!Currency1.getSelectedItem().equals(currencies[i])) {
                Currency2.addItem(currencies[i]);
            }
        }
    }
    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ExchangingCurrencyGUI");
//        frame.setContentPane(new ExchangingCurrencyGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
