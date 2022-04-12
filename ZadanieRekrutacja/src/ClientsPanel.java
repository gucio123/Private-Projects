import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientsPanel extends Thread{
    private JPanel panel1;
    private JButton Wpłata;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel currencies;
    private JLabel eurToZl;
    private JLabel eurToUsd;
    private JLabel ZlToUsd;
    private JLabel SaldoPln;
    private JLabel SaldoEur;
    private JLabel SaldoUsd;
    private JComboBox Clients;
    private JButton dodajKlientaButton;
    private Platform platform;
    private Client actualClient;
    public ClientsPanel() {
        platform = new Platform();
        start();
        Wpłata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });

        dodajKlientaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients.addItem(new Client(platform));
            }
        });
    }
    public void run(){
        while(true) {
            actualClient = (Client) Clients.getSelectedItem();
            try {
                refresh();
                Thread.sleep(1);
            }catch (InterruptedException exc){}
        }
    }
    private void refresh(){
        eurToUsd.setText("EUR  =>  USD:  " + String.valueOf(Math.round(platform.getEuroToUsdExchangeRate() * 100.0) / 100.0));
        eurToZl.setText("PLN  =>  EUR:  " + String.valueOf(Math.round(platform.getZlToEuroExchangeRate() * 100.0) / 100.0));
        ZlToUsd.setText("PLN  =>  USD:  " + String.valueOf(Math.round(platform.getZlToUsdExchangeRate() * 100.0) / 100.0));
        if(actualClient != null) {
            SaldoPln.setText("Saldo konta PLN:  " + String.valueOf(actualClient.getZlBalance()));
            SaldoEur.setText("Saldo konta EUR:  " + String.valueOf(actualClient.getEuroBalance()));
            SaldoUsd.setText("Saldo konta USD:  " + String.valueOf(actualClient.getUsdBalance()));
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ClientsPanel");
        frame.setContentPane(new ClientsPanel().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700,350);
    }
}
