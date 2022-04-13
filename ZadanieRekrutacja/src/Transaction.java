import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Transaction {
    private int id;
    private String type;
    private Date date;
    private int amount;
    private String currency;
    private String from;
    private String to;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public Transaction(String type, int amount, Platform platform, String currency, String from, String to) {
        do{
            this.id = new Random().nextInt(250000);
        }while(platform.getListOfTransactions().containsKey(this.id));
        platform.getListOfTransactions().put(this.id, this);
        this.type = type;
        this.date = new Date();
        this.amount = amount;
        this.currency = currency;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
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

    public String getCurrency() {
        return currency;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
