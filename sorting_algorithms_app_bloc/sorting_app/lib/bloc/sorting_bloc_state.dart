part of 'sorting_bloc_bloc.dart';

abstract class SortingBlocState extends Equatable {
  const SortingBlocState();

  @override
  List<Object> get props => [];
}

class SortingBlocInitial extends SortingBlocState {}

class InsertionSortingState extends SortingBlocState {
  String title;

  InsertionSortingState(this.title);

  @override
  List<Object> get props => [title];
}
