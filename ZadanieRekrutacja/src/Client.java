import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    private double euroBalance;
    private double usdBalance;
    private double zlBalance;
    private int id;
    private String login;
    private int hashedpassword;
    private List<Transaction> listOfTransactions;
    public Client(Platform platform) {
        do{
            this.id = new Random().nextInt(16000);
        }while(platform.getListOfClients().containsKey(this.id));
        platform.getListOfClients().put(this.getId(), this);
        this.euroBalance = 0;
        this.usdBalance = 0;
        this.zlBalance = 0;
        this.listOfTransactions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
    }

    public List<Transaction> getListOfTransactions() {
        return listOfTransactions;
    }

    public double getEuroBalance() {
        return euroBalance;
    }

    public void setEuroBalance(double euroBalance) {
        this.euroBalance = euroBalance;
    }

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    public double getZlBalance() {
        return zlBalance;
    }

    public void setZlBalance(double zlBalance) {
        this.zlBalance = zlBalance;
    }

    public int getId() {
        return id;
    }

    public int getHashedpassword() {
        return hashedpassword;
    }

    public void setHashedpassword(int hashedpassword) {
        this.hashedpassword = hashedpassword;
    }
}
