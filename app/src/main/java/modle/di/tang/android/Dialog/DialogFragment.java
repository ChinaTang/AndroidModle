package modle.di.tang.android.Dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import modle.di.tang.androidmodle.R;

/**
 * Created by tangdi on 2016/1/18.
 */
public class DialogFragment extends Fragment implements View.OnClickListener{

    public static final String DATE_DIALOG = "1";

    public static final int DATE_CODE = 1;

    private static final String TAG = "DialogFragment";

    private Button bn, bn01, bn02, bn03;

    private FragmentManager fm;

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(v);
        return v;
    }

    public static DialogFragment getInstance(Bundle arg){
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.dateDialog :
                Date date = new Date();
                Bundle arg = new Bundle();
                arg.putSerializable("setData", date);
                Fdialog fdialog = Fdialog.getInstance(arg);
                fdialog.setTargetFragment(DialogFragment.this, DATE_CODE);
                fdialog.show(fm, DATE_DIALOG);
                Log.e(TAG, "onClilck DateDialog");
                break;
            case R.id.hrtizontal_dialog :
                Bundle arg1 = new Bundle();
                arg1.putInt(ProgressDialogFragment.STYLE, ProgressDialogFragment.HORI_DIALOG);
                ProgressDialogFragment progressDialogFragment1 = ProgressDialogFragment.getInstance(arg1);
                progressDialogFragment1.show(fm, "hori");
                progressDialogFragment1.runThread();
                break;
            case R.id.spinner_dialog :
                Bundle arg2 = new Bundle();
                arg2.putInt(ProgressDialogFragment.STYLE, ProgressDialogFragment.CRICLE_DIALOG);
                ProgressDialogFragment progressDialogFragment2 = ProgressDialogFragment.getInstance(arg2);
                progressDialogFragment2.show(fm, "Cricle");
                break;
            case R.id.customer_dialog :
                Bundle arg3 = new Bundle();
                arg3.putInt(ProgressDialogFragment.STYLE, ProgressDialogFragment.SELF_DIALOG);
                ProgressDialogFragment progressDialogFragment3 = ProgressDialogFragment.getInstance(arg3);
                progressDialogFragment3.show(fm, "self");
                break;
        }
    }

    private void initView(View v){
        bn = (Button) v.findViewById(R.id.dateDialog);
        bn.setOnClickListener(this);
        bn01 = (Button)v.findViewById(R.id.hrtizontal_dialog);
        bn02 = (Button)v.findViewById(R.id.spinner_dialog);
        bn03 = (Button)v.findViewById(R.id.customer_dialog);
        bn01.setOnClickListener(this);
        bn02.setOnClickListener(this);
        bn03.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requetCode, int resultCode, Intent data){
        //if(requetCode != Activity.RESULT_OK)return;
        Log.e(TAG, "onActivityResult");
        if(requetCode == DATE_CODE){
            Date date = (Date)data.getSerializableExtra(Fdialog.SETDATA);
            bn.setText(date.toString());
        }
    }
}
