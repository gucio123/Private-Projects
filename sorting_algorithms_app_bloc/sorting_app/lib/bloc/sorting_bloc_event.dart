part of 'sorting_bloc_bloc.dart';

abstract class SortingBlocEvent extends Equatable {
  const SortingBlocEvent();

  @override
  List<Object> get props => [];
}

class InsertionEvent extends SortingBlocEvent {}
