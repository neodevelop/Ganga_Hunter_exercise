package com.makingdevs.gangahunter.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.makingdevs.gangahunter.model.Ganga
import com.makingdevs.gangahunter.R
import com.makingdevs.gangahunter.model.GangaHistory
import groovy.transform.CompileStatic

/**
 * Created by neodevelop on 20/11/15.
 */
@CompileStatic
class GangaFragment extends Fragment{

  static String ARG_GANGA_ID = "arg_ganga_id"

  Ganga mGanga
  EditText mTitle
  EditText mPrice
  EditText mPlace
  Button mDueDate

  static final String LOG_TAG = GangaFragment.class.simpleName
  static final int REQUEST_DATE = 100

  static GangaFragment newInstance(UUID gangaId){
    GangaFragment fragment = new GangaFragment()
    Bundle args = new Bundle()
    args.putSerializable(ARG_GANGA_ID, gangaId)
    fragment.arguments = args
    fragment
  }

  @Override
  void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)
    if(!getArguments() || !getArguments()?.getSerializable(ARG_GANGA_ID))
      throw new IllegalArgumentException("No arguments $ARG_GANGA_ID")
    UUID gangaId = (UUID) getArguments()?.getSerializable(ARG_GANGA_ID)
    mGanga = GangaHistory.getInstance(getContext()).findGangaById(gangaId)
    //mGanga = new Ganga()
  }

  @Override
  View onCreateView(LayoutInflater inflater,
                    @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_ganga, container, false)
    mTitle = (EditText) root.findViewById(R.id.input_title)
    mPrice = (EditText) root.findViewById(R.id.input_price)
    mPlace = (EditText) root.findViewById(R.id.input_place)
    mDueDate = (Button) root.findViewById(R.id.button_due_date)

    mTitle.text = mGanga.mTitle
    mPrice.text = "$mGanga.mPrice \$"
    mPlace.text = mGanga.mPlace
    mDueDate.text = mGanga.mDueDate.format("dd/MMMM/yyyy")

    Map textWatcherMethodsForTitle = [
        beforeTextChanged:{CharSequence s, int start, int count, int after -> },
        onTextChanged:{CharSequence s, int start, int count, int after ->
          mGanga.mTitle = s.toString()
        },
        afterTextChanged:{ Editable s -> }
    ]
    Map textWatcherMethodsForPrice = [
        beforeTextChanged:{CharSequence s, int start, int count, int after -> },
        onTextChanged:{CharSequence s, int start, int count, int after ->
          mGanga.mPrice = s ? s.toFloat() : 0
        },
        afterTextChanged:{ Editable s -> }
    ]
    Map textWatcherMethodsForPlace = [
        beforeTextChanged:{CharSequence s, int start, int count, int after -> },
        onTextChanged:{CharSequence s, int start, int count, int after ->
          mGanga.mPlace = s.toString()
        },
        afterTextChanged:{ Editable s -> }
    ]
    mTitle.addTextChangedListener( textWatcherMethodsForTitle as TextWatcher )
    mPrice.addTextChangedListener( textWatcherMethodsForPrice as TextWatcher )
    mPlace.addTextChangedListener( textWatcherMethodsForPlace as TextWatcher )
    mDueDate.onClickListener = { View view ->
      FragmentManager fm = getFragmentManager()
      DialogFragment datePickerFragment = DatePickerFragment.newInstance(mGanga.mDueDate)
      datePickerFragment.setTargetFragment(this, REQUEST_DATE)
      datePickerFragment.show(fm, DatePickerFragment.TAG)
    } as View.OnClickListener
    //return super.onCreateView(inflater, container, savedInstanceState)
    root
  }

  @Override
  void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data)
    if(resultCode == Activity.RESULT_OK)
      if(requestCode == REQUEST_DATE){
        Date newDueDate = data.getSerializableExtra(DatePickerFragment.EXTRA_RESULT_DATE) as Date
        mGanga.mDueDate = newDueDate
        mDueDate.text = "${newDueDate.format("dd/MMMM/yyyy")}"
      }
  }
}
