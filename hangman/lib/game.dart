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
    'PIANINO',
    'MYSZ',
    'ODKURZACZ',
    'LINA',
    'JEZUS',
    'DOMINO',
    'POZDRAWIAM',
    'KORONA',
    'ZEGAREK',
    'KALENDARZ',
    'KLAWIATURA',
    'TALERZ',
    'WIDELEC',
    'NOTATNIK',
    'SZAFKA',
    'KOT'
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
