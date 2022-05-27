import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientsPanelTest {
    Platform platform = new Platform();
    Client client = new Client(platform);
    Client client2 = new Client(platform);
    @Test
    public void addingClient(){
        platform.getListOfClients().put(client.getId(), client);
        Assert.assertTrue(platform.getListOfClients().containsKey(client.getId()));
    }

    @Test
    public void deposit(){
        platform.deposit(client, new Transaction("deposit", 12, platform, "PLN", "-", "-"));
        Assert.assertTrue(client.getZlBalance() == 12 - 12 * platform.getDepositFee());
    }

    @Test
    public void withdrawal(){
        platform.deposit(client, new Transaction("deposit", 12, platform, "PLN", "-", "-"));
        double currentBalance = client.getZlBalance();
        platform.withdrawal(client, new Transaction("withdrawal", 10, platform, "PLN", "-", "-"));
        Assert.assertTrue(client.getZlBalance() == currentBalance - 10 - 10 * platform.getWithdrawalFee());
    }
    @Test
    public void transfer(){
        platform.deposit(client, new Transaction("deposit", 12, platform, "PLN", "-", "-"));
        double currentBalance = client.getZlBalance();
        platform.transfer(client, client2.getId(), 10);
        Assert.assertTrue(client2.getZlBalance() == 10
                && client.getZlBalance() == currentBalance - 10 - 10 * platform.getTransferFee());
    }

    @Test
    public void exchange(){
        platform.deposit(client, new Transaction("deposit", 12, platform, "PLN", "-", "-"));
        double currentBalance = client.getZlBalance();
        platform.exchange(client, "PLN", "USD", 8);
        Assert.assertTrue(client.getUsdBalance() == 8 * platform.getZlToUsdExchangeRate()
                && client.getZlBalance() == currentBalance - 8 - 8 * platform.getExchangeFee());
    }
}