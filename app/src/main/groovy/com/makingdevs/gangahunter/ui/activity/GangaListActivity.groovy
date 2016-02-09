package com.makingdevs.gangahunter.ui.activity

import android.support.v4.app.Fragment
import com.makingdevs.gangahunter.common.SingleFragmentActivity
import com.makingdevs.gangahunter.ui.fragment.GangaListFragment

/**
 * Created by neodevelop on 20/11/15.
 */
class GangaListActivity extends SingleFragmentActivity {

  /*
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)
  }
  */

  @Override
  Fragment createFragment() {
    new GangaListFragment()
  }
}
