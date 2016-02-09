package com.makingdevs.gangahunter.ui.fragment

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import com.makingdevs.gangahunter.R
import groovy.transform.CompileStatic

/**
 * Created by neodevelop on 21/11/15.
 */
@CompileStatic
class DatePickerFragment extends DialogFragment {

  static final String TAG = DatePickerFragment.class.simpleName
  static String ARG_GANGA_DATE = "ganga_due_date"
  static String EXTRA_RESULT_DATE = "extra_result_date"

  static DatePickerFragment newInstance(Date initDate){
    DatePickerFragment fragment = new DatePickerFragment()
    Bundle args = new Bundle()
    args.putSerializable(ARG_GANGA_DATE, initDate)
    fragment.arguments = args
    fragment
  }

  @Override
  Dialog onCreateDialog(Bundle savedInstanceState) {
    DatePicker datePicker = LayoutInflater.from(context).inflate(R.layout.dialog_date_picker, null) as DatePicker
    Date initDate = getArguments().getSerializable(ARG_GANGA_DATE) as Date
    datePicker.init(initDate[Calendar.YEAR],initDate.month,initDate.date,null)
    new AlertDialog.Builder(getActivity()).with {
      title = R.string.title_date_picker
      setView(datePicker)
      setPositiveButton(android.R.string.ok, { DialogInterface dialogInterface, int i ->
        int year = datePicker.year
        int month = datePicker.month
        int dayOfMonth = datePicker.dayOfMonth
        def calendar = Calendar.instance
        calendar.set(year,month,dayOfMonth)
        Date dateSelected = calendar.time
        sendResult(dateSelected)
      } as DialogInterface.OnClickListener)
    }.create()
    //return super.onCreateDialog(savedInstanceState)
  }

  private void sendResult(Date date) {
    Intent intent = new Intent()
    intent.putExtra(EXTRA_RESULT_DATE, date)
    int requestCode = getTargetRequestCode()
    getTargetFragment().onActivityResult(requestCode, Activity.RESULT_OK, intent)
  }
}
