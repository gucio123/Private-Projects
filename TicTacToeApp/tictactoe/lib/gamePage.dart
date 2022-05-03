import 'package:flutter/material.dart';
import 'package:tictactoe/game.dart';
import 'package:tictactoe/oneCard.dart';

class GamePage extends StatefulWidget {
  const GamePage({Key? key}) : super(key: key);

  @override
  State<GamePage> createState() => _GamePageState();
}

class _GamePageState extends State<GamePage> {
  @override
  Widget build(BuildContext context) {
    Game game = new Game(0);
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return Scaffold(
        backgroundColor: Colors.grey[800],
        body: Center(
          child: Container(
            height: width,
            color: Colors.grey[800],
            child: GridView.count(
              padding: EdgeInsets.all(5),
              crossAxisSpacing: 5,
              mainAxisSpacing: 5,
              crossAxisCount: 3,
              children: [
                OneCard.second(0, game),
                OneCard.second(1, game),
                OneCard.second(2, game),
                OneCard.second(3, game),
                OneCard.second(4, game),
                OneCard.second(5, game),
                OneCard.second(6, game),
                OneCard.second(7, game),
                OneCard.second(8, game),
              ],
            ),
          ),
        ));
  }
}
