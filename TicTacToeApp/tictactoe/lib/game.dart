class Game {
  int counter = 0;
  int currIndex = 0;
  var list = [0, 0, 0, 0, 0, 0, 0, 0, 0];
  Game(int ctr) {
    this.counter = 0;
  }

  int win() {
    int result = 0;
    for (int i = 0; i < list.length; i += 3) {
      if (list[i] == list[i + 1] && list[i + 1] == list[i + 2] && list[i] != 0)
        result = list[i];
    }
    for (int i = 0; i <= 2; i++) {
      if (list[i] == list[i + 3] && list[i + 3] == list[i + 6] && list[i] != 0)
        result = list[i];
    }
    if (list[0] == list[4] && list[4] == list[8] && list[0] != 0)
      result = list[0];
    if (list[2] == list[4] && list[4] == list[6] && list[2] != 0)
      result = list[2];
    return result;
  }
}
