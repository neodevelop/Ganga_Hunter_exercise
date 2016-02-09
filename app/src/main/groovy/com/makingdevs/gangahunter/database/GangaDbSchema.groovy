package com.makingdevs.gangahunter.database

/**
 * Created by neodevelop on 21/11/15.
 */
class GangaDbSchema {
  static class GangaTable {
    static final String NAME = "gangas"
    static class Column {
      static final String UUID = "uuid"
      static final String TITLE = "title"
      static final String PRICE = "price"
      static final String PLACE = "place"
      static final String DUE_DATE = "due_date"
    }
  }
}
