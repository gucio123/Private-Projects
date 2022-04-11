import 'package:flutter/material.dart';

class GamePage extends StatefulWidget {
  const GamePage({Key? key}) : super(key: key);

  @override
  State<GamePage> createState() => _GamePageState();
}

class _GamePageState extends State<GamePage> {
  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return Scaffold(
        body: Center(
      child: Container(
        height: width,
        color: Colors.white,
        child: GridView.count(
          padding: EdgeInsets.all(5),
          crossAxisCount: 3,
          children: [
            Container(
              color: Colors.black,
              width: width / 3,
              child: GestureDetector(
                  onTap: () {
                    print("object");
                  },
                  child: Card(
                    child: Text("1"),
                  )),
            ),
            Container(
              color: Colors.yellow,
              width: width / 3,
              child: Card(child: Text("2")),
            ),
            Container(
              color: Colors.black,
              width: width / 3,
              child: Card(child: Text("3")),
            ),
            Container(
              color: Colors.red,
              width: width / 3,
              child: Card(child: Text("4")),
            ),
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Card(child: Text("5")),
            ),
            Container(
              color: Colors.red.shade700,
              width: width / 3,
              child: Card(child: Text("6")),
            ),
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Card(child: Text("7")),
            ),
            Container(
              color: Colors.red,
              width: width / 3,
              child: Card(child: Text("8")),
            ),
            Container(
                color: Colors.blue,
                width: width / 3,
                child: Card(child: Text("9"))),
          ],
        ),
      ),
    ));
  }
}
