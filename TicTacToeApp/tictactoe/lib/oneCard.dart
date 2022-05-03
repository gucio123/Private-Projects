import 'game.dart';
import 'package:flutter/material.dart';

class OneCard extends StatefulWidget {
  OneCard({Key? key}) : super(key: key);
  Game game = new Game(0);
  int index = 0;
  OneCard.second(int indeks, Game gam) {
    this.game = gam;
    index = indeks;
  }

  @override
  State<OneCard> createState() => _OneCardState(game, index);
}

class _OneCardState extends State<OneCard> {
  Game gam = new Game(0);
  bool tap = false;
  int counterr = 0;
  int index = 0;
  _OneCardState(Game game, int indeks) {
    gam = game;
    index = indeks;
  }
  String setasset() {
    String result;
    if (gam.counter % 2 == 1)
      result = "assets/xx.png";
    else
      result = "assets/oo.png";
    return result;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(5)),
          color: Colors.blue[300]),
      child: Card(
        child: GestureDetector(
          child: Container(
            child: counterr == 1 ? ImageIcon(AssetImage(setasset())) : Text(""),
          ),
          onTap: tap
              ? null
              : () {
                  setState(() {
                    gam.list[index] = gam.counter % 2 + 1;
                    tap = true;
                    gam.counter += 1;
                    counterr++;
                    print(gam.list);
                    if (gam.win != 0) {}
                  });
                },
        ),
      ),
    );
  }
}
