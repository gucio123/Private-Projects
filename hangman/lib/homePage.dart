import 'package:flutter/material.dart';
import 'package:hangman/gamePage.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ColoredBox(
          color: Colors.black,
          child: Container(
            height: 200,
            width: 200,
            child: Center(
              child: FlatButton(
                color: Colors.white,
                child: Text("ZAGRAJ"),
                onPressed: () {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => GamePage()));
                },
              ),
            ),
          ),
        ),
      ),
    );
  }
}
