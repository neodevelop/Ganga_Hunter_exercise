package com.makingdevs.gangahunter.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.makingdevs.gangahunter.R
import com.makingdevs.gangahunter.model.Ganga
import com.makingdevs.gangahunter.model.GangaHistory
import com.makingdevs.gangahunter.ui.activity.GangaActivity
import com.makingdevs.gangahunter.ui.adapter.GangaAdapter
import groovy.transform.CompileStatic


/**
 * A simple {@link Fragment} subclass.
 */
@CompileStatic
class GangaListFragment extends Fragment {

  RecyclerView mListGanga
  GangaAdapter mAdapter

  GangaListFragment() {
    // Required empty constructor
  }


  @Override
  View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View root = inflater.inflate(R.layout.fragment_ganga_list, container, false)
    mListGanga = (RecyclerView) root.findViewById(R.id.list_gangas)
    mListGanga.setLayoutManager(new LinearLayoutManager(getActivity()))
    updateUI()
    setHasOptionsMenu(true)
    root
  }

  @Override
  void onResume() {
    super.onResume()
    updateUI()
  }

  @Override
  void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_ganga_list_fragment, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  @Override
  boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId()
    switch(id){
      case R.id.action_add:
        Ganga ganga = new Ganga()
        GangaHistory.getInstance(getContext()).addGanga(ganga)
        Intent intent = GangaActivity.newIntentWithContext(getContext(), ganga.mId)
        startActivity(intent)
        break
    }
    return super.onOptionsItemSelected(item)
  }

  void updateUI() {
    List<Ganga> gangaHistory = GangaHistory.getInstance(getActivity()).getGangas()
    if(!mAdapter) {
      mAdapter = new GangaAdapter(getActivity(), gangaHistory)
      mListGanga.adapter = mAdapter
    }
    else{
      mAdapter.setmGangas(gangaHistory)
      mAdapter.notifyDataSetChanged()
    }

  }

}
