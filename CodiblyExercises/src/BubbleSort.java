import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BubbleSort {
    private List<Comparable> input;
    public List<Comparable> sort(List<Comparable> input) throws RuntimeException{
        List<Comparable> result = new ArrayList<>();
            if (input == null)
                throw new RuntimeException();
            int size = input.toArray().length;
            for (int i = 0; i < size - 1; i++) {
                if (input.get(i) == null)
                    continue;
                for (int j = 0; j < size - 1; j++) {
                    if (input.get(j + 1) == null)
                        continue;
                    else if (input.get(j).toString().compareTo(input.get(j + 1).toString()) > 0)
                        Collections.swap(input, j + 1, j);
                }
            }
            for(int i = 0; i < size ; i ++)
                if(input.get(i) != null)
                    result.add(input.get(i));
        return result;
    }

    public List<Comparable> getInput() {
        return input;
    }

    public void setInput(List<Comparable> input) {
        this.input = input;
    }

}
