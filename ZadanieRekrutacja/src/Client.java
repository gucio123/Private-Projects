import java.util.Random;

public class Client {
    private double euroBalance;
    private double usdBalance;
    private double zlBalance;
    private int id;
    private String login;
    private String password;

    public Client(Platform platform) {
        do{
            this.id = new Random().nextInt(16000);
        }while(platform.getListOfClients().containsKey(this.id));
        this.euroBalance = 0;
        this.usdBalance = 0;
        this.zlBalance = 0;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
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
}
