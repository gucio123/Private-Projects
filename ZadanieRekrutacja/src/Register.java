import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register {
    private JPanel panel1;
    private JTextField idField;
    private JTextField passwordField1;
    private JButton registerButton;
    private JTextField baiscInput;
    private JFrame frame;
    public Register(Client client, Platform platform, JComboBox comboBox, JFrame frame) {
        idField.setText(String.valueOf(client.getId()));
        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // rejestracja
                if(passwordField1.getText().length() > 0 && baiscInput.getText().length() > 0){
                    client.setHashedpassword(passwordField1.getText().hashCode()); //hashowanie hasla
                    Transaction deposit = new Transaction("deposit",
                            Integer.parseInt(baiscInput.getText()), platform, "PLN", "-", "-"); // pierwszy depozyt
                    client.getListOfTransactions().add(deposit);
                    platform.deposit(client,deposit);
                    comboBox.addItem(client);
                    frame.dispose();
                }
            }
        });
        baiscInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

}
