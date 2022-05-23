import 'package:tictactoe/main.dart';

import 'game.dart';
import 'package:flutter/material.dart';

class OneCard extends StatefulWidget {
  OneCard({Key? key}) : super(key: key);
  Game game = new Game(0);
  int index = 0;
  OneCard.second(int indeks, Game gam) {
    this.game = gam;
    this.index = indeks;
  }

  @override
  State<OneCard> createState() => _OneCardState(game, index);
}

class _OneCardState extends State<OneCard> {
  Game gam = new Game(0);
  int counterr = 0;
  int index = 0;
  _OneCardState(Game game, int indeks) {
    this.gam = game;
    this.index = indeks;
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
          onTap: gam.listOfButtons[index]
              ? null
              : () {
                  setState(() {
                    gam.list[index] = gam.counter % 2 + 1;
                    gam.listOfButtons[index] = true;
                    gam.counter += 1;
                    counterr++;
                    int result = gam.win();
                    if (result != 0) {
                      showModalBottomSheet(
                        isDismissible: false,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                        context: context,
                        builder: (context) => GestureDetector(
                          onTap: () => Navigator.of(context)
                              .pushNamedAndRemoveUntil(
                                  '/screen1', (Route<dynamic> route) => false),
                          child: Container(
                            child: Center(
                                child: Column(
                              children: [
                                SizedBox(
                                  height: 50,
                                ),
                                Text(
                                  "Brawo! Gracz nr $result wygrał!",
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 20,
                                  ),
                                ),
                                SizedBox(
                                  height: 10,
                                ),
                                Text(
                                  "Kliknij, aby rozpocząć nową grę!",
                                  style: TextStyle(
                                      color: Colors.white, fontSize: 10),
                                )
                              ],
                            )),
                            decoration: BoxDecoration(
                              color: Colors.blue[300],
                              borderRadius:
                                  BorderRadius.all(Radius.circular(5)),
                            ),
                          ),
                          onVerticalDragStart: (_) {},
                        ),
                      );
                    }
                  });
                  gam.win();
                },
        ),
      ),
    );
  }
}
