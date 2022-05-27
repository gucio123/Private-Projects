import 'package:flutter/material.dart';

class AsciiScreen extends StatefulWidget {
  AsciiScreen({Key? key}) : super(key: key);
  List list = [];
  AsciiScreen.second(List list) {
    this.list = list;
  }

  @override
  State<AsciiScreen> createState() => _AsciiScreenState(list);
}

class _AsciiScreenState extends State<AsciiScreen> {
  List list = [];
  _AsciiScreenState(List list) {
    this.list = list;
  }

  @override
  Widget build(BuildContext context) {
    return Container();
  }
}
