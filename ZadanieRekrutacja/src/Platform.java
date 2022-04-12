import java.util.HashMap;

public class Platform {
    private HashMap<Integer, Client> listOfClients = new HashMap<>();
    private double euroExchangeRate;
    private double usdExchangeRate;
    private double zlExchangeRate;

    public HashMap<Integer, Client> getListOfClients() {
        return listOfClients;
    }

    public static void main(String[] args) {
        Platform platform = new Platform();
        Client client = new Client(platform);
        platform.listOfClients.put(client.getId(), client);

        System.out.println(platform.listOfClients);
    }
}
