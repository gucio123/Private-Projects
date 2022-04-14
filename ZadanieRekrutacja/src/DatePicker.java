import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class DatePicker {
    private JTextField DayFieldFrom;
    private JTextField MonthFieldFrom;
    private JTextField YearFieldFrom;
    private JTextField HourFieldFrom;
    private JTextField MinuteFieldFrom;
    private JTextField SecondFieldFrom;
    private JPanel panel1;
    private JButton confirmButton;
    private JTextField DayFieldTO;
    private JTextField MonthFieldTo;
    private JTextField YearFieldTo;
    private JTextField HourFieldTo;
    private JTextField MinuteFieldTo;
    private JTextField SecondFieldTo;

    public DatePicker(Client client, Platform platform) {
        DayFieldFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        MonthFieldFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        YearFieldFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        HourFieldFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        MinuteFieldFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        SecondFieldFrom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar dateFrom = Calendar.getInstance();
                Calendar dateTo = Calendar.getInstance();
                if(DayFieldFrom.getText().length() == 2 && MonthFieldFrom.getText().length() == 2
                && YearFieldFrom.getText().length() == 4 && HourFieldFrom.getText().length() == 2
                && MinuteFieldFrom.getText().length() == 2 && SecondFieldFrom.getText().length() == 2
                && DayFieldTO.getText().length() == 2 && MonthFieldTo.getText().length() == 2
                        && YearFieldTo.getText().length() == 4 && HourFieldTo.getText().length() == 2
                        && MinuteFieldTo.getText().length() == 2 && SecondFieldTo.getText().length() == 2){
                    dateFrom.set(Integer.parseInt(YearFieldFrom.getText()), Integer.parseInt(MonthFieldFrom.getText()),
                            Integer.parseInt(DayFieldFrom.getText()), Integer.parseInt(HourFieldFrom.getText()),
                            Integer.parseInt(MinuteFieldFrom.getText()), Integer.parseInt(SecondFieldFrom.getText()));

                    dateTo.set(Integer.parseInt(YearFieldTo.getText()), Integer.parseInt(MonthFieldTo.getText()),
                            Integer.parseInt(DayFieldTO.getText()), Integer.parseInt(HourFieldTo.getText()),
                            Integer.parseInt(MinuteFieldTo.getText()), Integer.parseInt(SecondFieldTo.getText()));
                    JFrame frame = new JFrame("DateHistory");
                    frame.setContentPane(new History(platform.dateHistory(client.getListOfTransactions(),
                            dateFrom,dateTo)).getPanel1());
                    frame.pack();
                    frame.setVisible(true);
                }


            }
        });
        DayFieldTO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        MonthFieldTo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        YearFieldTo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        HourFieldTo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        MinuteFieldTo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        SecondFieldTo.addKeyListener(new KeyAdapter() {
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

    public static void main(String[] args) {
//        JFrame frame = new JFrame("DatePicker");
//        frame.setContentPane(new DatePicker().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
}
