import org.junit.Assert;
import org.junit.jupiter.api.Test;
class BalancedWordsCounterTest {

    BalancedWordsCounter instance = new BalancedWordsCounter();

    @Test
    public void firsttest(){
        instance.setWord("aabbabcccba");
        Assert.assertEquals(instance.count(instance.getWord()), 28);
    }

    @Test
    public void secondTest(){
        instance.setWord("");
        Assert.assertEquals(instance.count(instance.getWord()), 0);
    }

    @Test
    public void thirdTest(){
        instance.setWord("abababa1");
        Assert.assertThrows(RuntimeException.class, () -> {
            instance.count(instance.getWord());
        });
    }

    @Test
    public void forthTest(){
        instance.setWord(null);
        Assert.assertThrows(RuntimeException.class, () -> {
            instance.count(instance.getWord());
        });
    }


}