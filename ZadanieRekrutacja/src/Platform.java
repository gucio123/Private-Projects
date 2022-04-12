import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Platform extends Thread{
    private HashMap<Integer, Client> listOfClients;
    private double euroToUsdExchangeRate, zlToUsdExchangeRate, zlToEuroExchangeRate;
    private int eurPlnFluctuation, eurUsdFluctuation, UsdPlnFluctuation;
    private double EurPlnAmount, EurUsdAmount, UsdPlnAmount;
    public Platform() {
        this.listOfClients = new HashMap<>();
        this.euroToUsdExchangeRate = 1.09;
        this.zlToEuroExchangeRate = 0.22;
        this.zlToUsdExchangeRate = 0.23;
        start();
    }

    public void run(){
        while(true) {
            try {
                Random random = new Random();
                this.eurPlnFluctuation = random.nextInt(3);
                this.eurUsdFluctuation = random.nextInt(3);
                this.UsdPlnFluctuation = random.nextInt(3);
                this.EurPlnAmount = random.nextInt(5) / 100.0;
                this.EurUsdAmount = random.nextInt(5) / 100.0;
                this.UsdPlnAmount = random.nextInt(5) / 100.0;
                if (eurPlnFluctuation == 1)
                    this.zlToEuroExchangeRate += EurPlnAmount;
                else if (eurPlnFluctuation == 2)
                    this.zlToEuroExchangeRate -= EurPlnAmount;
                if (eurUsdFluctuation == 1)
                    this.euroToUsdExchangeRate += EurUsdAmount;
                else if (eurUsdFluctuation == 2)
                    this.euroToUsdExchangeRate -= EurUsdAmount;
                if (UsdPlnFluctuation == 1)
                    this.zlToUsdExchangeRate += UsdPlnAmount;
                else if (UsdPlnFluctuation == 2)
                    this.zlToUsdExchangeRate -= UsdPlnAmount;
                Thread.sleep(5000);
            }catch (InterruptedException exc){}
        }
    }

    public HashMap<Integer, Client> getListOfClients() {
        return listOfClients;
    }

    public double getEuroToUsdExchangeRate() {
        return euroToUsdExchangeRate;
    }

    public double getZlToUsdExchangeRate() {
        return zlToUsdExchangeRate;
    }

    public double getZlToEuroExchangeRate() {
        return zlToEuroExchangeRate;
    }

    public static void main(String[] args) {
        Platform platform = new Platform();
        Client client = new Client(platform);
        platform.listOfClients.put(client.getId(), client);

        System.out.println(platform.listOfClients);
    }
}
