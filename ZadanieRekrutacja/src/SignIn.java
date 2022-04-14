import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignIn {
    private JButton confirmButton;
    private JTextField IdField;
    private JTextField passwordField;
    private JLabel id;
    private JLabel password;
    private JPanel panel1;

    public SignIn(Client client, ClientsPanel panel) {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(IdField.getText().equals(String.valueOf(client.getId()))
                        && client.getHashedpassword() == passwordField.getText().hashCode()){
                    panel.setActualClient(client); // jesli hashe haseł się pokrywają, to wybrany klient staje się klientem na ktorym mozemy dzialac
                }
            }
        });
        IdField.addKeyListener(new KeyAdapter() {
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
