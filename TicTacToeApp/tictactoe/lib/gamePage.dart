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
        height: height * 0.61,
        color: Colors.white,
        child: GridView.count(
          padding: EdgeInsets.all(5),
          crossAxisCount: 3,
          children: [
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Card(child: Text("dupa")),
            ),
            Container(
              color: Colors.red,
              width: width / 3,
              child: Card(child: Text("dupa")),
            ),
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Card(child: Text("dupa")),
            ),
            Container(
              color: Colors.red,
              width: width / 3,
              child: Text("dupa"),
            ),
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Text("dupa"),
            ),
            Container(
              color: Colors.red.shade700,
              width: width / 3,
              child: Text("dupa"),
            ),
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Text("dupa"),
            ),
            Container(
              color: Colors.red,
              width: width / 3,
              child: Text("dupa"),
            ),
            Container(
              color: Colors.blue,
              width: width / 3,
              child: Text("dupa"),
            ),
          ],
        ),
      ),
    ));
  }
}
