import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Platform extends Thread{
    private HashMap<Integer, Client> listOfClients;
    private HashMap<Integer, Transaction> listOfTransactions;
    private double euroToUsdExchangeRate, zlToUsdExchangeRate, zlToEuroExchangeRate;
    private int eurPlnFluctuation, eurUsdFluctuation, UsdPlnFluctuation;
    private double EurPlnAmount, EurUsdAmount, UsdPlnAmount;
    private double transferFee, exchangeFee, depositFee, withdrawalFee;
    public Platform() {
        this.listOfClients = new HashMap<>();
        this.listOfTransactions = new HashMap<>();
        this.euroToUsdExchangeRate = 1.09;
        this.zlToEuroExchangeRate = 0.22;
        this.zlToUsdExchangeRate = 0.23;
        this.transferFee = 0.03;
        this.exchangeFee = 0.05;
        this.depositFee = 0.02;
        this.withdrawalFee = 0.01;
        start();
    }

    public void deposit(Client client, Transaction transaction){
        client.setZlBalance(client.getZlBalance() + transaction.getAmount() - transaction.getAmount() * depositFee);
        client.setInput( (client.getInput() + transaction.getAmount() - transaction.getAmount() * depositFee));
    }
    public void withdrawal(Client client, Transaction transaction){
        client.setZlBalance(client.getZlBalance() - transaction.getAmount() - transaction.getAmount() * withdrawalFee);
    }
    public void run(){
        while(true) {
            try {
                Random random = new Random();
                this.eurPlnFluctuation = random.nextInt(3);
                this.eurUsdFluctuation = random.nextInt(3);
                this.UsdPlnFluctuation = random.nextInt(3);
                this.EurPlnAmount = random.nextInt(2) / 100.0;
                this.EurUsdAmount = random.nextInt(2) / 100.0;
                this.UsdPlnAmount = random.nextInt(2) / 100.0;
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
                Thread.sleep(10000);
            }catch (InterruptedException exc){}
        }
    }
    public void transfer(Client client, int id, int amount){
        client.setZlBalance(client.getZlBalance() - amount - amount * transferFee);
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
                if(client.getZlBalance() >= amount + amount * exchangeFee){
                    if(to.equals("EUR")) {
                        Transaction exchange = new Transaction("exchange", amount,
                                this, "PLN", "PLN", "EUR");
                        client.setZlBalance(client.getZlBalance() - amount - amount * exchangeFee);
                        client.setEuroBalance(client.getEuroBalance() + Math.round((amount * zlToEuroExchangeRate)*100.0)/100.0);
                        client.getListOfTransactions().add(exchange);
                        this.listOfTransactions.put(exchange.getId(), exchange);
                    }
                    else if(to.equals("USD")){
                        Transaction exchange = new Transaction("exchange", amount,
                                this, "PLN", "PLN", "USD");
                        client.setZlBalance(client.getZlBalance() - amount - amount * exchangeFee);
                        client.setUsdBalance(client.getUsdBalance() + Math.round((amount * zlToUsdExchangeRate)*100.0)/100.0);
                        client.getListOfTransactions().add(exchange);
                        this.listOfTransactions.put(exchange.getId(), exchange);
                    }
                }
                break;
            case "EUR":
                if(client.getEuroBalance() >= amount + amount * exchangeFee){
                    if(to.equals("PLN")){
                        Transaction exchange = new Transaction("exchange", amount,
                                this, "EUR", "EUR", "PLN");
                        client.setEuroBalance(client.getEuroBalance() - amount - amount * exchangeFee);
                        client.setZlBalance(client.getZlBalance() + Math.round((amount / zlToEuroExchangeRate)*100.0)/100.0);
                        client.getListOfTransactions().add(exchange);
                        this.listOfTransactions.put(exchange.getId(), exchange);
                    }
                    else if(to.equals("USD")){
                        Transaction exchange = new Transaction("exchange", amount, this,
                                "EUR", "EUR", "USD");
                        client.setEuroBalance(client.getEuroBalance() - amount - amount * exchangeFee);
                        client.setUsdBalance(client.getUsdBalance() + Math.round((amount * euroToUsdExchangeRate)*100.0)/100.0);
                        client.getListOfTransactions().add(exchange);
                        this.listOfTransactions.put(exchange.getId(), exchange);
                    }
                }
                break;
            case "USD":
                if(client.getUsdBalance() >= amount + amount * exchangeFee){
                    if(to.equals("PLN")){
                        Transaction exchange = new Transaction("exchange", amount,
                                this, "USD", "USD", "PLN");
                        client.setUsdBalance(client.getUsdBalance() - amount - amount * exchangeFee);
                        client.setZlBalance(client.getZlBalance() + Math.round((amount / zlToUsdExchangeRate)*100.0)/100.0);
                        client.getListOfTransactions().add(exchange);
                        this.listOfTransactions.put(exchange.getId(), exchange);
                    }
                    else if(to.equals("EUR")){
                        Transaction exchange = new Transaction("exchange", amount, this,
                                "USD", "USD", "EUR");
                        client.setUsdBalance(client.getUsdBalance() - amount - amount * exchangeFee);
                        client.setEuroBalance(client.getEuroBalance() + Math.round((amount / euroToUsdExchangeRate)*100.0)/100.0);
                        client.getListOfTransactions().add(exchange);
                        this.listOfTransactions.put(exchange.getId(), exchange);
                }
                break;
        }}
    }

    public List<Transaction> currencyHistory(List<Transaction> list, String currency){
        List<Transaction> newlist;
        newlist = list.stream()
                .filter(n -> n.getFrom() == currency || n.getCurrency() == currency || n.getTo() == currency)
                .collect(Collectors.toList());
        return newlist;
    }

    public List<Transaction> typeHistory(List<Transaction> list, String type){
        List<Transaction> newList;
        newList = list.stream()
                .filter(n -> n.getType().equals(type))
                .collect(Collectors.toList());
        return newList;
    }

    public List<Transaction> dateHistory(List<Transaction> list, Calendar dateFrom, Calendar dateTo){
        List<Transaction> newList;
        newList = list.stream()
                .filter(n -> n.getDate().before(dateTo) && n.getDate().after(dateFrom))
                .collect(Collectors.toList());
        return newList;
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

    public double getTransferFee() {
        return transferFee;
    }

    public double getExchangeFee() {
        return exchangeFee;
    }

    public double getDepositFee() {
        return depositFee;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public static void main(String[] args) {
        Platform platform = new Platform();
        Client client = new Client(platform);
        platform.listOfClients.put(client.getId(), client);

        System.out.println(platform.listOfClients);
    }
}
