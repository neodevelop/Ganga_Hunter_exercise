package com.makingdevs.gangahunter.model

import java.util.UUID

/**
 * Created by neodevelop on 20/11/15.
 */
class Ganga {
  UUID mId
  String mTitle
  float mPrice
  Date mDueDate
  String mPlace

  Ganga() {
    mId = UUID.randomUUID()
    mDueDate = new Date()
  }

}
