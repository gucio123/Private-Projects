import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Transaction {
    private int id;
    private String type;
    private Date date;
    private int amount;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public Transaction(String type, int amount, Platform platform) {
        do{
            this.id = new Random().nextInt(250000);
        }while(platform.getListOfTransactions().containsKey(this.id));
        platform.getListOfTransactions().put(this.id, this);
        this.type = type;
        this.date = new Date();
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + formatter.format(date) +
                ", amount=" + amount +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
