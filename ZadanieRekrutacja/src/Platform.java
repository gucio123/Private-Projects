import java.util.HashMap;
import java.util.Random;

public class Platform extends Thread{
    private HashMap<Integer, Client> listOfClients;
    private HashMap<Integer, Transaction> listOfTransactions;
    private double euroToUsdExchangeRate, zlToUsdExchangeRate, zlToEuroExchangeRate;
    private int eurPlnFluctuation, eurUsdFluctuation, UsdPlnFluctuation;
    private double EurPlnAmount, EurUsdAmount, UsdPlnAmount;
    public Platform() {
        this.listOfClients = new HashMap<>();
        this.listOfTransactions = new HashMap<>();
        this.euroToUsdExchangeRate = 1.09;
        this.zlToEuroExchangeRate = 0.22;
        this.zlToUsdExchangeRate = 0.23;
        start();
    }

    public void deposit(Client client, Transaction transaction){
        client.setZlBalance(client.getZlBalance() + transaction.getAmount());
    }
    public void withdrawal(Client client, Transaction transaction){
        client.setZlBalance(client.getZlBalance() - transaction.getAmount());
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
    public void transfer(Client client, int id, int amount){
        client.setZlBalance(client.getZlBalance() - amount);
        listOfClients.get(id).setZlBalance(listOfClients.get(id).getZlBalance() + amount);
        Transaction transaction = new Transaction("transfer", amount, this, "PLN",
                String.valueOf(client.getId()), String.valueOf(id));
        listOfClients.get(id).getListOfTransactions().add(transaction);
        listOfTransactions.put(transaction.getId(), transaction);
        transaction = new Transaction("transfer", amount * (-1), this, "PLN",
                String.valueOf(client.getId()), String.valueOf(id));
        client.getListOfTransactions().add(transaction);
    }

    public void exchange(Client client, String from, String to, int amount){
        switch (from) {
            case "PLN":
                if(client.getZlBalance() >= amount){
                    if(to.equals("EUR")) {
//                        Transaction exchange = new Transaction("exchange", amount, this, "PLN");
                        client.setZlBalance(client.getZlBalance() - amount);
                        client.setEuroBalance(client.getEuroBalance() + Math.round((amount * zlToEuroExchangeRate)*100.0)/100.0);

                    }
                }
                break;
            case "EUR":

                break;
            case "USD":

                break;
        }
    }
    public HashMap<Integer, Client> getListOfClients() {
        return listOfClients;
    }

    public HashMap<Integer, Transaction> getListOfTransactions() {
        return listOfTransactions;
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
