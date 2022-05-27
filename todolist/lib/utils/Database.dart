import 'package:sqflite/sqflite.dart';
import 'package:sqflite/sqlite_api.dart';
import 'package:path/path.dart';

class DBProvider {
  DBProvider._();

  static final DBProvider db = DBProvider._();
  static late Database _database;

  Future<Database> get database async {
    if (_database != null) return _database;

    _database = await initDB();
    return _database;
  }

  initDB() async {
    return await openDatabase(
      join(await getDatabasesPath(), 'to_do_database'),
      onCreate: (db, version) async {
        await db.execute('''
          CREATE TABLE tasks (
            description TEXT , date datetime
          )
        ''');
      },
    );
  }
}
