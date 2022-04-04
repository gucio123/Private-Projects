import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyBackpack {

    public List<Items> listOfItems = new ArrayList<Items>(Arrays.asList(new Items("Skarpety",1, 2), new Items("taśmy",2, 5), new Items("Tank-top",2, 8),
            new Items("Pas",2, 5), new Items("rekawice",1, 1), new Items("Białko",4, 8),
            new Items("Trenbolon",6, 18), new Items("Kreatyna",5, 10), new Items("tygrys bubblegum",4, 6)));


    private List<Items> backpack = new ArrayList<>();
    private List<Items> items = listOfItems;
    private double wage;
    private int bkpkMaxCapacity = 20;
    private int bkpkCapacity = 0;
    private int temp = 0;

    public List<Items> getBackpack() {
        return backpack;
    }

    public List<Items> getItems() {
        return items;
    }

    public void greedyBackpack() {
        for (int k = 0; k < this.getItems().toArray().length; k++) {
            double maxi = 0;
            int index = 0;
            int bkpkMaxCapacity = 20;
            for (int i = 0; i < this.getItems().toArray().length; i++) {
                if(this.backpack.contains(this.getItems().get(i))){
                    continue;
                }
                wage = this.getItems().get(i).getValue() / this.getItems().get(i).getWeight();
                if (wage > maxi) {
                    maxi = wage;
                    index = i;
                }
                if (i == this.getItems().toArray().length - 1) {
                    if (bkpkCapacity + this.getItems().get(i).getWeight() < 20) {
                        this.backpack.add(this.getItems().get(index));
                        bkpkCapacity += this.backpack.get(temp).getWeight();
                        temp++;
                    }
                }
            }
        }
        for(int m=0;m <this.backpack.toArray().length;m++){
            System.out.println(this.backpack.get(m).getName());
        }
    }
    public static void main(String[] args) {
        GreedyBackpack backpack = new GreedyBackpack();
        backpack.greedyBackpack();
    }
}
