import javax.swing.*;
import java.util.List;

public class History {
    private JPanel panel1;
    private JList list1;

    public History(List<Transaction> listOfTransactions){
        list1.setListData(listOfTransactions.toArray());
    }

    public JPanel getPanel1() {
        return panel1;
    }

}