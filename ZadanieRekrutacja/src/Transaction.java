import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Transaction {
    private int id;
    private String type;
    private Date date;
    private int amount;
    private String currency;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public Transaction(String type, int amount, Platform platform, String currency) {
        do{
            this.id = new Random().nextInt(250000);
        }while(platform.getListOfTransactions().containsKey(this.id));
        platform.getListOfTransactions().put(this.id, this);
        this.type = type;
        this.date = new Date();
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
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
