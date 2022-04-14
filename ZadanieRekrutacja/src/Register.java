import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    private JPanel panel1;
    private JTextField idField;
    private JTextField passwordField1;
    private JButton registerButton;

    public Register(Client client, Platform platform, JComboBox comboBox) {
        idField.setText(String.valueOf(client.getId()));
        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordField1.getText().length() > 0){
                    client.setHashedpassword(passwordField1.getText().hashCode());
                    comboBox.addItem(client);
                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Register");
//        frame.setContentPane(new Register().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
