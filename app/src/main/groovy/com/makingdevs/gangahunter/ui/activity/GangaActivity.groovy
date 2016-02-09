package com.makingdevs.gangahunter.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.makingdevs.gangahunter.common.SingleFragmentActivity
import com.makingdevs.gangahunter.ui.fragment.GangaFragment
import groovy.transform.CompileStatic

@CompileStatic
class GangaActivity extends SingleFragmentActivity {

  static final String LOG_TAG = GangaActivity.class.simpleName
  static String EXTRA_GANGA_ID = "ganga_id"

  static Intent newIntentWithContext(Context context, UUID id){
    Intent intent = new Intent(context, GangaActivity)
    intent.putExtra(EXTRA_GANGA_ID, id)
    intent
  }

  @Override
  Fragment createFragment() {
    //NOTE : Los extras podrían ser null
    UUID id = (UUID) getIntent()?.extras.getSerializable(EXTRA_GANGA_ID)
    if(!id) throw new IllegalArgumentException("La ganga no tiene ID, chale!")
    GangaFragment.newInstance(id)
  }

  /*
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)

    FragmentManager fragmentManager = getSupportFragmentManager()
    Fragment fragment = fragmentManager.findFragmentById(R.id.container)
    if(!fragment)
      fragmentManager.beginTransaction().add(R.id.container, new GangaFragment()).commit()

    if(fragmentManager.fragments)
      Log.d(LOG_TAG,"Fragments: ${fragmentManager.fragments.size()}")

  }
  */
}
