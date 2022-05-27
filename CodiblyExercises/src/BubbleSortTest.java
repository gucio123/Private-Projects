import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class BubbleSortTest {

    BubbleSort instance = new BubbleSort();

    @Test
    public void firstTest(){
        List<Comparable> listToCompare = Arrays.asList(1,3,4,5,6,8,8);
        instance.setInput(Arrays.asList(1,4,5,6,8,3,8));
        Assert.assertArrayEquals(instance.sort(instance.getInput()).toArray(), listToCompare.toArray());
    }

    @Test
    public void secondTest(){
        List<Comparable> listToCompare = Arrays.asList(-9.3,0.1,0.2,4,5,9);
        instance.setInput(Arrays.asList(-9.3,0.2,4,0.1,5,9));
        Assert.assertArrayEquals(instance.sort(instance.getInput()).toArray(), listToCompare.toArray());
    }

    @Test
    public void thirdTest(){
        List<Comparable> listToCompare = Arrays.asList();
        instance.setInput(Arrays.asList());
        Assert.assertArrayEquals(instance.sort(instance.getInput()).toArray(), listToCompare.toArray());
    }

    @Test
    public void forthTest(){
        List<Comparable> listToCompare = Arrays.asList(5.0001);
        instance.setInput(Arrays.asList(null,5.0001));
        Assert.assertArrayEquals(instance.sort(instance.getInput()).toArray(), listToCompare.toArray());
    }

    @Test
    public void fifthTest(){
        instance.setInput(null);
        Assert.assertThrows(RuntimeException.class, () -> {
            instance.sort(instance.getInput());
        });
    }
}