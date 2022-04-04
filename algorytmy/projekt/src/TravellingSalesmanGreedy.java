import java.util.*;

public class TravellingSalesmanGreedy {

    public List<City> cities = new ArrayList<City>(Arrays.asList(new City("Warsaw", 417, 224, 1), new City("Moskwa", 450, 209, 2),
            new City("Berlin", 402, 225, 3), new City("Kair", 441, 278, 4), new City("Waszyngton", 223, 257, 5),
            new City("Tokio", 667, 256, 6), new City("Canberra", 687, 422, 7),
            new City("Pretoria", 437, 397, 8), new City("Antananarywa", 477, 380, 9),
            new City("Pekin", 614, 252, 10), new City("Nur-Sułtan", 517, 224, 11),
            new City("Nowe Delhi", 468, 288, 12), new City("Buenos Aires", 259, 424, 13),
            new City("Brasilia", 278, 377, 14), new City("Bogota", 223, 334, 15), new City("Ottawa", 232, 234, 16)));

    private List<City> finalList = new ArrayList<>();
    private List<City> list = cities;
    private int totalDist = 0;

    public int getTotalDist() {
        return totalDist;
    }

    public List<City> getList() {
        return list;
    }

    public List<City> getFinalList() {
        return finalList;
    }

    public void greedyAlgorithm() {
        TravellingSalesmanGreedy list = new TravellingSalesmanGreedy();
        list.finalList.add(list.getList().get(4));
        list.getList().remove(4);
        for (int k = 0; k < list.getList().toArray().length; k++) {
            int index = 0;
            int miniDist = 9999999;
            for (int i = 0; i < list.getList().toArray().length; i++) {
                if (list.finalList.contains(list.getList().get(i))) {
                    continue;
                }
                int distance = (int) Math.sqrt(Math.pow(list.finalList.get(k).getX() - list.list.get(i).getX(), 2) +
                        Math.pow(list.finalList.get(k).getY() - list.list.get(i).getY(), 2));
                if (distance < miniDist) {
                    miniDist = distance;
                    index = i;
                }
            }
            list.finalList.add(list.getList().get(index));
            this.totalDist += miniDist;
        }
        this.getFinalList().addAll(list.getFinalList());
    }


    public static void main(String[] args) {
        TravellingSalesmanGreedy zachlanny = new TravellingSalesmanGreedy();
        zachlanny.greedyAlgorithm();
    }
}
