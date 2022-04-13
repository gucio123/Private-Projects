import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientsPanel extends Thread{
    private JPanel panel1;
    private JButton Wpłata;
    private JPanel currencies;
    private JLabel eurToZl;
    private JLabel eurToUsd;
    private JLabel ZlToUsd;
    private JLabel SaldoPln;
    private JLabel SaldoEur;
    private JLabel SaldoUsd;
    private JComboBox Clients;
    private JButton dodajKlientaButton;
    private JButton Transfer;
    private JButton changingCurrency;
    private JButton Deposit;
    private JButton Withdrawal;
    private JTextField DepositAmount;
    private JTextField WithdrawalAmount;
    private JButton history;
    private JButton historyPLN;
    private JButton historyEUR;
    private JButton historyUSD;
    private Platform platform;
    private Client actualClient;
    public ClientsPanel() {
        platform = new Platform();
        start();
        dodajKlientaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client newClient = new Client(platform);
                Clients.addItem(newClient);
            }
        });
        Deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(Integer.parseInt(DepositAmount.getText()) > 0){
                        Transaction deposit = new Transaction("deposit",
                                Integer.parseInt(DepositAmount.getText()), platform, "PLN", "-", "-");
                        actualClient.getListOfTransactions().add(deposit);
                        platform.deposit(actualClient,deposit);
                    }
                    DepositAmount.setText("");
                }
        });
        Withdrawal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(actualClient.getZlBalance() >= Integer.parseInt(WithdrawalAmount.getText())){
                        Transaction withdrawal = new Transaction("withdrawal",
                                Integer.parseInt(WithdrawalAmount.getText()), platform, "PLN", "-", "-");
                        actualClient.getListOfTransactions().add(withdrawal);
                        platform.withdrawal(actualClient, withdrawal);
                    }
                    WithdrawalAmount.setText("");
                }
        });
        Transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transfer = new JFrame("Transfer");
                transfer.setContentPane(new Transfer(actualClient, platform).getPanel1());
                transfer.pack();
                transfer.setVisible(true);
            }
        });
        changingCurrency.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ExchangingCurrencyGUI");
                frame.setContentPane(new ExchangingCurrencyGUI(actualClient, platform).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        DepositAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }

            }
        });
        WithdrawalAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(actualClient.getListOfTransactions()).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        historyPLN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.currencyHistory(actualClient.getListOfTransactions(),
                        "PLN")).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        historyEUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.currencyHistory(actualClient.getListOfTransactions(),
                        "EUR")).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        historyUSD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.currencyHistory(actualClient.getListOfTransactions(),
                        "USD")).getPanel1());
                frame.pack();
                frame.setVisible(true);
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
            SaldoPln.setText("Saldo konta PLN:  " + String.valueOf(Math.round(actualClient.getZlBalance()* 100.0) / 100.0));
            SaldoEur.setText("Saldo konta EUR:  " + String.valueOf(Math.round(actualClient.getEuroBalance() * 100.0) / 100.0));
            SaldoUsd.setText("Saldo konta USD:  " + String.valueOf(Math.round(actualClient.getUsdBalance() * 100.0) / 100.0));

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
