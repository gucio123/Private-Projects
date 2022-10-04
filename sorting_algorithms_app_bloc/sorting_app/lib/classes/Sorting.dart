class Sorting {
  late List<int> list_to_sort;

  Sorting() {
    for (int i = 1; i <= 50; i++) this.list_to_sort.add(i);
    list_to_sort.shuffle();
  }
}
