class Task {
  String description = "";
  late DateTime date;

  Task(String desc, int year, int month, int day, int hour, int minute) {
    this.description = desc;
    this.date = new DateTime(year, month, day, hour, minute);
  }
}
