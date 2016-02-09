package com.makingdevs.gangahunter.common

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.makingdevs.gangahunter.R
import groovy.transform.CompileStatic

/**
sform.CompileStatic * @groovy.transform.CompileStatic * Created by neodevelop on 20/11/15.
 */
@CompileStatic
abstract class SingleFragmentActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)
    FragmentManager fm = getSupportFragmentManager()
    Fragment fragment = fm.findFragmentById(R.id.container)
    if (!fragment)
      fm.beginTransaction().add(R.id.container, createFragment()).commit()
  }

  abstract Fragment createFragment();
}
