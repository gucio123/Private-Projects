import 'package:flutter/material.dart';
import 'package:tictactoe/gamePage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: <String, WidgetBuilder>{
        '/screen1': (BuildContext context) => new MyHomePage(),
      },
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    return Scaffold(
        backgroundColor: Colors.blue[300],
        body: Center(
          child: Container(
              height: height / 3,
              width: width - 20,
              color: Colors.white,
              child: Column(
                children: [
                  SizedBox(
                    height: height / 10,
                  ),
                  Text(
                    "Wybierz rodzaj gry",
                    style: TextStyle(fontSize: 20),
                  ),
                  SizedBox(
                    height: height / 20,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      FlatButton(
                        onPressed: () {},
                        child: Text("Gra z komputerem"),
                        color: Colors.blue[300],
                      ),
                      SizedBox(
                        width: 10,
                      ),
                      FlatButton(
                        onPressed: () => Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => GamePage())),
                        child: Text("Gra z przeciwnikiem"),
                        color: Colors.blue[300],
                      )
                    ],
                  )
                ],
              )),
        ));
  }
}
