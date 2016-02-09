package com.makingdevs.gangahunter.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.makingdevs.gangahunter.R
import com.makingdevs.gangahunter.model.Ganga
import com.makingdevs.gangahunter.ui.activity.GangaActivity
import groovy.transform.CompileStatic

/**
 * Created by neodevelop on 20/11/15.
 */
@CompileStatic
class GangaAdapter extends RecyclerView.Adapter<GangaViewHolder> {

  Context mContext
  List<Ganga> mGangas

  GangaAdapter(Context context, List<Ganga> gangas){
    mContext = context
    mGangas = gangas
  }

  @Override
  GangaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_ganga, parent, false)
    new GangaViewHolder(view)
  }

  @Override
  void onBindViewHolder(GangaViewHolder holder, int position) {
    Ganga ganga = mGangas[position]
    holder.bindGanga(ganga)
  }

  @Override
  int getItemCount() {
    mGangas.size()
  }

  class GangaViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    TextView mTitle
    TextView mPrice
    TextView mPlace
    TextView mDueDate
    Ganga mGanga

    GangaViewHolder(View itemView) {
      super(itemView)
      itemView.onClickListener = this
      mTitle = (TextView) itemView.findViewById(R.id.label_title)
      mPrice = (TextView) itemView.findViewById(R.id.label_price)
      mPlace = (TextView) itemView.findViewById(R.id.label_place)
      mDueDate = (TextView) itemView.findViewById(R.id.label_date)
    }

    void bindGanga(Ganga ganga) {
      mGanga = ganga

      mTitle.text = ganga.mTitle
      mPrice.text = "${ganga.mPrice} \$"
      mPlace.text = ganga.mPlace
      mDueDate.text = ganga.mDueDate.format("dd/MM/yyyy")

    }

    @Override
    void onClick(View v) {
      //Toast.makeText(mContext, "${mTitle.text}", Toast.LENGTH_SHORT).show()
      Intent gangaIntent = GangaActivity.newIntentWithContext(mContext, mGanga.mId)
      mContext.startActivity(gangaIntent)
    }
  }
}
