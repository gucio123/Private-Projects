import 'package:flutter/material.dart';
import 'package:hangman/game.dart';
import 'package:hangman/word.dart';

class GamePage extends StatefulWidget {
  const GamePage({Key? key}) : super(key: key);

  @override
  State<GamePage> createState() => _GamePageState();
}

class _GamePageState extends State<GamePage> {
  Game game = new Game();
  TextEditingController controll = new TextEditingController();
  void guess(Game game, String letter) {
    if (game.word.contains(letter)) {
      game.guessedLetters.add(letter);
    } else
      game.mistakesCounter += 1;
    game.finalWord = "";
    for (int i = 0; i < game.word.length; i++) {
      if (game.guessedLetters.contains('${game.word[i]}')) {
        game.finalWord += '${game.word[i]}';
      }
    }
    if (game.mistakesCounter >= 6 || game.finalWord == game.word)
      game.gameON = false;
  }

  void instantGuess(Game game, String word) {
    if (game.word == word) {
      game.gameON = false;
      game.finalWord = word;
    } else
      game.mistakesCounter += 1;
  }

  String pickImage() {
    String image = 'assets/seventh.png';

    switch (game.mistakesCounter) {
      case 0:
        image = 'assets/first.png';
        break;
      case 1:
        image = 'assets/second.png';
        break;
      case 2:
        image = 'assets/third.png';
        break;
      case 3:
        image = 'assets/forth.png';
        break;
      case 4:
        image = 'assets/fifth.png';
        break;
      case 5:
        image = 'assets/sixth.png';
        break;
      case 6:
        image = 'assets/seventh.png';
        game.gameON = false;
        break;
    }

    return image;
  }

  List<bool> isButtonActive = [
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
    true,
  ];
  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.black,
        title: Text("Zagraj ze mną w wisielca!"),
      ),
      body: Column(
        children: [
          Expanded(
            flex: 5,
            child: Image(image: AssetImage(pickImage())),
          ),
          Expanded(
            flex: 2,
            child: Center(
              child: Padding(
                padding: EdgeInsets.fromLTRB(width / 20, 0, width / 20, 0),
                child: Column(
                  children: [
                    game.finalWord == game.word
                        ? Text("UDAŁO CI SIĘ WYGRAĆ!")
                        : Word.secondConstructor(game.word, game),
                    TextField(
                      textCapitalization: TextCapitalization.characters,
                      controller: controll,
                      cursorColor: Colors.black,
                      decoration: InputDecoration(
                          hintText: "Wpisz słowo jeśli już wiesz!",
                          hoverColor: Colors.black,
                          suffixIcon: IconButton(
                            icon: Icon(
                              Icons.arrow_circle_up,
                              color: Colors.black,
                            ),
                            onPressed: () {
                              instantGuess(game, controll.text);
                              controll.text = "";
                              setState(() {});
                            },
                          )),
                    )
                  ],
                ),
              ),
            ),
          ),
          Expanded(
            flex: 3,
            child: GridView.builder(
              padding: EdgeInsets.fromLTRB(width / 40, 0, width / 40, 0),
              scrollDirection: Axis.horizontal,
              itemCount: 24,
              gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                  maxCrossAxisExtent: 65,
                  childAspectRatio: 3 / 2,
                  crossAxisSpacing: 45,
                  mainAxisSpacing: 20),
              itemBuilder: (context, index) {
                return GridTile(
                    child: Container(
                        color: Colors.white,
                        alignment: Alignment.center,
                        child: TextButton(
                          style: ButtonStyle(
                              backgroundColor:
                                  MaterialStateProperty.all(Colors.black)),
                          child: Text(
                            game.alfabet[index],
                            style: isButtonActive[index]
                                ? TextStyle(color: Colors.white)
                                : TextStyle(color: Colors.black),
                          ),
                          onPressed: isButtonActive[index] && game.gameON
                              ? () {
                                  isButtonActive[index] = false;
                                  guess(game, game.alfabet[index]);
                                  setState(() {});
                                }
                              : null,
                        )));
              },
            ),
          )
        ],
      ),
    );
  }
}
