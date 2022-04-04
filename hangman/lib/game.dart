import 'dart:math';

class Game {
  int mistakesCounter = 0;
  String word = "";
  List<String> listOfWords = [
    'INFORMATYKA',
    'PROGRAMOWANIE',
    'BARCELONA',
    'KOMPUTER',
    'STUDIA',
    'KOSZYKOWKA',
    'SPORT',
    'GITARA',
    'APLIKACJA',
    'LEWANDOWSKI',
    'MESSI',
    'RONALDO',
    'PIANINO',
    'MYSZ',
    'ODKURZACZ',
    'LINA',
    'JEZUS',
    'DOMINO',
    'POZDRAWIAM'
  ];
  List<String> alfabet = [
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'W',
    'Y',
    'Z'
  ];
  List<String> guessedLetters = [];
  bool gameON = true;
  String finalWord = "";
  @override
  Game() {
    Random random = new Random();
    int index = random.nextInt(listOfWords.length);
    this.word = listOfWords[index];
  }
}
