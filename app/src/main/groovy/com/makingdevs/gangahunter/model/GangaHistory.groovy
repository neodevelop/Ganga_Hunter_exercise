package com.makingdevs.gangahunter.model

import android.content.Context
import com.makingdevs.gangahunter.database.GangaOpenHelper

/**
 * Created by neodevelop on 20/11/15.
 */
class GangaHistory {

  private Context mContext
  private List<Ganga> mGangas

  private static GangaHistory ourInstance

  static GangaHistory getInstance(Context context) {
    if(!ourInstance)
      ourInstance = new GangaHistory(context)
    ourInstance
  }

  private GangaHistory(Context context) {
    mContext = context
    mGangas = []

    GangaOpenHelper openhelper = new GangaOpenHelper(context)
    openhelper.getWritableDatabase()

    //100.times {
    //  Ganga ganga = new Ganga(mTitle: "Ganga $it", mPrice: new Random().nextInt(100), mPlace: "Place #$it")
    //  mGangas << ganga
    //}

  }

  Ganga findGangaById(UUID id){
    mGangas.find { g -> g.mId == id }
  }

  List<Ganga> getGangas(){ mGangas }

  void addGanga(Ganga ganga){ mGangas << ganga }

}
