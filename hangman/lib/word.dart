import 'package:flutter/material.dart';
import 'game.dart';

class Word extends StatefulWidget {
  Word({Key? key}) : super(key: key);
  String word = "";
  Game gam = new Game();
  Word.secondConstructor(String word, Game gam) {
    this.word = word;
    this.gam = gam;
  }
  @override
  State<Word> createState() => _WordState(word, gam);
}

class _WordState extends State<Word> {
  String myword = "";
  Game game = new Game();
  _WordState(String word, Game game) {
    myword = word;
    this.game = game;
  }

  String wordInGame(String word) {
    String result = "";
    game.finalWord = "";
    for (int i = 0; i < word.length; i++) {
      if (game.guessedLetters.contains('${word[i]}')) {
        result += '${word[i]}  ';
        game.finalWord += '${word[i]}';
      } else
        result += '_  ';
    }
    return result;
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        wordInGame(myword),
        style: TextStyle(fontSize: 20),
      ),
    );
  }
}
