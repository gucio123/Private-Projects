import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistorySorter {
    private JPanel panel1;
    private JRadioButton depositButton;
    private JRadioButton withdrawalButton;
    private JRadioButton exchangeButton;
    private JRadioButton transferButton;
    private JButton dateSort;

    public HistorySorter(Platform platform, Client client){
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.typeHistory(client.getListOfTransactions(),
                        "deposit")).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        withdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.typeHistory(client.getListOfTransactions(),
                        "withdrawal")).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.typeHistory(client.getListOfTransactions(),
                        "exchange")).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("History");
                frame.setContentPane(new History(platform.typeHistory(client.getListOfTransactions(),
                        "transfer")).getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        dateSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dateSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("DatePicker");
                frame.setContentPane(new DatePicker().getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
//        JFrame frame = new JFrame("HistorySorter");
//        frame.setContentPane(new HistorySorter().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
}
