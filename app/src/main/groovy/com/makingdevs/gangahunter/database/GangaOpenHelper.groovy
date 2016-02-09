package com.makingdevs.gangahunter.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import static com.makingdevs.gangahunter.database.GangaDbSchema.*

/**
 * Created by neodevelop on 21/11/15.
 */
class GangaOpenHelper extends SQLiteOpenHelper {

  static final String DATABASE_NAME = "ganga_hunter.db"
  static final int VERSION = 1

  GangaOpenHelper(Context context) {
    super(context, DATABASE_NAME, null, VERSION)
  }

  @Override
  void onCreate(SQLiteDatabase db) {
    db.execSQL("""\
      create table ${GangaTable.NAME}( _id integer primary key autoincrement,
      ${GangaTable.Column.UUID},
      ${GangaTable.Column.TITLE},
      ${GangaTable.Column.PRICE},
      ${GangaTable.Column.PLACE},
      ${GangaTable.Column.DUE_DATE} )
    """)
  }

  @Override
  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(" drop table if exists ${GangaTable.NAME} ")
    onCreate(db)
  }
}
