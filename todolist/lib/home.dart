import 'dart:async';

import 'package:flutter/material.dart';
import 'package:todolist/list.dart';
import 'package:todolist/task.dart';
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
    Timer.periodic(Duration(milliseconds: 700), (Timer t) => checkTime());
  }

  void checkTime() {
    for (int i = 0; i < nowaLista.lista.length; i++) {
      DateTime time = new DateTime.now();
      time.toLocal();
      if (time.year == nowaLista.lista[i].date.year &&
          time.month == nowaLista.lista[i].date.month &&
          time.day == nowaLista.lista[i].date.day &&
          time.hour == nowaLista.lista[i].date.hour &&
          time.minute == nowaLista.lista[i].date.minute &&
          time.second == 0) print(time.minute);
    }
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
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
    late DateTime date;
    late TimeOfDay hour;
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
                        child: Text("Wybierz datę"),
                        onPressed: (() {
                          showDatePicker(
                                  context: context,
                                  initialDate: DateTime.now(),
                                  firstDate: DateTime(2022),
                                  lastDate: DateTime(2024))
                              .then((value) => {date = value!});
                        })),
                    RaisedButton(
                        child: Text("Wybierz godzinę"),
                        onPressed: (() {
                          showTimePicker(
                                  context: context,
                                  initialTime: TimeOfDay.now())
                              .then((value) => {hour = value!});
                        })),
                    RaisedButton(
                        child: Text("Dodaj"),
                        onPressed: () {
                          nowaLista.lista.add(new Task(
                              myController.text,
                              date.year,
                              date.month,
                              date.day,
                              hour.hour,
                              hour.minute));
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
                            width: width * 0.6,
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: [
                                // SizedBox(
                                //   width: 10,
                                // ),
                                Text(
                                  nowaLista.lista[index].description,
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
