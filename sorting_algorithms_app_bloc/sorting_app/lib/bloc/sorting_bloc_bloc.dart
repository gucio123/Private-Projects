import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

part 'sorting_bloc_event.dart';
part 'sorting_bloc_state.dart';

class SortingBlocBloc extends Bloc<SortingBlocEvent, SortingBlocState> {
  SortingBlocBloc() : super(SortingBlocInitial()) {
    // ignore: void_checks
    on<SortingBlocEvent>((event, emit) async* {
      if (event is InsertionEvent) {
        print('ss');
        yield InsertionSortingState('insertion');
      }
    });
  }
}
