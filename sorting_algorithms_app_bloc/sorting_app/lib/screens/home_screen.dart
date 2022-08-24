import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../bloc/sorting_bloc_bloc.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    final bloc = SortingBlocBloc();
    return SafeArea(
      child: Scaffold(
        body: Container(
          child: Column(
            children: [
              Row(mainAxisAlignment: MainAxisAlignment.spaceEvenly, children: [
                ElevatedButton(
                    onPressed: () {
                      BlocProvider.of<SortingBlocBloc>(context)
                          .add(InsertionEvent());
                    },
                    child: Text('Insertion')),
                ElevatedButton(onPressed: () {}, child: Text('Bubble')),
                ElevatedButton(onPressed: () {}, child: Text('Quick')),
              ]),
              BlocBuilder<SortingBlocBloc, SortingBlocState>(
                bloc: bloc,
                builder: (context, state) {
                  if (state is InsertionSortingState) return Text(state.title);
                  return Text('ddd');
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
