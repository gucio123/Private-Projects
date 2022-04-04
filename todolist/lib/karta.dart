import 'package:flutter/material.dart';

class Karta extends StatefulWidget {
  Karta({Key? key}) : super(key: key);
  List lista = [];
  int index = 0;
  Karta.secondConstructor(List lista, int index) {
    this.lista = lista;
    this.index = index;
  }
  @override
  State<Karta> createState() => _KartaState();
}

List lista = [];
int index = 0;

class _KartaState extends State<Karta> {
  @override
  Widget build(BuildContext context) {
    return Card(
      child: Row(
        children: [
          Text(
            lista[index],
          ),
          SizedBox(
            width: 20,
          ),
          // Checkbox(value: value, onChanged: onChanged)
        ],
      ),
      elevation: 10,
    );
  }
}
