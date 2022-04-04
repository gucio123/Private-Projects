import 'package:flutter/material.dart';
import 'package:todolist/list.dart';
import 'list.dart';
import 'package:flutter/src/rendering/box.dart';

class Home extends StatefulWidget {
  Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  Lista nowaLista = new Lista();
  final myController = TextEditingController();
  bool boolcheck = false;
  final snackBar = SnackBar(
    padding: EdgeInsets.all(5),
    backgroundColor: Colors.amber,
    content: Text(
      "Brawo, udało się zrealizować wszystkie cele!",
      style: TextStyle(color: Colors.black),
    ),
  );

  @override
  void initState() {
    super.initState();
  }

  void checkIfDone(List lista) {
    int check = 1;
    for (int i = 0; i < lista.length; i++) {
      if (lista[i] == true) check += 1;
    }
    if (check == lista.length)
      ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey.shade800,
      floatingActionButton: new FloatingActionButton(
        backgroundColor: Colors.lightBlue,
        onPressed: () {
          showModalBottomSheet<void>(
              context: context,
              builder: (BuildContext context) {
                return Container(
                  padding: EdgeInsets.all(20),
                  child: Column(children: [
                    TextField(
                      maxLength: 30,
                      decoration: InputDecoration(),
                      controller: myController,
                    ),
                    RaisedButton(
                        child: Text("Dodaj"),
                        onPressed: () {
                          nowaLista.lista.add(myController.text);
                          nowaLista.listabool.add(false);
                          setState(() {});
                          myController.clear();
                        }),
                  ]),
                );
              });
        },
        child: Icon(
          Icons.add,
          color: Colors.white,
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      appBar: AppBar(
        titleSpacing: 50,
        iconTheme: IconThemeData(),
        title: Text(
          "Zobacz co masz do zrobienia!",
        ),
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              scrollDirection: Axis.vertical,
              shrinkWrap: true,
              itemCount: nowaLista.lista.length,
              itemBuilder: (BuildContext context, int index) {
                return ListTile(
                  title: SizedBox(
                    height: 40,
                    child: Card(
                      child: Row(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Container(
                            width: 245,
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: [
                                SizedBox(
                                  width: 10,
                                ),
                                Text(
                                  nowaLista.lista[index],
                                ),
                              ],
                            ),
                          ),
                          IconButton(
                            onPressed: () {
                              nowaLista.lista.remove(nowaLista.lista[index]);
                              nowaLista.listabool
                                  .remove(nowaLista.listabool[index]);
                              setState(() {});
                            },
                            icon: Icon(Icons.delete),
                            color: Colors.lightBlue,
                          ),
                          Checkbox(
                              activeColor: Colors.lightBlue,
                              visualDensity: VisualDensity(horizontal: 2),
                              value: nowaLista.listabool[index],
                              onChanged: (bool? value) {
                                checkIfDone(nowaLista.listabool);
                                setState(() {
                                  nowaLista.listabool[index] = value;
                                });
                              })
                        ],
                      ),
                      elevation: 10,
                    ),
                  ),
                );
              },
            ),
          ),
          SizedBox(
            height: 50,
          ),
        ],
      ),
    );
  }
}
