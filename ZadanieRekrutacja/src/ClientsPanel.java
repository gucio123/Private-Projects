import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class ClientsPanel extends Thread{
    //deklaracja zmiennych
    private JPanel panel1;
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
    private JLabel profit;
    private Platform platform;
    private Client actualClient;
    ClientsPanel var;
    public ClientsPanel() { //konstruktor
        var = this; //zmienna potrzebna do funkcji
        platform = new Platform(); //tworzenie platformy
        start(); //watek odswiezający aplikację co 1ms
        dodajKlientaButton.addActionListener(new ActionListener() { //uruchamia okienko rejestracji klienta
            @Override
            public void actionPerformed(ActionEvent e) {
                Client newClient = new Client(platform);
                JFrame frame = new JFrame("Register");
                frame.setContentPane(new Register(newClient, platform, Clients).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        Deposit.addActionListener(new ActionListener() { //przycisk depozytu
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(DepositAmount.getText().length() > 0){
                        Transaction deposit = new Transaction("deposit",
                                Integer.parseInt(DepositAmount.getText()), platform, "PLN", "-", "-");
                        actualClient.getListOfTransactions().add(deposit);
                        platform.deposit(actualClient,deposit);
                    }
                    DepositAmount.setText("");
                }
        });
        Withdrawal.addActionListener(new ActionListener() { //przycisk wypłaty
            @Override
            public void actionPerformed(ActionEvent e) {
                if(WithdrawalAmount.getText().length() > 0)
                    if(actualClient.getZlBalance() >= Integer.parseInt(WithdrawalAmount.getText()) +
                            platform.getWithdrawalFee()*Integer.parseInt(WithdrawalAmount.getText())){
                        Transaction withdrawal = new Transaction("withdrawal",
                                Integer.parseInt(WithdrawalAmount.getText()), platform, "PLN", "-", "-");
                        actualClient.getListOfTransactions().add(withdrawal);
                        platform.withdrawal(actualClient, withdrawal);
                    }
                    WithdrawalAmount.setText("");
                }
        });
        Transfer.addActionListener(new ActionListener() { //przycisk przelewu
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transfer = new JFrame("Transfer");
                transfer.setContentPane(new Transfer(actualClient, platform).getPanel1());
                transfer.pack();
                transfer.setVisible(true);
            }
        });
        changingCurrency.addActionListener(new ActionListener() { //przycisk wymiany waluty
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ExchangingCurrencyGUI");
                frame.setContentPane(new ExchangingCurrencyGUI(actualClient, platform).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        DepositAmount.addKeyListener(new KeyAdapter() { //textfield ilosci do depozytu
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                } // schemat do zablokowania mozliwosci wpisania czegokolwiek innego niz liczby

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
        history.addActionListener(new ActionListener() { //przycisk historii
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actualClient != null) {
                    JFrame frame = new JFrame("HistorySorter");
                    frame.setContentPane(new HistorySorter(platform, actualClient).getPanel1());
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        historyPLN.addActionListener(new ActionListener() { //nastepnie trzy przyciski historii poszczegolnych walut
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actualClient != null) {
                    JFrame frame = new JFrame("History");
                    frame.setContentPane(new History(platform.currencyHistory(actualClient.getListOfTransactions(),
                            "PLN")).getPanel1());
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        historyEUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actualClient != null){
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.currencyHistory(actualClient.getListOfTransactions(),
                        "EUR")).getPanel1());
                frame.pack();
                frame.setVisible(true);
                }
            }
        });
        historyUSD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actualClient != null) {
                    JFrame frame = new JFrame("History");
                    frame.setContentPane(new History(platform.currencyHistory(actualClient.getListOfTransactions(),
                            "USD")).getPanel1());
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        Clients.addActionListener(new ActionListener() { // listener do comboBoxa z klientami

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("SignIn");
                frame.setContentPane(new SignIn((Client)Clients.getSelectedItem(), var).getPanel1()); // wymóg zalogowania się na konto
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public void run(){ // wątek refreshujący
        while(true) {
            try {
                refresh();
                Thread.sleep(1);
            }catch (InterruptedException exc){}
        }
    }
    private void refresh(){ // odswiezamy pola tekstowe i zabezpieczamy się przed dostaniem do klienta, który nie istenieje
        eurToUsd.setText("EUR  =>  USD:  " + String.valueOf(Math.round(platform.getEuroToUsdExchangeRate() * 100.0) / 100.0));
        eurToZl.setText("PLN  =>  EUR:  " + String.valueOf(Math.round(platform.getZlToEuroExchangeRate() * 100.0) / 100.0));
        ZlToUsd.setText("PLN  =>  USD:  " + String.valueOf(Math.round(platform.getZlToUsdExchangeRate() * 100.0) / 100.0));
        if(actualClient != null) {
            SaldoPln.setText("Saldo konta PLN:  " + String.valueOf(Math.round(actualClient.getZlBalance()* 100.0) / 100.0));
            SaldoEur.setText("Saldo konta EUR:  " + String.valueOf(Math.round(actualClient.getEuroBalance() * 100.0) / 100.0));
            SaldoUsd.setText("Saldo konta USD:  " + String.valueOf(Math.round(actualClient.getUsdBalance() * 100.0) / 100.0));
            profit.setText(String.valueOf(Math.round((actualClient.getZlBalance() - actualClient.getInput()) * 100.0)/100.0));
        }
    }

    public JComboBox getClients() {
        return Clients;
    }

    public void setActualClient(Client actualClient) {
        this.actualClient = actualClient;
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
