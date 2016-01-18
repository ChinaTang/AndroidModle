package modle.di.tang.android.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tangdi on 2016/1/18.
 * dialogFragment
 */
public class Fdialog extends android.support.v4.app.DialogFragment{

    private Date mDate;

    private static final String TAG = "Fdialog";

    public final static String SETDATA = "setData";

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState){
        mDate = (Date)getArguments().getSerializable(SETDATA);
        DatePicker datePicker = new DatePicker(getActivity());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate = new GregorianCalendar(year, month, day).getTime();
                getArguments().putSerializable(SETDATA, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle("选择时间")
                .setView(datePicker)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(SETDATA, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        Log.d(TAG, "");
    }

    public static Fdialog getInstance(Bundle bundle){
        Fdialog fdialog = new Fdialog();
        fdialog.setArguments(bundle);
        return fdialog;
    }

}
